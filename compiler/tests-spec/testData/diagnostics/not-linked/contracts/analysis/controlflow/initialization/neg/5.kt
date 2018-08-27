// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 5
 DESCRIPTION: Calls in place contract functions with forbidden lamdas ...
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    reassignment:val
    uninitialized:val
    smartInit:val
    smartcast:inited
 */
fun case_1() {
    val value_1: Int

    funWithExactlyOnceCallsInPlace({ <!CAPTURED_VAL_INITIALIZATION!>value_1<!> = 10 })

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:AtLeastOnce
    uninitialized:var
    smartInit:var
    smartcast:inited
    lambda
 */
fun case_2() {
    var value_1: Int
    val l = { value_1 = 10 }

    funWithAtLeastOnceCallsInPlace(l)

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:AtLeastOnce
    uninitialized:var
    smartInit:var
    smartcast:inited
    fun:literal
 */
fun case_3() {
    var value_1: Int
    val l = fun () { value_1 = 10 }

    funWithAtLeastOnceCallsInPlace(l)

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:AtLeastOnce
    uninitialized:var
    smartInit:var
    smartcast:inited
    fun:literal
 */
fun case_4() {
    var value_1: Int

    funWithAtLeastOnceCallsInPlace(fun () { value_1 = 10 })

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    uninitialized:val
    reassignment:val
    smartInit:var
    smartcast:inited
    fun
    object:reflection
 */
fun case_5() {
    val value_1: Int
    val o = object {
        fun l() { <!CAPTURED_VAL_INITIALIZATION!>value_1<!> = 10 }
    }

    funWithExactlyOnceCallsInPlace(o::l)

    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}
