// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 4
 DESCRIPTION: Calls in place contract functions with name shadowing and wrong invocation kind of calls in place effect
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nameShadowing:val
    uninitialized:val
 */
fun case_1() {
    val value_1: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!> = 10
        value_1.inc()
    }

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nameShadowing:val
        nested
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
fun case_2() {
    val value_1: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!>: Int
        funWithExactlyOnceCallsInPlace {
            value_1 = 10
        }
        funWithAtLeastOnceCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }
    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,atLeastOnce
        nameShadowing:val
        nested
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
fun case_3() {
    val value_1: Int

    funWithAtLeastOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!>: Int
        funWithAtMostOnceCallsInPlace {
            value_1 = 10
        }
        funWithAtMostOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
        }
        value_1.inc()
    }
    funWithAtMostOnceCallsInPlace {
        value_1 = 10
    }
    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,atLeastOnce,exactlyOnce
        nameShadowing:var,val
        nested
    uninitialized:var
    smartInit:val,var
    smartcast:inited
 */
fun case_6() {
    var value_1: Int

    funWithAtLeastOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!>: Int
        funWithExactlyOnceCallsInPlace {
            value_1 = 10
        }
        funWithAtMostOnceCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    funWithAtMostOnceCallsInPlace {
        value_1 = 1
    }

    <!UNINITIALIZED_VARIABLE!>value_1<!>.dec()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,unknown
        nameShadowing:val,var
        nested
    uninitialized:val
    §
    smartInit:val,var
    smartcast:inited
 */
fun case_7() {
    val value_1: Int

    funWithUnknownCallsInPlace {
        var <!NAME_SHADOWING!>value_1<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value_1 = 10
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    funWithUnknownCallsInPlace {
        <!VAL_REASSIGNMENT!>value_1<!> = 1
    }

    <!UNINITIALIZED_VARIABLE!>value_1<!>.dec()
}
