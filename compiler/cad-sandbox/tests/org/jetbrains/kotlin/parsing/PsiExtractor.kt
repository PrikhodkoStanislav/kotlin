/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.intellij.psi.PsiElement
import junit.framework.AssertionFailedError
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.TargetBackend
import java.io.File

class PsiExtractor : AbstractParsingTest() {
    companion object {
        private const val SOURCES_PATH = "compiler/cad-sandbox/sources"
    }

    private fun psi2json(psiElement: PsiElement, jsonElement: JsonObject): JsonObject {
        jsonElement.addProperty("type", psiElement.node.elementType.toString())

        if (psiElement.children.isNotEmpty()) {
            JsonArray().apply {
                psiElement.children.forEach {
                    add(psi2json(it, JsonObject()))
                }
                jsonElement.add("children", this)
            }
        }

        return jsonElement
    }

    override fun doParsingTest(filePath: String) {
        try {
            super.doParsingTest(filePath)
        } catch (e: AssertionFailedError) {} finally {
            val json = psi2json(myFile, JsonObject())
            val sourceFile = File(filePath)
            val baseName = "${sourceFile.parent}/${sourceFile.nameWithoutExtension}"

            File("$baseName.json").writeText(json.toString())
            File("$baseName.txt").delete()
        }
    }

    fun testRun() {
        val filesSequence = File(SOURCES_PATH).walkTopDown().filter { it.extension == "kt" }
        val filesNumber = filesSequence.count()
        var filesCounter = 1
        val unparsedFiles = mutableListOf<String>()
        val psiGeneratedMessage = { path: String -> println("PSI GENERATED ($filesCounter out of $filesNumber): $path") }

        filesSequence.forEach {
            val relativeTestFilePath = it.relativeTo(File(SOURCES_PATH))

            try {
                KotlinTestUtils.runTest(this::doParsingTest, TargetBackend.ANY, it.path)
                psiGeneratedMessage(relativeTestFilePath.path)
            } catch (e: RuntimeException) {
                print("PSI BUILD EXCEPTION: ${e.message}")
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