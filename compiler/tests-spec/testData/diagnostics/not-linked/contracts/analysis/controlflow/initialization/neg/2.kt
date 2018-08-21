// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 2
 DESCRIPTION: nested val/var init and usage
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1() {
    val value: Int

    funWithAtLeastOnceCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            <!VAL_REASSIGNMENT!>value<!> = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value<!>.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_2() {
    val value: Int

    funWithAtMostOnceCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value<!>.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_3() {
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            value = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value<!>.inc()
        }
        value.inc()
    }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_4() {
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        funWithUnknownCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value<!>.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
}
