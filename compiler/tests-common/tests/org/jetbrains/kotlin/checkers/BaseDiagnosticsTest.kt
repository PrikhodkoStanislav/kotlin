/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.checkers

import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.test.KotlinTestUtils
import java.io.File
import kotlin.reflect.jvm.javaField

internal const val JVM_TARGET = "JVM_TARGET"

abstract class BaseDiagnosticsTest : KotlinMultiFileTestWithJava<TestModule, TestFile>() {
    protected lateinit var environment: KotlinCoreEnvironment

    protected val project by lazy { environment.project }

    override fun tearDown() {
        this::environment.javaField!![this] = null
        super.tearDown()
    }

    override fun createTestModule(name: String): TestModule =
            TestModule(name)

    override fun createTestFile(module: TestModule?, fileName: String, text: String, directives: Map<Directive, String>): TestFile =
            TestFile(module, fileName, text, directives)

    override fun doMultiFileTest(
            file: File,
            modules: @JvmSuppressWildcards Map<String, ModuleAndDependencies>,
            testFiles: List<TestFile>
    ) {
        for (moduleAndDependencies in modules.values) {
            moduleAndDependencies.module.getDependencies().addAll(moduleAndDependencies.dependencies.map { name ->
                modules[name]?.module ?: error("Dependency not found: $name for module ${moduleAndDependencies.module.name}")
            })
        }

        environment = createEnvironment(file)

        testFiles.forEach { it.project = environment.project }

        analyzeAndCheck(file, testFiles)
    }

    protected abstract fun analyzeAndCheck(testDataFile: File, files: List<TestFile>)

    protected open fun getKtFiles(testFiles: List<TestFile>, includeExtras: Boolean): List<KtFile> {
        val declareFlexibleType = testFiles.any { Directive.EXPLICIT_FLEXIBLE_TYPES in it.directives }
        val declareCheckType = testFiles.any { Directive.CHECK_TYPE in it.directives }
        val ktFiles = testFiles.mapNotNull { it.ktFile }.toMutableList()

        if (includeExtras) {
            if (declareFlexibleType) {
                ktFiles.add(KotlinTestUtils.createFile("EXPLICIT_FLEXIBLE_TYPES.kt", EXPLICIT_FLEXIBLE_TYPES_DECLARATIONS, project))
            }
            if (declareCheckType) {
                ktFiles.add(KotlinTestUtils.createFile("CHECK_TYPE.kt", CHECK_TYPE_DECLARATIONS, project))
            }
        }

        println(ktFiles)

        return ktFiles
    }

    companion object {
        private val EXPLICIT_FLEXIBLE_TYPES_DECLARATIONS = "\npackage " + TestFile.EXPLICIT_FLEXIBLE_PACKAGE +
                "\npublic class " + TestFile.EXPLICIT_FLEXIBLE_CLASS_NAME + "<L, U>"
        private val CHECK_TYPE_DECLARATIONS = "\npackage " + TestFile.CHECK_TYPE_PACKAGE +
                "\nfun <T> checkSubtype(t: T) = t" +
                "\nclass Inv<T>" +
                "\nfun <E> Inv<E>._() {}" +
                "\ninfix fun <T> T.checkType(f: Inv<T>.() -> Unit) {}"

        val DEFAULT_DIAGNOSTIC_TESTS_FEATURES = mapOf(
            LanguageFeature.Coroutines to LanguageFeature.State.ENABLED
        )

        // Change it to "true" to load diagnostics for old inference to test new inference (ignore diagnostics with <NI; prefix)
        val USE_OLD_INFERENCE_DIAGNOSTICS_FOR_NI = false
    }
}
