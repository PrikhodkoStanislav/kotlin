/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.utils

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
    val testType: TestType,
    val sectionNumber: String,
    val sectionName: String,
    val paragraphNumber: Int,
    val sentenceNumber: Int,
    val sentence: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TestInfo) return false

        return this.testType == other.testType && this.sectionNumber == other.sectionNumber && this.paragraphNumber == other.paragraphNumber && this.sentenceNumber == other.sentenceNumber
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

enum class SpecTestValidationFailedReason(val description: String) {
    FILENAME_NOT_VALID(
        "Incorrect test filename or folder name.\n" +
                "It must match the following path pattern: " +
                "testsSpec/s<sectionNumber>_<sectionName>/p<paragraph>s<sentence>_<pos|neg>.kt " +
                "(example: testsSpec/s16.30:when-expression/1:1-pos.kt)"
    ),
    METAINFO_NOT_VALID("Incorrect meta info in test file."),
    FILENAME_AND_METAINFO_NOT_CONSISTENCY("Test info from filename and file content is not consistency")
}

class SpecTestValidationException(val reason: SpecTestValidationFailedReason) : Exception()

object SpecTestUtil {
    const val specUrl = "https://petukhovvictor.github.io/kotlin-spec"

    private const val integerRegex = "[1-9]\\d*"
    private const val testPathRegex =
        "^.*?/s(?<sectionNumber>(?:$integerRegex)(?:\\.$integerRegex)*):(?<sectionName>[\\w-]+)/(?<paragraphNumber>$integerRegex):(?<sentenceNumber>$integerRegex)-(?<testType>pos|neg)\\.kt$"
    private const val testContentMetaInfoRegex =
        "\\/\\*\\s+KOTLIN SPEC TEST \\((?<testType>POSITIVE|NEGATIVE)\\)\\s+SECTION (?<sectionNumber>(?:$integerRegex)(?:\\.$integerRegex)*):\\s*(?<sectionName>.*?)\\s+PARAGRAPH\\s*(?<paragraphNumber>$integerRegex)\\s+SENTENCE\\s*(?<sentenceNumber>$integerRegex):\\s*(?<sentence>.*?)\\s+\\*\\/\\s+"

    private fun getTestInfo(testInfoMatcher: Matcher, directMappedTestTypeEnum: Boolean = false, withSentence: Boolean = false): TestInfo {
        return TestInfo(
            if (directMappedTestTypeEnum)
                TestType.valueOf(testInfoMatcher.group("testType")) else
                TestType.fromValue(testInfoMatcher.group("testType"))!!,
            testInfoMatcher.group("sectionNumber"),
            testInfoMatcher.group("sectionName"),
            testInfoMatcher.group("paragraphNumber").toInt(),
            testInfoMatcher.group("sentenceNumber").toInt(),
            if (withSentence) testInfoMatcher.group("sentence") else null
        )
    }

    private fun getTestFileWithoutMetaInfo(testInfoByContentMatcher: Matcher): File {
        val testFileContentWithoutMetaInfo = testInfoByContentMatcher.replaceAll("")
        val tempFile = createTempFile()
        tempFile.writeText(testFileContentWithoutMetaInfo)

        return tempFile
    }

    private fun parseTestInfo(testDataFile: File): Pair<TestInfo, TestInfo> {
        val testInfoByFilenameMatcher = Pattern.compile(testPathRegex).matcher(testDataFile.path)

        if (!testInfoByFilenameMatcher.find()) {
            throw SpecTestValidationException(SpecTestValidationFailedReason.FILENAME_NOT_VALID)
        }

        val testInfoByContentMatcher = Pattern.compile(testContentMetaInfoRegex).matcher(testDataFile.readText())

        if (!testInfoByContentMatcher.find()) {
            throw SpecTestValidationException(SpecTestValidationFailedReason.METAINFO_NOT_VALID)
        }

        val testInfoByFilename = getTestInfo(testInfoByFilenameMatcher)
        val testInfoByContent = getTestInfo(testInfoByContentMatcher, withSentence = true, directMappedTestTypeEnum = true)

        if (testInfoByFilename != testInfoByContent) {
            throw SpecTestValidationException(SpecTestValidationFailedReason.FILENAME_AND_METAINFO_NOT_CONSISTENCY)
        }

        return Pair(testInfoByFilename, testInfoByContent)
    }

    fun printSpecTestInfo(testDataFile: File) {
        val (testInfoByFilename, testInfoByContent) = parseTestInfo(testDataFile)

        println("-------------------------")
        println("DIAGNOSTIC SPEC TEST IS RUNNING [${testInfoByFilename.testType}]")
        println("SECTION: ${testInfoByFilename.sectionNumber} ${testInfoByContent.sectionName} (paragraph: ${testInfoByFilename.paragraphNumber}, sentence: ${testInfoByFilename.sentenceNumber})")
        println("SENTENCE: ${testInfoByContent.sentence}")
        println("URL: $specUrl#${testInfoByFilename.sectionName}:${testInfoByFilename.paragraphNumber}:${testInfoByFilename.sentenceNumber}")
    }
}