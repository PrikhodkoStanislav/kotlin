/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import org.jetbrains.kotlin.test.ConfigurationKind
import org.jetbrains.kotlin.utils.SpecTestUtil.printSpecTestInfo
import org.jetbrains.kotlin.utils.SpecTestValidationException
import org.junit.Assert
import java.io.File

abstract class AbstractDiagnosticsTestSpec : AbstractDiagnosticsTest() {
    override fun getConfigurationKind(): ConfigurationKind {
        return ConfigurationKind.ALL
    }

    override fun analyzeAndCheck(testDataFile: File, files: List<TestFile>) {
        try {
            printSpecTestInfo(testDataFile)
        } catch (e: SpecTestValidationException) {
            Assert.fail(e.reason.description)
        }

        super.analyzeAndCheck(testDataFile, files)
    }
}
