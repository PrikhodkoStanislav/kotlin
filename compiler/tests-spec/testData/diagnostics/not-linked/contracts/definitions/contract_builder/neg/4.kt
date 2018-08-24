// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE -UNUSED_PARAMETER -UNREACHABLE_CODE -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 4
 DESCRIPTION: Contract isn't first statement.
 */

import kotlin.internal.contracts.*

/*
 CASE DESCRIPTION: assignment statement before contract description
 UNEXPECTED BEHAVIOUR
 */
fun case_1(): Boolean {
    contract { returns(true) implies true }
    return true
}

// CASE DESCRIPTION: assignment statement before contract description
fun case_2(): Boolean {
    contract { returns(true) implies (true || false) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_3(): Boolean {
    contract { returnsNotNull() implies (<!NULL_FOR_NONNULL_TYPE!>null<!>) }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_4(): Boolean {
    contract { returns(false) implies <!CONSTANT_EXPECTED_TYPE_MISMATCH!>0.000001<!> }
    return true
}

// CASE DESCRIPTION: expression before contract description
fun case_5(): Boolean? {
    contract { returns(null) implies <!TYPE_MISMATCH!>""<!> }
    return null
}
