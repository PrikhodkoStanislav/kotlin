// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 1
 DESCRIPTION: Using equality with expressions as implies argument.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26178
 TODO
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: equality with negative number as implies argument
fun case_1(x: Any?): Boolean {
    contract {
        returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>-.15f<!>)
    }
    return x !is Number
}

// CASE DESCRIPTION: equality with string concatenations as implies argument
fun case_2(x: Any?): Boolean {
    contract {
        returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>"..." + "."<!>)
    }
    return x !is Number
}

// CASE DESCRIPTION: equality between two arguments
fun case_3(x: Any?, y: Any?): Boolean {
    contract {
        returns(true) implies (<!ERROR_IN_CONTRACT_DESCRIPTION!>x == y<!>)
    }
    return x !is Number
}

// CASE DESCRIPTION: equality with call expression
fun case_4(x: Any?, y: Any?): Boolean {
    contract {
        returns(true) implies (x == <!ERROR_IN_CONTRACT_DESCRIPTION!>y.toString()<!>)
    }
    return x !is Number
}
