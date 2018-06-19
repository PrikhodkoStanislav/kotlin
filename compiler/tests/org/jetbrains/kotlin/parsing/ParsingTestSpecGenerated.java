/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parsing;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/psi/testsSpec")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ParsingTestSpecGenerated extends AbstractParsingTestSpec {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doParsingTest, TargetBackend.ANY, testDataFilePath);
    }

    public void testAllFilesPresentInTestsSpec() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/psi/testsSpec"), Pattern.compile("^(.*)\\.kts?$"), TargetBackend.ANY, true);
    }

    @TestMetadata("compiler/testData/psi/testsSpec/s16.30:when-expression")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class S16_30_when_expression extends AbstractParsingTestSpec {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doParsingTest, TargetBackend.ANY, testDataFilePath);
        }

        @TestMetadata("2:3-pos.kt")
        public void test2_3_pos() throws Exception {
            runTest("compiler/testData/psi/testsSpec/s16.30:when-expression/2:3-pos.kt");
        }

        public void testAllFilesPresentInS16_30_when_expression() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/psi/testsSpec/s16.30:when-expression"), Pattern.compile("^(.*)\\.kts?$"), TargetBackend.ANY, true);
        }
    }
}
