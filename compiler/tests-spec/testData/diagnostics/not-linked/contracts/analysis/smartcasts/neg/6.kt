// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 6
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions on receiver and another value (mixed).
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun <T> T?.case_1(value: Int?) {
    contract { returns() implies (this@case_1 == null || this@case_1 !is String || value == null) }
    if (!(this == null || this !is String || value == null)) throw Exception()
}

fun <T : Number?> T.case_2(value2: Any?) {
    contract { returns() implies (this@case_2 !is Int || <!SENSELESS_COMPARISON!>this@case_2 == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>) }
    if (!(this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>)) throw Exception()
}

<!NOTHING_TO_INLINE!>inline<!> fun <T : Any?> T?.case_3(value2: Any?) {
    contract { returns() implies (this@case_3 !is Number || this@case_3 !is Int || <!SENSELESS_COMPARISON!>this@case_3 == null<!> || value2 == null) }
    if (!(this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>)) throw Exception()
}

inline fun <reified T : Any?> T?.case_4(value2: Number, value3: Any?, value4: String?) {
    contract { returns() implies ((this@case_4 !is Number && this@case_4 !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null) }
    if (!((this !is Number && this !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null)) throw Exception()
}

fun <T> T?.case_5_1(value: Int?): Boolean {
    contract { returns(true) implies (this@case_5_1 == null || this@case_5_1 !is String || value == null) }
    return this == null || this !is String || value == null
}
fun <T> T?.case_5_2(value: Int?): Boolean {
    contract { returns(false) implies (this@case_5_2 == null || this@case_5_2 !is String || value == null) }
    return this == null || this !is String || value == null
}
fun <T> T?.case_5_3(value: Int?): Boolean? {
    contract { returnsNotNull() implies (this@case_5_3 == null || this@case_5_3 !is String || value == null) }
    return this == null || this !is String || value == null
}

fun <T : Number?> T.case_6_1(value2: Any?): Boolean {
    contract { returns(true) implies (this@case_6_1 !is Int || <!SENSELESS_COMPARISON!>this@case_6_1 == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>
}
fun <T : Number?> T.case_6_2(value2: Any?): Boolean {
    contract { returns(false) implies (this@case_6_2 !is Int || <!SENSELESS_COMPARISON!>this@case_6_2 == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>
}
fun <T : Number?> T.case_6_3(value2: Any?): Boolean? {
    contract { returnsNotNull() implies (this@case_6_3 !is Int || <!SENSELESS_COMPARISON!>this@case_6_3 == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 !is Number || <!SENSELESS_COMPARISON!>value2 == null<!>
}

<!NOTHING_TO_INLINE!>inline<!> fun <T : Any?> T?.case_7_1(value2: Any?): Boolean {
    contract { returns(true) implies (this@case_7_1 !is Number || this@case_7_1 !is Int || <!SENSELESS_COMPARISON!>this@case_7_1 == null<!> || value2 == null) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 == null
}
<!NOTHING_TO_INLINE!>inline<!> fun <T : Any?> T?.case_7_2(value2: Any?): Boolean {
    contract { returns(true) implies (this@case_7_2 !is Number || this@case_7_2 !is Int || <!SENSELESS_COMPARISON!>this@case_7_2 == null<!> || value2 == null) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 == null
}
<!NOTHING_TO_INLINE!>inline<!> fun <T : Any?> T?.case_7_3(value2: Any?): Boolean? {
    contract { returnsNotNull() implies (this@case_7_3 !is Number || this@case_7_3 !is Int || <!SENSELESS_COMPARISON!>this@case_7_3 == null<!> || value2 == null) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!> || value2 == null
}

inline fun <reified T : Any?> T?.case_8_1(value2: Number, value3: Any?, value4: String?): Boolean {
    contract { returns(true) implies ((this@case_8_1 !is Number && this@case_8_1 !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null) }
    return (this !is Number && this !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null
}
inline fun <reified T : Any?> T?.case_8_2(value2: Number, value3: Any?, value4: String?): Boolean {
    contract { returns(false) implies ((this@case_8_2 !is Number && this@case_8_2 !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null) }
    return (this !is Number && this !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null
}
inline fun <reified T : Any?> T?.case_8_3(value2: Number, value3: Any?, value4: String?): Boolean? {
    contract { returnsNotNull() implies ((this@case_8_3 is Number || this@case_8_3 is Int) && value2 is Int && value3 != null && value3 is Number && value4 != null) }
    return (this !is Number && this !is Int) || value2 !is Int || value3 == null || value3 !is Number || value4 == null
}

// FILE: usages.kt

import contracts.*

fun case_1(value1: Any?, value2: Int?) {
    value1.case_1(value2)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2<!UNSAFE_CALL!>.<!>inv())
}

fun case_2(value1: Number?, value2: Any?) {
    value1.case_2(value2)
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

fun case_3(value1: Any?, value2: String?) {
    value1.case_3(value2)
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    println(value2<!UNSAFE_CALL!>.<!>length)
}

fun case_4(value1: Any?, value2: Number, value3: Any?, value4: String?) {
    value1.case_4(value2, value3, value4)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    println(value4<!UNSAFE_CALL!>.<!>length)
}

fun case_5(value1: Any?, value2: Int?, value3: Any?, value4: Int?, value5: Any?, value6: Int?) {
    when {
        value1.case_5_1(value2) -> {
            println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value2<!UNSAFE_CALL!>.<!>inv())
        }
    }
    when {
        !value3.case_5_2(value4) -> {
            println(value3.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value4<!UNSAFE_CALL!>.<!>inv())
        }
    }
    when {
        value5.case_5_3(value6) != null -> {
            println(value5.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value6<!UNSAFE_CALL!>.<!>inv())
        }
    }
}

fun case_6(value1: Number?, value2: Any?, value3: Number?, value4: Any?, value5: Number?, value6: Any?) {
    if (value1.case_6_1(value2)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!value3.case_6_2(value4)) {
        println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value4.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (value5.case_6_3(value6) != null) {
        println(value5.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value6.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_7(value1: Any?, value2: String?, value3: Any?, value4: String?, value5: Any?, value6: String?) {
    if (value1.case_7_1(value2)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value2<!UNSAFE_CALL!>.<!>length)
    }
    if (value3.case_7_2(value4)) {
        println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value4<!UNSAFE_CALL!>.<!>length)
    }
    if (value5.case_7_3(value6) != null) {
        println(value5.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
        println(value6<!UNSAFE_CALL!>.<!>length)
    }
}

fun case_8(value1: Any?, value2: Number, value3: Any?, value4: String?, value5: Any?, value6: Number, value7: Any?, value8: String?) {
    when { value1.case_8_1(value2, value3, value4) -> println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { value1.case_8_1(value2, value3, value4) -> println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) }
    when { value1.case_8_1(value2, value3, value4) -> println(value4<!UNSAFE_CALL!>.<!>length) }
    when { !value5.case_8_2(value6, value7, value8) -> println(value6.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { !value5.case_8_2(value6, value7, value8) -> println(value7.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) }
    when { !value5.case_8_2(value6, value7, value8) -> println(value8<!UNSAFE_CALL!>.<!>length) }
    when { value5.case_8_3(value6, value7, value8) == null -> println(value6.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { value5.case_8_3(value6, value7, value8) == null -> println(value7.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) }
    when { value5.case_8_3(value6, value7, value8) == null -> println(value8<!UNSAFE_CALL!>.<!>length) }
}
