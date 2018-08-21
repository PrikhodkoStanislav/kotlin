// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 2
 DESCRIPTION: nested val/var init and usage
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1() {
    val value: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_2() {
    val value: Int

    funWithAtMostOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_3() {
    var value: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_4() {
    var value: Int

    funWithAtMostOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_7() {
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value = 1
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_8() {
    var value: Int

    funWithUnknownCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            funWithAtLeastOnceCallsInPlace {
                value = 1
            }
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
            funWithAtLeastOnceCallsInPlace {
                value.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
            funWithUnknownCallsInPlace {
                value.inc()
            }
        }
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_9() {
    var value: Int

    funWithAtMostOnceCallsInPlace {
        funWithUnknownCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                value = 1
            }
            funWithAtLeastOnceCallsInPlace {
                value.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
            funWithUnknownCallsInPlace {
                value.inc()
            }
        }
    }
}

