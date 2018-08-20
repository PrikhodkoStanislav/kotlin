// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 1
 DESCRIPTION: Check smartcasts after non-null assertions or assignment in lambdas with contract and 'exactly once' or 'at least once' CallsInPlace effects.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26148
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1(arg: Int?) {
    funWithExacltyOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'at least once' CallsInPlace effect.
fun case_2(arg: Int?) {
    funWithAtLeastOnceCallsInPlace { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with not-null assignment and 'exactly once' CallsInPlace effect.
fun case_3() {
    val value: Boolean?

    funWithExacltyOnceCallsInPlace { value = false }

    value<!UNSAFE_CALL!>.<!>not()
}

// CASE DESCRIPTION: lambdas with not-null assignment and 'at least once' CallsInPlace effect.
fun case_4() {
    val value: Boolean?

    funWithAtLeastOnceCallsInPlace { <!VAL_REASSIGNMENT!>value<!> = true }

    value<!UNSAFE_CALL!>.<!>not()
}
