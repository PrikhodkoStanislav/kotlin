// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 3
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions as implies parameter in contract definition.
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
        returns() implies (value1 is String && value2 == null)
    }
    if (value1 !is String || value2 != null) throw Exception()
}
fun case_2(value1: Any?, value2: Any?) {
    case_2_funWithContract(value1, value2)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_3_funWithContract(value1: Any?, value2: Any?, value3: Any?, value4: Any?) {
    contract {
        returns() implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)
    }
    if (!(value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)) throw Exception()
}
class case_3_class {
    val prop_1: Int? = 10
    fun case_3(value1: Any?, value2: Number?) {
        val o = case_3_class()
        case_3_funWithContract(value1, value2, o.prop_1, this.prop_1)
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
        println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_4_funWithContract_1(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(true) implies (value1 is String && value2 is Number)
    }
    return value1 is String && value2 is Number
}
fun case_4_funWithContract_2(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(false) implies (value1 is String && value2 is Number)
    }
    return value1 is String && value2 is Number
}
fun case_4(value1: Any?, value2: Any?) {
    if (case_4_funWithContract_1(value1, value2)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
    if (!case_4_funWithContract_2(value1, value2)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_5_funWithContract_1(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(true) implies (value1 is String && value2 == null)
    }
    return value1 is String && value2 == null
}
fun case_5_funWithContract_2(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(false) implies (value1 is String && value2 == null)
    }
    return value1 is String && value2 == null
}
fun case_5(value1: Any?, value2: Any?) {
    if (case_5_funWithContract_1(value1, value2)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
    }
    if (!case_5_funWithContract_2(value1, value2)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_6_funWithContract_1(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract {
        returns(true) implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)
    }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}
fun case_6_funWithContract_2(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract {
        returns(false) implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)
    }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}
class case_6_class {
    val prop_1: Int? = 10
    fun case_6(value1: Any?, value2: Number?) {
        val o = case_6_class()
        if (case_6_funWithContract_1(value1, value2, o.prop_1, this.prop_1)) {
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
            println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
            println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
        }
        if (!case_6_funWithContract_2(value1, value2, o.prop_1, this.prop_1)) {
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
            println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
            println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
        }
    }
}
