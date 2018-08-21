// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 5
 DESCRIPTION: Using equality with null in function contract (returns effect) and invert condition ('not' operator).
 */

fun case_1(value1: Any?, value2: Any?, value3: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String? || value2 !is Number && value3 !is Float)
    println(value1!!.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
}

fun case_2(value1: Any?, value2: Any?, value3: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String || value2 !is Number || <!USELESS_IS_CHECK!>value3 !is Any?<!>)
    println(value1!!.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
}