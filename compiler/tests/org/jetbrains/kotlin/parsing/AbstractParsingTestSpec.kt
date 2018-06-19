/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing

import org.jetbrains.kotlin.utils.SpecTestUtil
import org.jetbrains.kotlin.utils.SpecTestValidationException
import org.junit.Assert
import java.io.File

open class AbstractParsingTestSpec : AbstractParsingTest() {
    override fun doParsingTest(filePath: String) {
        try {
            SpecTestUtil.printSpecTestInfo(File(filePath))
        } catch (e: SpecTestValidationException) {
            Assert.fail(e.reason.description)
        }

        super.doParsingTest(filePath)
    }
}