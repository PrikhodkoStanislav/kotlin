
import java.io.File
import java.util.regex.Pattern
import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

jvmTarget = "1.6"

val compilerModules: Array<String> by rootProject.extra
val otherCompilerModules = compilerModules.filter { it != path }

val effectSystemEnabled: Boolean by rootProject.extra
val newInferenceEnabled: Boolean by rootProject.extra

configureFreeCompilerArg(effectSystemEnabled, "-Xeffect-system")
configureFreeCompilerArg(newInferenceEnabled, "-Xnew-inference")

fun configureFreeCompilerArg(isEnabled: Boolean, compilerArgument: String) {
    if (isEnabled) {
        allprojects {
            tasks.withType<KotlinCompile<*>> {
                kotlinOptions {
                    freeCompilerArgs += listOf(compilerArgument)
                }
            }
        }
    }
}

val depDistProjects = listOf(
        ":kotlin-script-runtime",
        ":kotlin-stdlib",
        ":kotlin-test:kotlin-test-jvm"
)

// TODO: it seems incomplete, find and add missing dependencies
val testDistProjects = listOf(
        "", // for root project
        ":kotlin-stdlib:jvm-minimal-for-test",
        ":kotlin-compiler",
        ":kotlin-script-runtime",
        ":kotlin-stdlib",
        ":kotlin-stdlib-jre7",
        ":kotlin-stdlib-jre8",
        ":kotlin-stdlib-js",
        ":kotlin-reflect",
        ":kotlin-test:kotlin-test-jvm",
        ":kotlin-test:kotlin-test-junit",
        ":kotlin-test:kotlin-test-js",
        ":kotlin-preloader",
        ":plugins:android-extensions-compiler",
        ":kotlin-ant",
        ":kotlin-annotations-jvm",
        ":kotlin-annotations-android"
)

val testJvm6ServerRuntime by configurations.creating
val antLauncherJar by configurations.creating

dependencies {
    testRuntime(intellijDep()) // Should come before compiler, because of "progarded" stuff needed for tests

    depDistProjects.forEach {
        testCompile(projectDist(it))
    }
    testCompile(commonDep("junit:junit"))
    testCompileOnly(projectDist(":kotlin-test:kotlin-test-jvm"))
    testCompileOnly(projectDist(":kotlin-test:kotlin-test-junit"))
    testCompile(projectTests(":compiler:tests-common"))
    testCompile(projectTests(":generators:test-generator"))
    testCompile(project(":compiler:ir.ir2cfg"))
    testCompile(project(":compiler:ir.tree")) // used for deepCopyWithSymbols call that is removed by proguard from the compiler TODO: make it more straightforward
    testCompile(project(":kotlin-scripting-compiler"))
    testCompile(project(":kotlin-scripting-misc"))
    testCompile(project(":kotlin-script-util"))
    testCompileOnly(projectRuntimeJar(":kotlin-daemon-client"))
    testCompileOnly(project(":kotlin-reflect-api"))
    otherCompilerModules.forEach {
        testCompileOnly(project(it))
    }
    testCompileOnly(intellijCoreDep()) { includeJars("intellij-core") }
    testCompileOnly(intellijDep()) { includeJars("openapi", "idea", "idea_rt", "util", "asm-all") }

    testRuntime(projectDist(":kotlin-reflect"))
    testRuntime(projectDist(":kotlin-daemon-client"))
    testRuntime(androidDxJar())
    testRuntime(files(toolsJar()))

    testJvm6ServerRuntime(projectTests(":compiler:tests-common-jvm6"))

    antLauncherJar(commonDep("org.apache.ant", "ant"))
    antLauncherJar(files(toolsJar()))
}

sourceSets {
    "main" {}
    "test" {
        projectDefault()
        // not yet ready
//        java.srcDir("tests-ir-jvm/tests")
    }
}

val jar: Jar by tasks
jar.from("../idea/src") {
    include("META-INF/extensions/compiler.xml")
}

projectTest {
    dependsOn(*testDistProjects.map { "$it:dist" }.toTypedArray())
    workingDir = rootDir
    systemProperty("kotlin.test.script.classpath", the<JavaPluginConvention>().sourceSets.getByName("test").output.classesDirs.joinToString(File.pathSeparator))
    doFirst {
        systemProperty("kotlin.ant.classpath", antLauncherJar.asPath)
        systemProperty("kotlin.ant.launcher.class", "org.apache.tools.ant.Main")
    }
}

fun Project.codegenTest(target: Int, jvm: Int,
                        jdk: String = "JDK_${if (jvm <= 8) "1" else ""}$jvm",
                        body: Test.() -> Unit): Test = projectTest("codegenTarget${target}Jvm${jvm}Test") {
    dependsOn(*testDistProjects.map { "$it:dist" }.toTypedArray())
    workingDir = rootDir

    filter.includeTestsMatching("org.jetbrains.kotlin.codegen.BlackBoxCodegenTestGenerated*")
    filter.includeTestsMatching("org.jetbrains.kotlin.codegen.BlackBoxInlineCodegenTestGenerated*")
    filter.includeTestsMatching("org.jetbrains.kotlin.codegen.CompileKotlinAgainstInlineKotlinTestGenerated*")
    filter.includeTestsMatching("org.jetbrains.kotlin.codegen.CompileKotlinAgainstKotlinTestGenerated*")
    filter.includeTestsMatching("org.jetbrains.kotlin.codegen.BlackBoxAgainstJavaCodegenTestGenerated*")

    if (jdk == "JDK_9") {
        jvmArgs = listOf("--add-opens", "java.desktop/javax.swing=ALL-UNNAMED", "--add-opens", "java.base/java.io=ALL-UNNAMED")
    }
    body()
    doFirst {
        val jdkPath = project.findProperty(jdk) ?: error("$jdk is not optional to run this test")
        executable = "$jdkPath/bin/java"
        println("Running test with $executable")
    }
    group = "verification"
}

codegenTest(target = 6, jvm = 6, jdk = "JDK_18") {
    dependsOn(testJvm6ServerRuntime)

    val port = project.findProperty("kotlin.compiler.codegen.tests.port")?.toString() ?: "5100"
    var jdkProcess: Process? = null

    doFirst {
        logger.info("Configuring JDK 6 server...")
        val jdkPath = project.findProperty("JDK_16") ?: error("JDK_16 is not optional to run this test")
        val executable = "$jdkPath/bin/java"
        val main = "org.jetbrains.kotlin.test.clientserver.TestProcessServer"
        val classpath = testJvm6ServerRuntime.asPath

        logger.debug("Server classpath: $classpath")

        val builder = ProcessBuilder(executable, "-cp", classpath, main, port)
        builder.directory(rootDir)

        builder.inheritIO()
        builder.redirectErrorStream(true)

        logger.info("Starting JDK 6 server $executable")
        jdkProcess = builder.start()

    }
    systemProperty("kotlin.test.default.jvm.target", "1.6")
    systemProperty("kotlin.test.java.compilation.target", "1.6")
    systemProperty("kotlin.test.box.in.separate.process.port", port)

    doLast {
        logger.info("Stopping JDK 6 server...")
        jdkProcess?.destroy()
    }
}

codegenTest(target = 6, jvm = 9) {
    systemProperty("kotlin.test.default.jvm.target", "1.6")
}

codegenTest(target = 8, jvm = 8) {
    systemProperty("kotlin.test.default.jvm.target", "1.8")
}

codegenTest(target = 8, jvm = 9) {
    systemProperty("kotlin.test.default.jvm.target", "1.8")
}

codegenTest(target = 9, jvm = 9) {
    systemProperty("kotlin.test.default.jvm.target", "1.8")
    systemProperty("kotlin.test.substitute.bytecode.1.8.to.1.9", "true")
}

codegenTest(target = 10, jvm = 10) {
    systemProperty("kotlin.test.default.jvm.target", "1.8")
    systemProperty("kotlin.test.substitute.bytecode.1.8.to.10", "true")
}

val generateTests by generator("org.jetbrains.kotlin.generators.tests.GenerateCompilerTestsKt")

testsJar()

task("printSpecTestStatistic") {
    val testDataDir = "testData"
    val specTestsDir = "testsSpec"
    val specTestAreas = listOf("diagnostics", "psi", "codegen")
    val integerRegex = "[1-9]\\d*"
    val testPathRegex =
        "s-(?<sectionNumber>(?:$integerRegex)(?:\\.$integerRegex)*)_(?<sectionName>[\\w-]+)/p-(?<paragraphNumber>$integerRegex)/(?<testType>pos|neg)/(?<sentenceNumber>$integerRegex)\\.(?<testNumber>$integerRegex)\\.kt$"

    abstract class StatElement {
        var counter = 0
        abstract val elements: MutableMap<out Any, out StatElement>?
        abstract fun increment()
    }

    class TestTypeStat(private val paragraph: StatElement): StatElement() {
        override val elements = null
        override fun increment() {
            counter++
            paragraph.increment()
        }
    }

    class ParagraphStat(private val section: StatElement): StatElement() {
        override val elements: MutableMap<String, TestTypeStat>? = sortedMapOf()
        override fun increment() {
            counter++
            section.increment()
        }
    }

    class SectionStat(private val area: StatElement): StatElement() {
        override val elements: MutableMap<Int, ParagraphStat>? = sortedMapOf()
        override fun increment() {
            counter++
            area.increment()
        }
    }

    class AreaStat: StatElement() {
        override val elements: MutableMap<String, SectionStat>? = sortedMapOf()
        override fun increment() {
            counter++
        }
    }

    fun printStat(statistic: MutableMap<out Any, out StatElement>, depth: Int = 0) {
        statistic.forEach {
            for (i in 1..depth) print("    ")

            var statKey = it.key

            statKey = if (statKey is String) statKey.toUpperCase() else statKey

            println("$statKey: ${it.value.counter}")

            if (it.value.elements != null) {
                printStat(it.value.elements!!, depth + 1)
            }
        }
    }

    fun incrementStatCounters(testAreaStats: AreaStat, sectionName: String, paragraphNumber: Int, testType: String) {
        val section = testAreaStats.elements!!.computeIfAbsent(sectionName) { SectionStat(testAreaStats) }
        val paragraph = section.elements!!.computeIfAbsent(paragraphNumber) { ParagraphStat(section) }

        paragraph.elements!!.computeIfAbsent(testType) { TestTypeStat(paragraph) }.increment()
    }

    println("--------------------------------------------------")
    println("SPEC TESTS STATISTIC")
    println("--------------------------------------------------")

    val statistic: MutableMap<String, AreaStat> = mutableMapOf()

    specTestAreas.forEach {
        val specTestType = it
        val specTestsPath = "$testDataDir/$specTestType/$specTestsDir"

        statistic[specTestType] = AreaStat()

        File(specTestsPath).walkTopDown().forEach areaTests@{
            if (!it.isFile || it.extension != "kt") {
                return@areaTests
            }

            val testInfoMatcher = Pattern.compile(testPathRegex).matcher(it.path)

            if (!testInfoMatcher.find()) {
                return@areaTests
            }

            val sectionNumber = testInfoMatcher.group("sectionNumber")
            val sectionName = testInfoMatcher.group("sectionName")
            val paragraphNumber = testInfoMatcher.group("paragraphNumber").toInt()
            val testType = testInfoMatcher.group("testType")
            val section = "$sectionNumber $sectionName"

            incrementStatCounters(statistic[specTestType]!!, section, paragraphNumber, testType)
        }
    }

    printStat(statistic)

    println("--------------------------------------------------")
}
