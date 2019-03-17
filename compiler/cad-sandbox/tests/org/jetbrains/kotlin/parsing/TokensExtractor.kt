/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing

import org.jetbrains.kotlin.lexer.AbstractLexerTest
import org.jetbrains.kotlin.lexer.KotlinLexer
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.TargetBackend
import java.io.File

class TokensExtractor : AbstractLexerTest(KotlinLexer()) {
    companion object {
        private const val SOURCES_PATH = "compiler/cad-sandbox/sources"
    }

    fun testRun() {
        val filesSequence = File(SOURCES_PATH).walkTopDown().filter { it.extension == "kt" }
        val filesNumber = filesSequence.count()
        var filesCounter = 1
        val unparsedFiles = mutableListOf<String>()

        filesSequence.forEach {
            val relativeTestFilePath = it.relativeTo(File(SOURCES_PATH))

            try {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, it.path)
            } catch (e: RuntimeException) {
                print("TOKENS BUILD EXCEPTION: ${e.message}")
                println("\t\tFor file: ${relativeTestFilePath.path}")
                unparsedFiles.add(relativeTestFilePath.path)
            }

            filesCounter++
        }

        println("=====================================================")
        if (unparsedFiles.isNotEmpty()) {
            println("UNPARSED FILES:")
            unparsedFiles.forEach { println("\t$it") }
            println("=====================================================")
        }
        println("SUCCESSFUL PARSED: ${filesNumber - unparsedFiles.size}, FAILURE PARSED: ${unparsedFiles.size}")
        println("=====================================================")
    }
}
