/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import org.jetbrains.kotlin.test.ConfigurationKind
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

private enum class TestType(val type: String) {
    POSITIVE("pos"),
    NEGATIVE("neg");

    companion object {
        private val map = TestType.values().associateBy(TestType::type)
        fun fromValue(type: String) = map[type]
    }
}

private data class TestInfo(
    val sectionNumber: String,
    val sectionName: String,
    val paragraph: Int,
    val sentence: Int,
    val testType: TestType
)

abstract class AbstractDiagnosticsTestSpec : AbstractDiagnosticsTest() {
    private val testInfoRegex =
        "^.*?/s(?<sectionNumber>(?:\\d+)(?:\\.\\d+)*)_(?<sectionName>[\\w-]+)/p(?<paragraph>\\d+)s(?<sentence>\\d+)_(?<testType>pos|neg)\\.kt$"

    private val specUrl = "file:///Users/victor/IdeaProjects/kotlin-spec/.pages/index.html"

    override fun getConfigurationKind(): ConfigurationKind {
        return ConfigurationKind.ALL
    }

    private fun getTestInfo(testInfoMatcher: Matcher): TestInfo {
        return TestInfo(
            testInfoMatcher.group("sectionNumber"),
            testInfoMatcher.group("sectionName"),
            testInfoMatcher.group("paragraph").toInt(),
            testInfoMatcher.group("sentence").toInt(),
            TestType.fromValue(testInfoMatcher.group("testType"))!!
        )
    }

    private fun getHumanReadableSectionName(sectionName: String): String {
        return sectionName.replace("-", " ")
    }

    override fun analyzeAndCheck(testDataFile: File, files: List<TestFile>) {
        val testInfoMatcher = Pattern.compile(testInfoRegex).matcher(testDataFile.path)

        assertTrue(
            "Incorrect test filename or folder name.\n" +
                    "It must match the following path pattern: " +
                    "testsSpec/s<sectionNumber>_<sectionName>/p<paragraph>s<sentence>_<pos|neg>.kt " +
                    "(example: testsSpec/s16.30_WhenExpression/p1s1_pos.kt)", testInfoMatcher.find()
        )

        val testInfo = getTestInfo(testInfoMatcher)

        println(
            "SPEC TEST IS RUNNING [${testInfo.testType}]: ${testInfo.sectionNumber} ${getHumanReadableSectionName(testInfo.sectionName)} " +
                    "(paragraph: ${testInfo.paragraph}, sentence: ${testInfo.sentence}), $specUrl#${testInfo.sectionName}:${testInfo.paragraph}:${testInfo.sentence}"
        )

        super.analyzeAndCheck(testDataFile, files)
    }
}
