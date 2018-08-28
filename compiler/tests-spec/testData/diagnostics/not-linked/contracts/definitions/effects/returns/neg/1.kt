// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 1
 DESCRIPTION: Using equality with expressions in implies.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26178
 */

import kotlin.internal.contracts.*

fun case_1(x: Any?): Boolean {
    contract { returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>-.15f<!>) }
    return x !is Number
}

fun case_2(x: Any?): Boolean {
    contract { returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>"..." + "."<!>) }
    return x !is Number
}

fun case_3(x: Int, y: Int): Boolean {
    contract { returns(true) implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>x > y<!>) }
    return x > y
}

fun case_4(x: Any?, y: Any?): Boolean {
    contract { returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>y.toString()<!>) }
    return x !is Number
}
