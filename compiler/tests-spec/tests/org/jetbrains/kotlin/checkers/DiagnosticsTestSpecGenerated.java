/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers;

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
@TestMetadata("compiler/tests-spec/testData/diagnostics")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class DiagnosticsTestSpecGenerated extends AbstractDiagnosticsTestSpec {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
    }

    public void testAllFilesPresentInDiagnostics() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true, "_helpers");
    }

    @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Not_linked extends AbstractDiagnosticsTestSpec {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
        }

        public void testAllFilesPresentInNot_linked() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Contracts extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInContracts() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Analysys extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                public void testAllFilesPresentInAnalysys() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }

                @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts")
                @TestDataPath("$PROJECT_ROOT")
                @RunWith(JUnit3RunnerWithInners.class)
                public static class Smartcasts extends AbstractDiagnosticsTestSpec {
                    private void runTest(String testDataFilePath) throws Exception {
                        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                    }

                    public void testAllFilesPresentInSmartcasts() throws Exception {
                        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                    }

                    @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts/neg")
                    @TestDataPath("$PROJECT_ROOT")
                    @RunWith(JUnit3RunnerWithInners.class)
                    public static class Neg extends AbstractDiagnosticsTestSpec {
                        private void runTest(String testDataFilePath) throws Exception {
                            KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                        }

                        @TestMetadata("1.kt")
                        public void test1() throws Exception {
                            runTest("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts/neg/1.kt");
                        }

                        @TestMetadata("2.kt")
                        public void test2() throws Exception {
                            runTest("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts/neg/2.kt");
                        }

                        public void testAllFilesPresentInNeg() throws Exception {
                            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts/analysys/smartcasts/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                        }
                    }
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts/descriptions")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Descriptions extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                public void testAllFilesPresentInDescriptions() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts/descriptions"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }

                @TestMetadata("compiler/tests-spec/testData/diagnostics/not-linked/contracts/descriptions/pos")
                @TestDataPath("$PROJECT_ROOT")
                @RunWith(JUnit3RunnerWithInners.class)
                public static class Pos extends AbstractDiagnosticsTestSpec {
                    private void runTest(String testDataFilePath) throws Exception {
                        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                    }

                    @TestMetadata("1.kt")
                    public void test1() throws Exception {
                        runTest("compiler/tests-spec/testData/diagnostics/not-linked/contracts/descriptions/pos/1.kt");
                    }

                    public void testAllFilesPresentInPos() throws Exception {
                        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/not-linked/contracts/descriptions/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                    }
                }
            }
        }
    }

    @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class S_16_30_when_expression extends AbstractDiagnosticsTestSpec {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
        }

        public void testAllFilesPresentInS_16_30_when_expression() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_11 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_11() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/1.2.kt");
                }

                @TestMetadata("3.1.kt")
                public void test3_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/3.1.kt");
                }

                @TestMetadata("6.1.kt")
                public void test6_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/6.1.kt");
                }

                @TestMetadata("7.1.kt")
                public void test7_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/7.1.kt");
                }

                @TestMetadata("8.1.kt")
                public void test8_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/8.1.kt");
                }

                @TestMetadata("8.2.kt")
                public void test8_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/8.2.kt");
                }

                @TestMetadata("8.3.kt")
                public void test8_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg/8.3.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/1.2.kt");
                }

                @TestMetadata("1.3.kt")
                public void test1_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/1.3.kt");
                }

                @TestMetadata("3.1.kt")
                public void test3_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/3.1.kt");
                }

                @TestMetadata("6.1.kt")
                public void test6_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/6.1.kt");
                }

                @TestMetadata("7.1.kt")
                public void test7_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/7.1.kt");
                }

                @TestMetadata("8.1.kt")
                public void test8_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/8.1.kt");
                }

                @TestMetadata("8.2.kt")
                public void test8_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/8.2.kt");
                }

                @TestMetadata("8.3.kt")
                public void test8_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos/8.3.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-11/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_2 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_2() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("3.1.kt")
                public void test3_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2/pos/3.1.kt");
                }

                @TestMetadata("3.2.kt")
                public void test3_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2/pos/3.2.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-2/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_3 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_3() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/neg/1.1.kt");
                }

                @TestMetadata("2.1.kt")
                public void test2_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/neg/2.1.kt");
                }

                @TestMetadata("2.2.kt")
                public void test2_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/neg/2.2.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos/1.1.kt");
                }

                @TestMetadata("2.1.kt")
                public void test2_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos/2.1.kt");
                }

                @TestMetadata("2.2.kt")
                public void test2_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos/2.2.kt");
                }

                @TestMetadata("2.3.kt")
                public void test2_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos/2.3.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-3/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_5 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_5() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/neg/1.1.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/pos/1.1.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-5/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_6 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_6() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/neg/1.1.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/pos/1.1.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-6/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_7 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_7() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/1.2.kt");
                }

                @TestMetadata("3.1.kt")
                public void test3_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/3.1.kt");
                }

                @TestMetadata("3.2.kt")
                public void test3_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/3.2.kt");
                }

                @TestMetadata("5.1.kt")
                public void test5_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/5.1.kt");
                }

                @TestMetadata("5.2.kt")
                public void test5_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/5.2.kt");
                }

                @TestMetadata("7.1.kt")
                public void test7_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg/7.1.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/1.2.kt");
                }

                @TestMetadata("1.3.kt")
                public void test1_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/1.3.kt");
                }

                @TestMetadata("1.4.kt")
                public void test1_4() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/1.4.kt");
                }

                @TestMetadata("3.1.kt")
                public void test3_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/3.1.kt");
                }

                @TestMetadata("3.2.kt")
                public void test3_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/3.2.kt");
                }

                @TestMetadata("5.1.kt")
                public void test5_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/5.1.kt");
                }

                @TestMetadata("5.2.kt")
                public void test5_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/5.2.kt");
                }

                @TestMetadata("7.1.kt")
                public void test7_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos/7.1.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-7/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }

        @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class P_9 extends AbstractDiagnosticsTestSpec {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
            }

            public void testAllFilesPresentInP_9() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Neg extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg/1.2.kt");
                }

                @TestMetadata("1.3.kt")
                public void test1_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg/1.3.kt");
                }

                @TestMetadata("1.4.kt")
                public void test1_4() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg/1.4.kt");
                }

                public void testAllFilesPresentInNeg() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/neg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }

            @TestMetadata("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos")
            @TestDataPath("$PROJECT_ROOT")
            @RunWith(JUnit3RunnerWithInners.class)
            public static class Pos extends AbstractDiagnosticsTestSpec {
                private void runTest(String testDataFilePath) throws Exception {
                    KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
                }

                @TestMetadata("1.1.kt")
                public void test1_1() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos/1.1.kt");
                }

                @TestMetadata("1.2.kt")
                public void test1_2() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos/1.2.kt");
                }

                @TestMetadata("1.3.kt")
                public void test1_3() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos/1.3.kt");
                }

                @TestMetadata("1.4.kt")
                public void test1_4() throws Exception {
                    runTest("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos/1.4.kt");
                }

                public void testAllFilesPresentInPos() throws Exception {
                    KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/tests-spec/testData/diagnostics/s-16.30_when-expression/p-9/pos"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
                }
            }
        }
    }
}
