// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 2
 DESCRIPTION: Using equality with literals as implies argument.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26178
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: equality with float as implies argument
fun case_1(x: Any?): Boolean {
    contract {
        returns(true) implies (x == .15f)
    }
    return x !is Number
}

// CASE DESCRIPTION: equality with string as implies argument
fun case_2(x: Any?): Boolean {
    contract {
        returns(true) implies (x == "...")
    }
    return x !is Number
}

// CASE DESCRIPTION: equality with char as implies argument
fun case_3(x: Any?): Boolean {
    contract {
        returns(true) implies (x == '-')
    }
    return x !is Number
}
