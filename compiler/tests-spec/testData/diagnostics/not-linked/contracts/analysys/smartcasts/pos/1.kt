// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 3
 DESCRIPTION: Smartcast using returns effect with type checking conditions.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1_require(cond: Boolean) {
    contract {
        returns() implies (cond)
    }
    if (!cond) throw Exception()
}
fun case_1() {
    val value: Any? = "sf"
    case_1_require(value is String)
    println(value.length)
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_2_require(value: Any?) {
    contract {
        returns() implies (value is String)
    }
    if (value !is String) throw Exception()
}
fun case_2() {
    val value: Any? = "sf"
    case_2_require(value)
    println(value.length)
}
