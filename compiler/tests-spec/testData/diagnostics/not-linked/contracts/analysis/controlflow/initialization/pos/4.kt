// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 4
 DESCRIPTION: Calls in place contract functions with name shadowing
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nameShadowing:val
 */
fun case_1() {
    val <!UNUSED_VARIABLE!>value_1<!>: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!> = 10
        value_1.inc()
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce
        nameShadowing:val
        nested
    smartInit:val
    smartcast:inited
 */
fun case_2() {
    val <!UNUSED_VARIABLE!>value_1<!>: Int

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
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce,atMostOnce
        nameShadowing:val
        nested
    smartInit:val
    smartcast:inited
 */
fun case_3() {
    val value_1: Int

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
    funWithExactlyOnceCallsInPlace {
        value_1 = 10
    }
    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,unknown,atMostOnce
        nameShadowing:val
        nested
    smartInit:val
    smartcast:inited
 */
fun case_4() {
    val value_1: Int

    funWithAtMostOnceCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!>: Int
        funWithExactlyOnceCallsInPlace {
            value_1 = 10
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }
    funWithExactlyOnceCallsInPlace {
        value_1 = 10
    }
    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,unknown,atMostOnce
        nameShadowing:val
        nested
    smartInit:val
    smartcast:inited
 */
fun case_5() {
    val value_1: Int

    funWithUnknownCallsInPlace {
        val <!NAME_SHADOWING!>value_1<!>: Int
        funWithExactlyOnceCallsInPlace {
            value_1 = 10
        }
        funWithAtMostOnceCallsInPlace {
            value_1.inc()
        }
    }
    funWithExactlyOnceCallsInPlace {
        value_1 = 10
    }
    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce,atMostOnce
        nameShadowing:var,val
        nested
    smartInit:var,val
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

    funWithAtLeastOnceCallsInPlace {
        value_1 = 1
    }

    value_1.dec()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce,unknown
        nameShadowing:val,var
        nested
    smartInit:var,val
    smartcast:inited
 */
fun case_7() {
    val value_1: Int

    funWithAtLeastOnceCallsInPlace {
        var <!NAME_SHADOWING!>value_1<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value_1 = 10
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    funWithExactlyOnceCallsInPlace {
        value_1 = 1
    }

    value_1.dec()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
        nameShadowing:var
        nested
    smartInit:var
    smartcast:inited
 */
fun case_8() {
    var value_1: Int

    funWithAtLeastOnceCallsInPlace {
        var <!NAME_SHADOWING!>value_1<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value_1 = 10
        }
        funWithAtLeastOnceCallsInPlace {
            value_1.inc()
        }
        value_1++
    }

    funWithAtLeastOnceCallsInPlace {
        value_1 = 1
    }

    value_1--
}


