/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license 
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Condition
import com.intellij.openapi.util.Conditions
import com.intellij.openapi.util.TextRange
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.containers.ContainerUtil
import org.jetbrains.kotlin.asJava.getJvmSignatureDiagnostics
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.diagnostics.*
import org.jetbrains.kotlin.load.java.InternalFlexibleTypeTransformer
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.MultiTargetPlatform
import org.junit.Assert
import java.util.ArrayList
import java.util.HashSet
import java.util.regex.Pattern

enum class TestArea {
    PSI,
    DIAGNOSTICS,
    CODEGEN
}

enum class TestRunner(area: TestArea) {
    DIAGNOSTIC_SPEC_TESTS(TestArea.DIAGNOSTICS),
    PSI_SPEC_TESTS(TestArea.PSI)
}

enum class DirectiveType {
    DEFAULT,
    HELPER_FILE
}

enum class DirectiveOption {
    HELPER_FILE_PATH
}

enum class Directive(
    val directiveType: DirectiveType = DirectiveType.DEFAULT,
    val directiveOptions: Map<DirectiveOption, String>? = null
) {
    EXPLICIT_FLEXIBLE_TYPES,
    CHECK_LAZY_LOG,
    MARK_DYNAMIC_CALLS,
    WITH_NEW_INFERENCE,
    API_VERSION,
    LANGUAGE,
    USE_EXPERIMENTAL,
    EXPERIMENTAL,
    JVM_DEFAULT_MODE,
    IGNORE_DATA_FLOW_IN_ASSERT,
    SKIP_METADATA_VERSION_CHECK,
    DIAGNOSTICS,
    DIAGNOSTICS_NUMBER,
    MESSAGE_TYPE,

    CHECK_TYPE(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/checkType.kt")
    ),
    WITH_BASIC_TYPES(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/basicTypes.kt")
    ),
    WITH_CLASSES(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/classes.kt")
    ),
    WITH_CONTRACT_FUNCTIONS(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/contractFunctions.kt")
    ),
    WITH_SEALED_CLASSES(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/sealedClasses.kt")
    ),
    WITH_ENUM_CLASSES(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/enumClasses.kt")
    ),
    WITH_FUNCTIONS(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/functions.kt")
    ),
    WITH_OBJECTS(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "diagnostics/helpers/templates/objects.kt")
    ),
    WITH_TYPEALIASES(
        DirectiveType.HELPER_FILE,
        mapOf(DirectiveOption.HELPER_FILE_PATH to "templates/typealiases.kt")
    );
}

class TestFile(
    val module: TestModule?,
    fileName: String,
    val expectedText: String,
    val directives: Map<Directive, String>
) {
    private val diagnosedRanges: List<CheckerTestUtil.DiagnosedRange> = ArrayList()
    val actualDiagnostics: MutableList<CheckerTestUtil.ActualDiagnostic> = ArrayList()
    private val clearText: String
    private val createKtFile: Lazy<KtFile?>
    private val whatDiagnosticsToConsider: Condition<Diagnostic>
    val customLanguageVersionSettings: LanguageVersionSettings?
    val jvmTarget: JvmTarget?
    val checkLazyLog: Boolean
    val dynamicCallDescriptors: List<DeclarationDescriptor> = ArrayList()
    val newInferenceEnabled: Boolean
    lateinit var project: Project

    companion object {
        private const val TEST_DATA_PATH = "./testData"
        val DIAGNOSTICS_PATTERN: Pattern = Pattern.compile("([\\+\\-!])(\\w+)\\s*")
        val DIAGNOSTICS_TO_INCLUDE_ANYWAY: Set<DiagnosticFactory<*>> = setOf(
            Errors.UNRESOLVED_REFERENCE,
            Errors.UNRESOLVED_REFERENCE_WRONG_RECEIVER,
            CheckerTestUtil.SyntaxErrorDiagnosticFactory.INSTANCE,
            CheckerTestUtil.DebugInfoDiagnosticFactory.ELEMENT_WITH_ERROR_TYPE,
            CheckerTestUtil.DebugInfoDiagnosticFactory.MISSING_UNRESOLVED,
            CheckerTestUtil.DebugInfoDiagnosticFactory.UNRESOLVED_WITH_TARGET
        )

        val CHECK_TYPE_PACKAGE = "tests._checkType"
        val CHECK_TYPE_IMPORT = "import $CHECK_TYPE_PACKAGE.*"

        val EXPLICIT_FLEXIBLE_PACKAGE = InternalFlexibleTypeTransformer.FLEXIBLE_TYPE_CLASSIFIER.packageFqName.asString()
        val EXPLICIT_FLEXIBLE_CLASS_NAME = InternalFlexibleTypeTransformer.FLEXIBLE_TYPE_CLASSIFIER.relativeClassName.asString()
        private val EXPLICIT_FLEXIBLE_TYPES_IMPORT = "import $EXPLICIT_FLEXIBLE_PACKAGE.$EXPLICIT_FLEXIBLE_CLASS_NAME"
        val CHECK_LAZY_LOG_DEFAULT = "true" == System.getProperty("check.lazy.logs", "false")

        // Change it to "true" to load diagnostics for old inference to test new inference (ignore diagnostics with <NI; prefix)
        val USE_OLD_INFERENCE_DIAGNOSTICS_FOR_NI = false
    }

    init {
        this.whatDiagnosticsToConsider = parseDiagnosticFilterDirective(directives, Directive.CHECK_TYPE in directives)
        this.customLanguageVersionSettings = parseLanguageVersionSettings(directives)
        this.jvmTarget = parseJvmTarget(directives)
        this.checkLazyLog = Directive.CHECK_LAZY_LOG in directives || CHECK_LAZY_LOG_DEFAULT
        this.newInferenceEnabled = customLanguageVersionSettings?.supportsFeature(LanguageFeature.NewInference) ?: shouldUseNewInferenceForTests()
        if (fileName.endsWith(".java")) {
            // TODO: check there are no syntax errors in .java sources
            this.clearText = this.expectedText
            this.createKtFile = lazyOf(null)
        } else {
            this.clearText = CheckerTestUtil.parseDiagnosedRanges(addExtras(expectedText), diagnosedRanges)
            this.createKtFile = lazy { TestCheckerUtil.createCheckAndReturnPsiFile(fileName, clearText, project) }
        }
    }

    val ktFile: KtFile? by createKtFile

    private fun includeExtras() {
        for ((directive) in directives) {
            if (directive.directiveType == DirectiveType.HELPER_FILE) {
                
            }
        }
    }

    private val imports: String
        get() = buildString {
            // Line separator is "\n" intentionally here (see DocumentImpl.assertValidSeparators)
            if (Directive.CHECK_TYPE in directives) {
                append(CHECK_TYPE_IMPORT + "\n")
            }
            if (Directive.EXPLICIT_FLEXIBLE_TYPES in directives) {
                append(EXPLICIT_FLEXIBLE_TYPES_IMPORT + "\n")
            }
        }

    private val extras: String
        get() = "/*extras*/\n$imports/*extras*/\n\n"

    private fun addExtras(text: String): String {
        for ((directive) in directives) {
            if (directive.directiveType == DirectiveType.HELPER_FILE) {

            }
        }


        addImports(text, extras)
    }

    private fun stripExtras(actualText: StringBuilder) {
        val extras = extras
        val start = actualText.indexOf(extras)
        if (start >= 0) {
            actualText.delete(start, start + extras.length)
        }
    }

    private fun parseJvmTarget(directiveMap: Map<Directive, String>) = directiveMap[JVM_TARGET]?.let { JvmTarget.fromString(it) }

    private fun addImports(text: String, imports: String): String {
        var result = text
        val pattern = Pattern.compile("^package [\\.\\w\\d]*\n", Pattern.MULTILINE)
        val matcher = pattern.matcher(result)
        if (matcher.find()) {
            // add imports after the package directive
            result = result.substring(0, matcher.end()) + imports + result.substring(matcher.end())
        }
        else {
            // add imports at the beginning
            result = imports + result
        }
        return result
    }

    private fun shouldUseNewInferenceForTests(): Boolean {
        if (System.getProperty("kotlin.ni") == "true") return true
        return LanguageVersionSettingsImpl.DEFAULT.supportsFeature(LanguageFeature.NewInference)
    }

    private fun parseDiagnosticFilterDirective(directiveMap: Map<Directive, String>, allowUnderscoreUsage: Boolean): Condition<Diagnostic> {
        val directives = directiveMap[Directive.DIAGNOSTICS]
        val initialCondition =
            if (allowUnderscoreUsage)
                Condition<Diagnostic> { it.factory.name != "UNDERSCORE_USAGE_WITHOUT_BACKTICKS" }
            else
                Conditions.alwaysTrue()

        if (directives == null) {
            // If "!API_VERSION" is present, disable the NEWER_VERSION_IN_SINCE_KOTLIN diagnostic.
            // Otherwise it would be reported in any non-trivial test on the @SinceKotlin value.
            if (Directive.API_VERSION in directiveMap) {
                return Conditions.and(initialCondition, Condition {
                        diagnostic -> diagnostic.factory !== Errors.NEWER_VERSION_IN_SINCE_KOTLIN
                })
            }
            return initialCondition
        }

        var condition = initialCondition
        val matcher = DIAGNOSTICS_PATTERN.matcher(directives)
        if (!matcher.find()) {
            Assert.fail("Wrong syntax in the '// !${Directive.DIAGNOSTICS}: ...' directive:\n" +
                                "found: '$directives'\n" +
                                "Must be '([+-!]DIAGNOSTIC_FACTORY_NAME|ERROR|WARNING|INFO)+'\n" +
                                "where '+' means 'include'\n" +
                                "      '-' means 'exclude'\n" +
                                "      '!' means 'exclude everything but this'\n" +
                                "directives are applied in the order of appearance, i.e. !FOO +BAR means include only FOO and BAR")
        }

        var first = true
        do {
            val operation = matcher.group(1)
            val name = matcher.group(2)

            val newCondition: Condition<Diagnostic> =
                if (name in setOf("ERROR", "WARNING", "INFO")) {
                    Condition { diagnostic -> diagnostic.severity == Severity.valueOf(name) }
                }
                else {
                    Condition { diagnostic -> name == diagnostic.factory.name }
                }

            when (operation) {
                "!" -> {
                    if (!first) {
                        Assert.fail("'$operation$name' appears in a position rather than the first one, " +
                                            "which effectively cancels all the previous filters in this directive")
                    }
                    condition = newCondition
                }
                "+" -> condition = Conditions.or(condition, newCondition)
                "-" -> condition = Conditions.and(condition, Conditions.not(newCondition))
            }
            first = false
        }
        while (matcher.find())

        // We always include UNRESOLVED_REFERENCE and SYNTAX_ERROR because they are too likely to indicate erroneous test data
        return Conditions.or(
            condition,
            Condition { diagnostic -> diagnostic.factory in DIAGNOSTICS_TO_INCLUDE_ANYWAY }
        )
    }

    fun getActualText(
        bindingContext: BindingContext,
        implementingModulesBindings: List<Pair<MultiTargetPlatform, BindingContext>>,
        actualText: StringBuilder,
        skipJvmSignatureDiagnostics: Boolean
    ): Boolean {
        val ktFile = this.ktFile
        if (ktFile == null) {
            // TODO: check java files too
            actualText.append(this.clearText)
            return true
        }

        if (ktFile.name.endsWith("CoroutineUtil.kt") && ktFile.packageFqName == FqName("helpers")) return true

        // TODO: report JVM signature diagnostics also for implementing modules
        val jvmSignatureDiagnostics = if (skipJvmSignatureDiagnostics)
            emptySet()
        else
            computeJvmSignatureDiagnostics(bindingContext)

        val ok = booleanArrayOf(true)
        val withNewInference = newInferenceEnabled && Directive.WITH_NEW_INFERENCE in directives && !USE_OLD_INFERENCE_DIAGNOSTICS_FOR_NI
        val diagnostics = ContainerUtil.filter(
            CheckerTestUtil.getDiagnosticsIncludingSyntaxErrors(
                bindingContext,
                implementingModulesBindings,
                ktFile,
                Directive.MARK_DYNAMIC_CALLS in directives,
                dynamicCallDescriptors,
                newInferenceEnabled
            ) + jvmSignatureDiagnostics,
            { whatDiagnosticsToConsider.value(it.diagnostic) }
        )

        actualDiagnostics.addAll(diagnostics)

        val uncheckedDiagnostics = mutableListOf<PositionalTextDiagnostic>()
        val inferenceCompatibilityOfTest = asInferenceCompatibility(withNewInference)
        val invertedInferenceCompatibilityOfTest = asInferenceCompatibility(!withNewInference)

        val diagnosticToExpectedDiagnostic = CheckerTestUtil.diagnosticsDiff(diagnosedRanges, diagnostics, object : CheckerTestUtil.DiagnosticDiffCallbacks {
            override fun missingDiagnostic(diagnostic: CheckerTestUtil.TextDiagnostic, expectedStart: Int, expectedEnd: Int) {
                if (Directive.WITH_NEW_INFERENCE in directives && diagnostic.inferenceCompatibility != inferenceCompatibilityOfTest) {
                    updateUncheckedDiagnostics(diagnostic, expectedStart, expectedEnd)
                    return
                }

                val message = "Missing " + diagnostic.description + PsiDiagnosticUtils.atLocation(
                    ktFile,
                    TextRange(expectedStart, expectedEnd)
                )
                System.err.println(message)
                ok[0] = false
            }

            override fun wrongParametersDiagnostic(
                expectedDiagnostic: CheckerTestUtil.TextDiagnostic,
                actualDiagnostic: CheckerTestUtil.TextDiagnostic,
                start: Int,
                end: Int
            ) {
                val message = "Parameters of diagnostic not equal at position " +
                        PsiDiagnosticUtils.atLocation(ktFile, TextRange(start, end)) +
                        ". Expected: ${expectedDiagnostic.asString()}, actual: $actualDiagnostic"
                System.err.println(message)
                ok[0] = false
            }

            override fun unexpectedDiagnostic(diagnostic: CheckerTestUtil.TextDiagnostic, actualStart: Int, actualEnd: Int) {
                if (Directive.WITH_NEW_INFERENCE in directives && diagnostic.inferenceCompatibility != inferenceCompatibilityOfTest) {
                    updateUncheckedDiagnostics(diagnostic, actualStart, actualEnd)
                    return
                }

                val message = "Unexpected ${diagnostic.description}${PsiDiagnosticUtils.atLocation(
                    ktFile,
                    TextRange(actualStart, actualEnd)
                )}"
                System.err.println(message)
                ok[0] = false
            }

            fun updateUncheckedDiagnostics(diagnostic: CheckerTestUtil.TextDiagnostic, start: Int, end: Int) {
                diagnostic.enhanceInferenceCompatibility(invertedInferenceCompatibilityOfTest)
                uncheckedDiagnostics.add(PositionalTextDiagnostic(diagnostic, start, end))
            }
        })

        actualText.append(CheckerTestUtil.addDiagnosticMarkersToText(
            ktFile, diagnostics, diagnosticToExpectedDiagnostic, { file -> file.text }, uncheckedDiagnostics, Directive.WITH_NEW_INFERENCE in directives)
        )

        stripExtras(actualText)

        return ok[0]
    }

    private fun asInferenceCompatibility(isNewInference: Boolean): CheckerTestUtil.TextDiagnostic.InferenceCompatibility {
        return if (isNewInference)
            CheckerTestUtil.TextDiagnostic.InferenceCompatibility.NEW
        else
            CheckerTestUtil.TextDiagnostic.InferenceCompatibility.OLD
    }

    private fun computeJvmSignatureDiagnostics(bindingContext: BindingContext): Set<CheckerTestUtil.ActualDiagnostic> {
        val jvmSignatureDiagnostics = HashSet<CheckerTestUtil.ActualDiagnostic>()
        val declarations = PsiTreeUtil.findChildrenOfType(ktFile, KtDeclaration::class.java)
        for (declaration in declarations) {
            val diagnostics = getJvmSignatureDiagnostics(declaration, bindingContext.diagnostics,
                                                         GlobalSearchScope.allScope(project)) ?: continue
            jvmSignatureDiagnostics.addAll(diagnostics.forElement(declaration).map {
                CheckerTestUtil.ActualDiagnostic(
                    it,
                    null,
                    newInferenceEnabled
                )
            })
        }
        return jvmSignatureDiagnostics
    }

    override fun toString(): String = ktFile?.name ?: "Java file"
}