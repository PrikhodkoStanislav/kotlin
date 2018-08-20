// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 3
 DESCRIPTION: Using invert type checking operator in function contract (returns effect) and invert condition ('not' operator).
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26176
 */

import kotlin.internal.contracts.*

fun case_1_funWithContract(x: Any?): Boolean {
    contract {
        returns(true) implies (x !is Number)
    }
    return x !is Number
}

// CASE DESCRIPTION: with not null Number expected type
fun case_1(t: Any?) {
    if (!case_1_funWithContract(t)) {
        println(t.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) // no smartcast
    }
}

fun case_2_funWithContract(x: Any?): Boolean {
    contract {
        returns(true) implies (x !is Number?)
    }
    return x !is Number?
}

// CASE DESCRIPTION: with nullable Number expected type
fun case_2(t: Any?) {
    if (!case_2_funWithContract(t)) {
        println(t?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) // no smartcast
    }
}
