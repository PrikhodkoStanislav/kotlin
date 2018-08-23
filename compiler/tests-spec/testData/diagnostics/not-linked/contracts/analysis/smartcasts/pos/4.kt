// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 4
 DESCRIPTION: Smartcast using returns effect with simple type checking and not-null conditions on receiver (extention function).
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun <T> T.case_1() {
    contract { returns() implies (this@case_1 is String) }
    if (this !is String) throw Exception()
}

fun <T : Number> T.case_2() {
    contract { returns() implies (this@case_2 is Int) }
    if (this !is Int) throw Exception()
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_1() {
    contract { returns() implies (this@case_3_1 != null) }
    if (this == null) throw Exception()
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_2() {
    contract { returns() implies (this@case_3_2 == null) }
    if (this != null) throw Exception()
}

fun <T : String?> T.case_4_1() {
    contract { returns() implies (this@case_4_1 != null) }
    if (this == null) throw Exception()
}
fun <T : String?> T.case_4_2() {
    contract { returns() implies (this@case_4_2 == null) }
    if (this != null) throw Exception()
}

fun <T> T.case_5_1(): Boolean {
    contract { returns(true) implies (this@case_5_1 is String) }
    return this is String
}
fun <T> T.case_5_2(): Boolean {
    contract { returns(false) implies (this@case_5_2 is String) }
    return this is String
}
fun <T> T.case_5_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_5_3 is String) }
    return this is String
}

fun <T : Number> T.case_6_1(): Boolean {
    contract { returns(true) implies (this@case_6_1 is Int) }
    return this is Int
}
fun <T : Number> T.case_6_2(): Boolean {
    contract { returns(false) implies (this@case_6_2 is Int) }
    return this is Int
}
fun <T : Number> T.case_6_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_6_3 is Int) }
    return this is Int
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_1(): Boolean {
    contract { returns(true) implies (this@case_7_1 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_2(): Boolean {
    contract { returns(true) implies (this@case_7_2 == null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_7_3 == null) }
    return this == null
}

fun <T : String?> T.case_8_1(): Boolean {
    contract { returns(true) implies (this@case_8_1 != null) }
    return this != null
}
fun <T : String?> T.case_8_2(): Boolean {
    contract { returns(true) implies (this@case_8_2 == null) }
    return this == null
}
fun <T : String?> T.case_8_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_8_3 == null) }
    return this == null
}

// FILE: usages.kt

import contracts.*

fun case_1(value1: Any?) {
    value1.case_1()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
}

fun case_2(value1: Number) {
    value1.case_2()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
}

fun case_3(value1: String?, value2: String?) {
    value1.case_3_1()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    value2.case_3_2()
    println(<!DEBUG_INFO_CONSTANT!>value2<!>)
}

fun case_4(value1: String?, value2: String?) {
    value1.case_4_1()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    value2.case_4_2()
    println(<!DEBUG_INFO_CONSTANT!>value2<!>)
}

fun case_5(value1: Any?) {
    if (value1.case_5_1()) println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    if (!value1.case_5_2()) println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    if (value1.case_5_3() != null) println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
}

fun case_6(value1: Number) {
    when {
        value1.case_6_1() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
    when {
        !value1.case_6_2() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
    when {
        value1.case_6_3() != null -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
}

fun case_7(value1: String?, value2: String?) {
    if (value1.case_7_1()) println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    if (value2.case_7_2()) println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    if (!(value2.case_7_3() == null)) println(<!DEBUG_INFO_CONSTANT!>value2<!>)
}

fun case_8(value1: String?, value2: String?) {
    when {
        value1.case_8_1() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    when {
        value2.case_8_2() -> println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
    when {
        !(value2.case_8_3() == null) -> println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
}
