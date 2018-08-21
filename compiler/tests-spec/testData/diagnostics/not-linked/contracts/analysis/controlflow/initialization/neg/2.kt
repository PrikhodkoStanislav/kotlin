// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 2
 DESCRIPTION: Nested val/var wrong assignments or uninitialized usages based on 'call in place' effect with wrong invocation kind
 */

// CASE DESCRIPTION: reassignment and uninitialized usage inside nested calls in place contract functions: 'at least once', 'at most once' (reassignment) / 'exactly once' (uninitialized usage)
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

// CASE DESCRIPTION: uninitialized usage (init in 'at most onces' calls in place contract function) inside nested calls in place contract functions: 'at most once', 'at least once'
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

// CASE DESCRIPTION: uninitialized usage (init in 'at most once' calls in place contract function) inside nested calls in place contract functions: 'at least once', 'exactly once'
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

// CASE DESCRIPTION: uninitialized usage (init in 'unknown' calls in place contract function) inside nested calls in place contract functions: 'at least once', 'at least once'
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
