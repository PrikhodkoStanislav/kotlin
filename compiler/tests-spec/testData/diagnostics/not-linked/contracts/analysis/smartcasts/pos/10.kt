// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 10
 DESCRIPTION: Smartcast using some returns effects on the same values.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1_funWithContract(value1: Number, value2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value2 is Boolean?)
        returns(false) implies (value1 is Int)
        returnsNotNull() implies (block != null)
    }

    return <!SENSELESS_COMPARISON!>value1 != null<!>
}
fun case_3(value1: Number, value2: Any?, value3: (() -> Unit)?) {
    if (case_1_funWithContract(value1, value2, value3) == true) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(false)
    } else if (case_1_funWithContract(value1, value2, value3) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    } else if (case_1_funWithContract(value1, value2, value3) != null) {
        <!DEBUG_INFO_SMARTCAST!>value3<!>()
    }
}