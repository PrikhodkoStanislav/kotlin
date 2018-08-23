// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 4
 DESCRIPTION: Smartcast using returns effect with simple type checking and not-null conditions on receiver.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T> T.case_1_funWithContract() {
    contract {
        returns() implies (this@case_1_funWithContract is String)
    }
    if (this !is String) throw Exception()
}
fun case_1(value1: Any?) {
    value1.case_1_funWithContract()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
}

fun <T : Number> T.case_2_funWithContract() {
    contract {
        returns() implies (this@case_2_funWithContract is Int)
    }
    if (this !is Int) throw Exception()
}
fun case_2(value1: Number) {
    value1.case_2_funWithContract()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_funWithContract_1() {
    contract {
        returns() implies (this@case_3_funWithContract_1 != null)
    }
    if (this == null) throw Exception()
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_funWithContract_2() {
    contract {
        returns() implies (this@case_3_funWithContract_2 == null)
    }
    if (this != null) throw Exception()
}
fun case_3(value1: String?, value2: String?) {
    value1.case_3_funWithContract_1()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    value2.case_3_funWithContract_2()
    println(<!DEBUG_INFO_CONSTANT!>value2<!>)
}

fun <T : String?> T.case_4_funWithContract_1() {
    contract {
        returns() implies (this@case_4_funWithContract_1 != null)
    }
    if (this == null) throw Exception()
}
fun <T : String?> T.case_4_funWithContract_2() {
    contract {
        returns() implies (this@case_4_funWithContract_2 == null)
    }
    if (this != null) throw Exception()
}
fun case_4(value1: String?, value2: String?) {
    value1.case_4_funWithContract_1()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    value2.case_4_funWithContract_2()
    println(<!DEBUG_INFO_CONSTANT!>value2<!>)
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T> T.case_5_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_5_funWithContract_1 is String)
    }
    return this is String
}
fun <T> T.case_5_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_5_funWithContract_2 is String)
    }
    return this is String
}
fun <T> T.case_5_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_5_funWithContract_3 is String)
    }
    return this is String
}
fun case_5(value1: Any?) {
    if (value1.case_5_funWithContract_1()) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    if (!value1.case_5_funWithContract_2()) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    if (value1.case_5_funWithContract_3() != null) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
}

fun <T : Number> T.case_6_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_6_funWithContract_1 is Int)
    }
    return this is Int
}
fun <T : Number> T.case_6_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_6_funWithContract_2 is Int)
    }
    return this is Int
}
fun <T : Number> T.case_6_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_6_funWithContract_3 is Int)
    }
    return this is Int
}
fun case_6(value1: Number) {
    when {
        value1.case_6_funWithContract_1() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
    when {
        !value1.case_6_funWithContract_2() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
    when {
        value1.case_6_funWithContract_3() != null -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    }
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_7_funWithContract_1 != null)
    }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_funWithContract_2(): Boolean {
    contract {
        returns(true) implies (this@case_7_funWithContract_2 == null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_7_funWithContract_3 == null)
    }
    return this == null
}
fun case_7(value1: String?, value2: String?) {
    if (value1.case_7_funWithContract_1()) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    if (value2.case_7_funWithContract_2()) {
        println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
    if (!(value2.case_7_funWithContract_3() == null)) {
        println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
}

fun <T : String?> T.case_8_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_8_funWithContract_1 != null)
    }
    return this != null
}
fun <T : String?> T.case_8_funWithContract_2(): Boolean {
    contract {
        returns(true) implies (this@case_8_funWithContract_2 == null)
    }
    return this == null
}
fun <T : String?> T.case_8_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_8_funWithContract_3 == null)
    }
    return this == null
}
fun case_8(value1: String?, value2: String?) {
    when {
        value1.case_8_funWithContract_1() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    when {
        value2.case_8_funWithContract_2() -> println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
    when {
        !(value2.case_8_funWithContract_3() == null) -> println(<!DEBUG_INFO_CONSTANT!>value2<!>)
    }
}
