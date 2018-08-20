// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 2
 DESCRIPTION: Check the lack of smartcasts after non-null assertions or assignment in lambdas with contract and 'at most once' or 'unknown' CallsInPlace effects.
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'at most once' CallsInPlace effect.
fun case_1(arg: Int?) {
    funWithAtMostOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'unknown' CallsInPlace effect.
fun case_2(arg: Int?) {
    funWithUnknownCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'at most once' CallsInPlace effect.
fun case_3() {
    val value: Boolean?

    funWithAtMostOnceCallsInPlace { value = false }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'unknown' CallsInPlace effect.
fun case_4() {
    val value: Boolean?

    funWithUnknownCallsInPlace { <!VAL_REASSIGNMENT!>value<!> = true }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}
