// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 3
 DESCRIPTION: Smartcast using returns effect with simple type checking conditions.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1_funWithContract(value1: Any?, value2: Any?) {
    contract {
        returns() implies (value1 is String && value2 is Number)
    }
    if (value1 !is String || value2 !is Number) throw Exception()
}
fun case_1(value1: Any?, value2: Any?) {
    case_1_funWithContract(value1, value2)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_2_funWithContract(value1: Any?, value2: Any?) {
    contract {
        returns() implies (value1 is String && value2 is Number)
    }
    if (value1 !is String || value2 !is Number) throw Exception()
}
fun case_2(value1: Any?, value2: Any?) {
    case_2_funWithContract(value1, value2)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_3_funWithContract(value1: Any?, value2: Any?) {
    contract {
        returns() implies (value1 is String && value2 == null)
    }
    if (value1 !is String || value2 != null) throw Exception()
}
fun case_3(value1: Any?, value2: Any?) {
    case_3_funWithContract(value1, value2)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_4_funWithContract(value1: Any?, value2: Any?) {
    contract {
        returns() implies (value1 is String && value2 == null)
    }
    if (value1 !is String || value2 != null) throw Exception()
}
fun case_4(value1: Any?, value2: Number?) {
    case_4_funWithContract(value1, value2)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
}

/*
 CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
 UNEXPECTED BEHAVIOUR: in the commented last line
 ISSUES: KT-26300
 */
fun case_4_funWithContract(value1: Any?, value2: Any?, value3: Any?, value4: Any?) {
    contract {
        returns() implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)
    }
    if (!(value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)) throw Exception()
}
class case_4_class {
    val prop_1: Int? = 10
    fun case_4(value1: Any?, value2: Number?) {
        val o = case_4_class()
        case_4_funWithContract(value1, value2, o.prop_1, this.prop_1)
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
        println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
//        println(this.prop_1.plus(3))
    }
}
