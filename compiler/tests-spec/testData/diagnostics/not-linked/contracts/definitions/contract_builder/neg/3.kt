// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE -UNUSED_PARAMETER -UNREACHABLE_CODE -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 3
 DESCRIPTION: Contract isn't first statement.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: assignment statement before contract description
fun case_1(value_1: Boolean?): Boolean {
    contract { returns(true) implies (value_1 != null && <!ERROR_IN_CONTRACT_DESCRIPTION!>value_1 == false<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_2(value_1: Boolean, value_2: Boolean): Boolean {
    contract { returnsNotNull() implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>value_1 != false<!> || value_2) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_3(value_1: String?, value_2: Boolean): Boolean {
    contract { returns(false) implies (value_1 != null && <!ERROR_IN_CONTRACT_DESCRIPTION!>value_2 != true<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_4(value_1: Nothing?, value_2: Boolean?): Boolean {
    contract { returns(null) implies (<!SENSELESS_COMPARISON!><!DEBUG_INFO_CONSTANT!>value_1<!> == null<!> || value_2 != null || <!ERROR_IN_CONTRACT_DESCRIPTION!><!DEBUG_INFO_CONSTANT!>value_2<!> == false<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_5(value_1: Any?, value_2: String?): Boolean {
    contract { returns(null) implies (value_1 != null && value_2 != null || value_2 == ".") }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_6(value_1: Boolean, value_2: Int?): Boolean {
    contract { returns(null) implies (value_2 == null && value_1 || value_2 == 0) }
    return true
}
