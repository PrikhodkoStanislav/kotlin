/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.spec

import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

enum class FutureSpecTestFileInfoElementType(
    override val valuePattern: Pattern? = null,
    override val required: Boolean = false
) : SpecTestInfoElementType {
    SECTION(
        valuePattern = Pattern.compile("""XX.XX (?<name>.*?)"""),
        required = true
    ),
    CATEGORY(
        valuePattern = Pattern.compile("""\w+(,\s+\w+)*"""),
        required = true
    ),
    NUMBER(required = true),
    DESCRIPTION(required = true),
    ISSUES(valuePattern = SpecTestFileInfoElementType.ISSUES.valuePattern),
    UNEXPECTED_BEHAVIOUR,
    DISCUSSION,
    NOTE
}

class FutureSpecTest(
    testArea: TestArea,
    testType: TestType,
    sectionName: String,
    val categories: List<String>,
    testNumber: Int,
    description: String? = null,
    cases: List<SpecTestCase>? = null,
    unexpectedBehavior: Boolean? = null,
    issues: Set<String>? = null
) : AbstractSpecTest(testArea, testType, sectionName, testNumber, description, cases, unexpectedBehavior, issues) {
    override fun checkConsistency(other: AbstractSpecTest) =
        if (other is FutureSpecTest) checkConsistency(other) else false

    private fun checkConsistency(other: FutureSpecTest): Boolean {
        return this.testArea == other.testArea
                && this.testType == other.testType
                && this.categories.joinToString(",") == other.categories.joinToString(",")
                && this.testNumber == other.testNumber
    }
}

class FutureSpecTestValidator(
    private val testDataFile: File,
    private val testArea: TestArea
) : AbstractSpecTestValidator<FutureSpecTest>(testDataFile, testArea) {
    override val testPathPattern: Pattern =
        Pattern.compile(testPathRegexTemplate.format(pathPartRegex, filenameRegex))
    override val testInfoPattern: Pattern =
        Pattern.compile(MULTILINE_COMMENT_REGEX.format("""KOTLIN $testAreaRegex FUTURE SPEC TEST \($testTypeRegex\)\n(?<infoElements>[\s\S]*?\n)"""))

    companion object {
        val pathPartRegex =
            """(?<futureSpecTestMarker>s-xx\.xx_(?<sectionName>[\w-]+)/(?<categories>(?:\w+)(?:/\w+)*?))"""
        val filenameRegex = """(?<testNumber>$INTEGER_REGEX)\.kt"""
    }

    override fun getTestInfo(
        testInfoMatcher: Matcher,
        testInfoElements: SpecTestInfoElements<SpecTestInfoElementType>,
        testCases: List<SpecTestCase>,
        unexpectedBehavior: Boolean,
        issues: Set<String>?
    ): FutureSpecTest {
        val sectionMatcher = testInfoElements[FutureSpecTestFileInfoElementType.SECTION]!!.additionalMatcher!!

        return FutureSpecTest(
            TestArea.valueOf(testInfoMatcher.group("testArea").toUpperCase()),
            TestType.valueOf(testInfoMatcher.group("testType")),
            sectionMatcher.group("name"),
            testInfoElements[FutureSpecTestFileInfoElementType.CATEGORY]!!.content.split(Regex(""",\s*""")),
            testInfoElements[FutureSpecTestFileInfoElementType.NUMBER]!!.content.toInt(),
            testInfoElements[FutureSpecTestFileInfoElementType.DESCRIPTION]!!.content,
            testCases,
            unexpectedBehavior,
            issues
        )
    }

    override fun getTestInfo(testInfoMatcher: Matcher) = FutureSpecTest(
        TestArea.valueOf(testInfoMatcher.group("testArea").toUpperCase()),
        TestType.fromValue(testInfoMatcher.group("testType"))!!,
        testInfoMatcher.group("sectionName"),
        testInfoMatcher.group("categories").split("/"),
        testNumber = testInfoMatcher.group("testNumber").toInt()
    )

    override fun parseTestInfo() = parseTestInfo(FutureSpecTestFileInfoElementType.values())

    override fun printTestInfo() {
        println("--------------------------------------------------")
        if (testInfoByContent.unexpectedBehavior!!)
            println("(!!!) HAS UNEXPECTED BEHAVIOUR (!!!)")
        println("$testArea ${testInfoByFilename.testType} FUTURE SPEC TEST")
        println("SECTION: ${testInfoByContent.sectionName}")
        println("CATEGORIES: ${testInfoByContent.categories}")
        println("TEST NUMBER: ${testInfoByContent.testNumber}")
        println("NUMBER OF TEST CASES: ${testInfoByContent.cases!!.size}")
        println("DESCRIPTION: ${testInfoByContent.description}")
        if (testInfoByContent.issues!!.isNotEmpty()) {
            println("LINKED ISSUES: ${testInfoByContent.issues!!.joinToString(", ")}")
        }
    }

    override fun getSingleTestCase(testInfoElements: SpecTestInfoElements<SpecTestInfoElementType>) = SpecTestCase(
        1,
        description = testInfoElements[FutureSpecTestFileInfoElementType.DESCRIPTION]!!.content,
        unexpectedBehavior = testInfoElements.contains(FutureSpecTestFileInfoElementType.UNEXPECTED_BEHAVIOUR),
        issues = parseIssues(testInfoElements[FutureSpecTestFileInfoElementType.ISSUES])
    )
}