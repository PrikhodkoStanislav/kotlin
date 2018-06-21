/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing

import org.jetbrains.kotlin.test.SpecTestUtil
import org.jetbrains.kotlin.test.SpecTestValidationException
import org.jetbrains.kotlin.test.TestArea
import org.junit.Assert
import java.io.File

open class AbstractParsingTestSpec : AbstractParsingTest() {
    override fun doParsingTest(filePath: String) {
        try {
            SpecTestUtil.printTestInfo(File(filePath), TestArea.PARSING)
        } catch (e: SpecTestValidationException) {
            Assert.fail(e.reason.description)
        }

        super.doParsingTest(filePath)
    }
}