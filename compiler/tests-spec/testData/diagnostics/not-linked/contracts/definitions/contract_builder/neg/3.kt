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
fun case_1(value1: Boolean?): Boolean {
    contract { returns(true) implies (value1 != null && <!ERROR_IN_CONTRACT_DESCRIPTION!>value1 == false<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_2(value1: Boolean, value2: Boolean): Boolean {
    contract { returnsNotNull() implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>value1 != false<!> || value2) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_3(value1: String?, value2: Boolean): Boolean {
    contract { returns(false) implies (value1 != null && <!ERROR_IN_CONTRACT_DESCRIPTION!>value2 != true<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_4(value1: Nothing?, value2: Boolean?): Boolean {
    contract { returns(null) implies (<!SENSELESS_COMPARISON!><!DEBUG_INFO_CONSTANT!>value1<!> == null<!> || value2 != null || <!ERROR_IN_CONTRACT_DESCRIPTION!><!DEBUG_INFO_CONSTANT!>value2<!> == false<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_5(value1: Any?, value2: String?): Boolean {
    contract { returns(null) implies (value1 != null && value2 != null || value2 == ".") }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_6(value1: Boolean, value2: Int?): Boolean {
    contract { returns(null) implies (value2 == null && value1 || value2 == 0) }
    return true
}
