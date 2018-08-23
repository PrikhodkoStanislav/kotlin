// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 10
 DESCRIPTION: Check the lack of smartcasts after non-null assertions or assignment in lambdas with contract and 'at most once' or 'unknown' CallsInPlace effects.
 */

// CASE KEYWORDS: notNullAssertion, atMostOnce, callsInPlace
fun case_1(arg: Int?) {
    funWithAtMostOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE KEYWORDS: notNullAssertion, unknown, callsInPlace
fun case_2(arg: Int?) {
    funWithUnknownCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE KEYWORDS: notNullAssignment, notNullSmartCast, atMostOnce, callsInPlace
fun case_3() {
    val value: Boolean?

    funWithAtMostOnceCallsInPlace { value = false }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}

// CASE KEYWORDS: notNullAssignment, notNullSmartCast, unknown, callsInPlace
fun case_4() {
    val value: Boolean?

    funWithUnknownCallsInPlace { <!VAL_REASSIGNMENT!>value<!> = true }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}
