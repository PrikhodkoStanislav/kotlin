// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 1
 DESCRIPTION: Simple val/var wrong assignments or usages (uninitialized variable) based on 'call in place' effect with wrong invocation kind
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
    reassignment:val
    smartInit:val
    smartcast:inited
 */
fun case_1() {
    val value: Int

    funWithAtLeastOnceCallsInPlace { <!VAL_REASSIGNMENT!>value<!> = 10 }

    value.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
fun case_2() {
    val value: Int

    funWithAtMostOnceCallsInPlace { value = 10 }

    <!UNINITIALIZED_VARIABLE!>value<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:unknown
    reassignment:val
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
fun case_3() {
    val value: Int

    funWithUnknownCallsInPlace { <!VAL_REASSIGNMENT!>value<!> = 10 }

    <!UNINITIALIZED_VARIABLE!>value<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,unknown
    uninitialized:var
    smartInit:var
    smartcast:inited
 */
fun case_4() {
    var value1: Int
    var value2: Int

    funWithAtMostOnceCallsInPlace { value1 = 10 }
    funWithUnknownCallsInPlace { value2 = 10 }

    <!UNINITIALIZED_VARIABLE!>value1<!>.dec()
    <!UNINITIALIZED_VARIABLE!>value2<!>.div(10)
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,unknown,atLeastOnce
    reassignment:val
    uninitialized:val,var
    smartInit:val,var
    class:
        fields:init,uninitialized
        init
 */
class case_5 {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val value1: Int<!>
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val value2: Int<!>
    val value3: Int
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>var value4: Int<!>
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>var value5: Int<!>

    init {
        funWithAtMostOnceCallsInPlace { value1 = 1 }
        funWithUnknownCallsInPlace { <!VAL_REASSIGNMENT!>value2<!> = 1 }
        funWithAtLeastOnceCallsInPlace { <!VAL_REASSIGNMENT!>value3<!> = 1 }
        funWithAtMostOnceCallsInPlace { value4 = 2 }
        funWithUnknownCallsInPlace { value5 = 3 }
    }
}
