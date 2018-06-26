/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing

import org.jetbrains.kotlin.test.SpecTestValidationException
import org.junit.Assert

open class AbstractParsingTestSpec : AbstractParsingTest() {
    override fun doParsingTest(filePath: String) {
        try {

        } catch (e: SpecTestValidationException) {
            Assert.fail(e.reason.description)
        }

        super.doParsingTest(filePath)
    }
}