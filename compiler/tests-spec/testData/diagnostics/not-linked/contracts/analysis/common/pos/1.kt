// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, common
 NUMBER: 1
 DESCRIPTION: Smartcast using some returns effects on the same values.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun case_1_funWithContract(value1: Int?, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
        returns(true) implies (value1 != null)
    }

    block()

    return value1 != null
}
fun case_1(value1: Int?) {
    var value3: Int
    if (case_1_funWithContract(value1) { value3 = 10 }) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        println(value3)
    } else {
        println(value3)
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun <T> T?.case_2_funWithContract(value1: Int?, value2: Boolean, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returns(true) implies (value1 != null)
        returns(false) implies (!value2)
    }

    block()

    return value1 != null
}
fun case_2(value1: Int?, value2: Any?) {
    val value3: Int
    if (value1.case_2_funWithContract(value1, value2 is String) { value3 = 10 }) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        println(value3)
    } else {
        println(value3)
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun <T> T?.case_3_funWithContract(value1: Int?, value2: Any?, block: () -> Unit): Boolean? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returnsNotNull() implies (value1 != null)
        returns(false) implies (value2 is Boolean?)
        returns(false) implies (this@case_3_funWithContract != null)
    }

    block()

    return value1 != null
}
fun case_3(value1: Int?, value2: Any?) {
    var value3: Int
    if (value1.case_3_funWithContract(value1, value2) { value3 = 10 } != null) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        println(value3)
    } else {
        if (value1.case_3_funWithContract(value1, value2) { value3 = 10 } == false) {
            println(<!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(true))
            println(value3)
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
        }
    }
}
