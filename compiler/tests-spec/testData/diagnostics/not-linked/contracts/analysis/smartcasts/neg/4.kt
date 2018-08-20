// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 4
 DESCRIPTION: Using equality with null in function contract (returns effect) and invert condition ('not' operator).
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26176
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: contract function with direct condition in implies
fun case_1_funWithContract(number: Int?): Boolean {
    contract {
        returns(true) implies (number == null)
    }

    return number == null
}
fun case_1(number: Int?) {
    if (!case_1_funWithContract(number)) {
        number<!UNSAFE_CALL!>.<!>inc() // nullable receiver
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_2_funWithContract(number: Int?): Boolean {
    contract {
        returns(false) implies (number != null)
    }

    return number == null
}
fun case_2(number: Int?) {
    if (!case_1_funWithContract(number)) {
        number<!UNSAFE_CALL!>.<!>inc()
    }
}
