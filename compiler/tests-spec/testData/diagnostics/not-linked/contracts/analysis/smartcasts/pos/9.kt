// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 9
 DESCRIPTION: Smartcast using some returns effects on the same values.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T> T?.case_1_funWithContract(value1: Int?, value2: Boolean): Boolean {
    contract {
        returns(true) implies (value1 != null)
        returns(false) implies (value2)
    }

    return value1 != null
}
fun case_1(value1: Int?, value2: Any?) {
    if (!value1.case_1_funWithContract(value1, value2 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    } else {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
    }
}
