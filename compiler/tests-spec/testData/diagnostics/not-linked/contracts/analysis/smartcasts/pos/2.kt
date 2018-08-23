// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 2
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions as paremeter of contract function.
 */

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1(value1: Any?, value2: Any?) {
    funWithReturns(value1 is String && value2 is Number)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_2(value1: Any?, value2: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String || value2 !is Number)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_3(value1: Any?, value2: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String || value2 != null)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_4(value1: Any?, value2: Number?) {
    funWithReturns(value1 is Float? && value1 != null && value2 != null)
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
    println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
class case_5_class {
    val prop_1: Int? = 10

    fun case_5(value1: Any?, value2: Number?) {
        val o = case_5_class()
        funWithReturns(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null)
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
        println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_6(value1: Any?, value2: Any) {
    if (funWithReturnsTrue(value1 is String && value2 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
    if (!funWithReturnsFalse(value1 is String && value2 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
    if (funWithReturnsNotNull(value1 is String && value2 is Number) != null) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_7(value1: Any?, value2: Any?) {
    if (funWithReturnsTrueAndInvertCondition(value1 !is String || value2 !is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
    if (!funWithReturnsFalseAndInvertCondition(value1 !is String || value2 !is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 !is String || value2 !is Number) != null) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_8(value1: Any?, value2: Any?) {
    if (funWithReturnsTrueAndInvertCondition(value1 !is String || value2 != null)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
    }
    if (!funWithReturnsFalseAndInvertCondition(value1 !is String || value2 != null)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 !is String || value2 != null) != null) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
        println(<!DEBUG_INFO_CONSTANT!>value2<!>?.toByte())
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_9(value1: Any?, value2: Number?) {
    if (funWithReturnsTrue(value1 is Float? && value1 != null && value2 != null)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
    }
    if (!funWithReturnsFalse(value1 is Float? && value1 != null && value2 != null)) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
    }
    if (funWithReturnsNotNull(value1 is Float? && value1 != null && value2 != null) != null) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
        println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
    }
}

/*
 CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
 UNEXPECTED BEHAVIOUR: in the commented last line
 ISSUES: KT-26300
 */
class case_10_class {
    val prop_1: Int? = 10

    fun case_10(value1: Any?, value2: Number?) {
        val o = case_10_class()
        if (funWithReturnsTrue(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null)) {
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
            println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
            println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
        }
        if (!funWithReturnsFalse(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null)) {
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
            println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
            println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
        }
        if (funWithReturnsNotNull(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null) != null) {
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.dec())
            println(value2<!UNNECESSARY_SAFE_CALL!>?.<!>toByte())
            println(<!DEBUG_INFO_SMARTCAST!>o.prop_1<!>.plus(3))
        }
    }
}
