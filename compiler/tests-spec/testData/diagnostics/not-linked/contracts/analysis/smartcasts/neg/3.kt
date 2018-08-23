// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 3
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions as implies parameter in contract definition.
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun case_1(value1: Any?, value2: Any?) {
    contract { returns() implies (value1 !is String || value2 !is Number) }
    if (value1 is String && value2 is Number) throw Exception()
}

fun case_2(value1: Any?, value2: Any?) {
    contract { returns() implies (value1 !is String || value2 != null) }
    if (value1 is String && value2 == null) throw Exception()
}

fun case_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?) {
    contract { returns() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    if (!(value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null)) throw Exception()
}

fun case_4_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_4_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_4_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}

fun case_5_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 != null) }
    return value1 !is String || value2 != null
}
fun case_5_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 != null) }
    return value1 !is String || value2 != null
}
fun case_5_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 is String && value2 == null) }
    return value1 !is String || value2 != null
}

fun case_6_1(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(true) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_6_2(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(false) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_6_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}

// FILE: usages.kt

import contracts.*

fun case_1(value1: Any?, value2: Any?) {
    contracts.case_1(value1, value2)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

fun case_2(value1: Any?, value2: Any?) {
    contracts.case_2(value1, value2)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

class case_3_class {
    val prop_1: Int? = 10
    fun case_3(value1: Any?, value2: Number?) {
        val o = case_3_class()
        contracts.case_3(value1, value2, o.prop_1, this.prop_1)
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
        println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
    }
}

fun case_4(value1: Any?, value2: Any?) {
    if (contracts.case_4_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_4_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_4_3(value1, value2) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_5(value1: Any?, value2: Any?) {
    if (contracts.case_5_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_5_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_5_3(value1, value2) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

class case_6_class {
    val prop_1: Int? = 10
    fun case_6(value1: Any?, value2: Number?) {
        val o = case_6_class()
        if (contracts.case_6_1(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (!contracts.case_6_2(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_6_3(value1, value2, o.prop_1, this.prop_1) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}
