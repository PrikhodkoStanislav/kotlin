import java.util.regex.Pattern
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import java.util.regex.Matcher

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    testCompile(projectTests(":compiler"))
}

sourceSets {
    "main" { }
    "test" { projectDefault() }
}

projectTest {
    workingDir = rootDir
}

val generateTests by generator("org.jetbrains.kotlin.generators.tests.GenerateCompilerSpecTestsKt")

val printSpecTestsStatistic by smartJavaExec {
    main = "org.jetbrains.kotlin.tasks.PrintSpecTestsStatisticKt"
}

val generateJsonTestsMap by smartJavaExec {
    classpath = javaPluginConvention().sourceSets.getByName("test").runtimeClasspath
    main = "org.jetbrains.kotlin.tasks.GenerateJsonTestsMapKt"
}
