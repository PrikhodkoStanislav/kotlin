// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 9
 DESCRIPTION: Check smartcasts after non-null assertions or assignment in lambdas with contract and 'exactly once' or 'at least once' CallsInPlace effects.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26148
 */

// CASE KEYWORDS: notNullAssertion, exactlyOnce, callsInPlace
fun case_1(arg: Int?) {
    funWithExactlyOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE KEYWORDS: notNullAssertion, atLeastOnce, callsInPlace
fun case_2(arg: Int?) {
    funWithAtLeastOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE KEYWORDS: notNullAssignment, notNullSmartCast, exactlyOnce, callsInPlace
fun case_3() {
    val value_1: Boolean?

    funWithExactlyOnceCallsInPlace { value_1 = false }

    value_1<!UNSAFE_CALL!>.<!>not()
}

// CASE KEYWORDS: notNullAssignment, notNullSmartCast, atLeastOnce, callsInPlace
fun case_4() {
    val value_1: Boolean?

    funWithAtLeastOnceCallsInPlace { <!VAL_REASSIGNMENT!>value_1<!> = true }

    value_1<!UNSAFE_CALL!>.<!>not()
}
