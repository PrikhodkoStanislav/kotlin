// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE -UNUSED_PARAMETER -UNREACHABLE_CODE -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 2
 DESCRIPTION: Contract isn't first statement.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: assignment statement before contract description
fun case_1(value1: Boolean): Boolean {
    contract { returns(true) implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>value1 == true<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_2(value1: Boolean): Boolean {
    contract { returnsNotNull() implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>value1 != false<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_3(value1: String): Boolean {
    contract { returns(false) implies (value1 != "") }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_4(value1: Int): Boolean? {
    contract { returns(null) implies (value1 == 0) }
    return null
}
