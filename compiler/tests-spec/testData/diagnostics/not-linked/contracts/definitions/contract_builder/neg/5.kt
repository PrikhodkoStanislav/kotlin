// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE -UNUSED_PARAMETER -UNREACHABLE_CODE -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 5
 DESCRIPTION: Contract isn't first statement.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: assignment statement before contract description
fun case_1(): Boolean {
    contract { returns(true) implies (<!TYPE_MISMATCH, ERROR_IN_CONTRACT_DESCRIPTION!>-10<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_2(): Boolean {
    contract { returnsNotNull() implies (return return <!RETURN_TYPE_MISMATCH!>return<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_3(): Boolean {
    contract { returns(false) implies (<!TYPE_MISMATCH, ERROR_IN_CONTRACT_DESCRIPTION!>"..." + "$<!UNRESOLVED_REFERENCE!>value_1<!>"<!>) }
    return true
}

/*
 CASE DESCRIPTION: expression before contract description
 UNEXPECTED BEHAVIOUR
 */
fun case_4(): Boolean {
    contract { returns(null) implies throw Exception() }
    return true
}

/*
 CASE DESCRIPTION: expression before contract description
 NOTE: check for indirect recursion
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26386
 */
//fun case_5(): Boolean? {
//    contract { returns(null) implies case_5() }
//    return null
//}

// CASE DESCRIPTION: expression before contract description
fun case_6(): Boolean? {
    contract { returns(null) implies <!TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH, ERROR_IN_CONTRACT_DESCRIPTION!>listOf(0)<!> }
    return null
}

// CASE DESCRIPTION: expression before contract description
fun case_7(value_1: Boolean): Boolean? {
    contract { returns(null) implies <!TYPE_MISMATCH, ERROR_IN_CONTRACT_DESCRIPTION!>contract { returns(null) implies (!value_1) }<!> }
    return null
}
