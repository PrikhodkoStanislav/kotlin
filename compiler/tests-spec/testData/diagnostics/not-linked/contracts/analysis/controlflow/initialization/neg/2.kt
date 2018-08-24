// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 2
 DESCRIPTION: Nested val/var wrong assignments or uninitialized usages based on 'call in place' effect with wrong invocation kind
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,exactlyOnce,atLeastOnce
        nested
    reassignment:val
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
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

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,unknown,atLeastOnce
        nested
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
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

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atMostOnce,atLeastOnce
        nested
    uninitialized:var
    smartInit:var
    smartcast:inited
 */
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

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:unknown,exactlyOnce,atLeastOnce
        nested
    uninitialized:var
    smartInit:var
    smartcast:inited
 */
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
