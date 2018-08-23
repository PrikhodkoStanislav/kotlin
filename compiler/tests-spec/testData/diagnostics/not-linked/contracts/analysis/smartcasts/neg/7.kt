// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 7
 DESCRIPTION: Smartcast using more of the same returns effects on the same values.
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun case_1_1(value: Int?) {
    contract { returns() implies (value == null) }
    if (value != null) throw Exception()
}
fun case_1_2(value: Int?) {
    contract { returns() implies (value != null) }
    if (value == null) throw Exception()
}

fun case_2_1(value: Number?) {
    contract { returns() implies (value !is Float) }
    if (value != null) throw Exception()
}
fun case_2_2(value: Number?) {
    contract { returns() implies (value !is Int) }
    if (value != null) throw Exception()
}

fun case_3_1(value: Any?) {
    contract { returns() implies (value !is String) }
    if (value is String) throw Exception()
}
fun case_3_2(value: Any?) {
    contract { returns() implies (value is String) }
    if (value !is String) throw Exception()
}

fun case_4_1(value: Any?) {
    contract { returns() implies (value !is Number?) }
    if (value is Number?) throw Exception()
}
fun case_4_2(value: Number?) {
    contract { returns() implies (value == null) }
    if (value != null) throw Exception()
}
fun case_4_3(value: Number) {
    contract { returns() implies (value !is Int) }
    if (value is Int) throw Exception()
}

fun case_5_1(value: Int?): Boolean {
    contract { returns(true) implies (value == null) }
    return value == null
}
fun case_5_2(value: Int?): Boolean {
    contract { returns(true) implies (value != null) }
    return value != null
}
fun case_5_3(value: Int?): Boolean {
    contract { returns(false) implies (value == null) }
    return value == null
}
fun case_5_4(value: Int?): Boolean {
    contract { returns(false) implies (value != null) }
    return value != null
}
fun case_5_5(value: Int?): Boolean? {
    contract { returnsNotNull() implies (value == null) }
    return value == null
}
fun case_5_6(value: Int?): Boolean? {
    contract { returnsNotNull() implies (value != null) }
    return value != null
}

fun case_6_1(value: Number?): Boolean {
    contract { returns(true) implies (value !is Float) }
    return value !is Float
}
fun case_6_2(value: Number?): Boolean {
    contract { returns(true) implies (value !is Int) }
    return value !is Int
}
fun case_6_3(value: Number?): Boolean {
    contract { returns(false) implies (value !is Float) }
    return value !is Float
}
fun case_6_4(value: Number?): Boolean {
    contract { returns(false) implies (value !is Int) }
    return value !is Int
}
fun case_6_5(value: Number?): Boolean? {
    contract { returnsNotNull() implies (value !is Float) }
    return value !is Float
}
fun case_6_6(value: Number?): Boolean? {
    contract { returnsNotNull() implies (value !is Int) }
    return value !is Int
}

fun case_7_1(value: Any?): Boolean {
    contract { returns(true) implies (value !is String) }
    return value !is String
}
fun case_7_2(value: Any?): Boolean {
    contract { returns(true) implies (value is String) }
    return value is String
}
fun case_7_3(value: Any?): Boolean {
    contract { returns(false) implies (value !is String) }
    return value !is String
}
fun case_7_4(value: Any?): Boolean {
    contract { returns(false) implies (value is String) }
    return value is String
}
fun case_7_5(value: Any?): Boolean? {
    contract { returnsNotNull() implies (value !is String) }
    return value !is String
}
fun case_7_6(value: Any?): Boolean? {
    contract { returnsNotNull() implies (value is String) }
    return value is String
}

fun case_8_1(value: Any?): Boolean {
    contract { returns(true) implies (value !is Number?) }
    return value !is Number?
}
fun case_8_2(value: Number?): Boolean {
    contract { returns(true) implies (value == null) }
    return value == null
}
fun case_8_3(value: Number): Boolean {
    contract { returns(true) implies (value !is Int) }
    return value !is Int
}
fun case_8_4(value: Any?): Boolean {
    contract { returns(false) implies (value !is Number?) }
    return value !is Number?
}
fun case_8_5(value: Number?): Boolean {
    contract { returns(false) implies (value == null) }
    return value == null
}
fun case_8_6(value: Number): Boolean {
    contract { returns(false) implies (value !is Int) }
    return value !is Int
}
fun case_8_7(value: Any?): Boolean? {
    contract { returnsNotNull() implies (value is Number?) }
    return value is Number?
}
fun case_8_8(value: Number?): Boolean? {
    contract { returnsNotNull() implies (value == null) }
    return value == null
}
fun case_8_9(value: Number): Boolean? {
    contract { returnsNotNull() implies (value !is Int) }
    return value !is Int
}

// FILE: usages.kt

import contracts.*

fun case_1(value: Int?) {
    case_1_1(value)
    <!DEBUG_INFO_CONSTANT!>value<!><!UNSAFE_CALL!>.<!>inv()
    case_1_2(<!DEBUG_INFO_CONSTANT!>value<!>)
    <!DEBUG_INFO_SMARTCAST!>value<!>.<!UNREACHABLE_CODE!>inv()<!>
    <!UNREACHABLE_CODE!>case_1_1(value)<!>
    <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value<!>.inv()<!>
}

fun case_2(t: Number?) {
    case_2_1(t)
    t<!UNSAFE_CALL!>.<!>toByte()
    case_2_2(t)
    t.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()
}

fun case_3(t: Any?) {
    case_3_1(t)
    t.<!UNRESOLVED_REFERENCE!>length<!>
    case_3_2(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>.length
}

fun case_4(t: Any?) {
    case_4_1(t)
    t?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()
    case_4_2(<!TYPE_MISMATCH!>t<!>)
    <!DEBUG_INFO_CONSTANT!>t<!><!UNSAFE_CALL!>.<!>toByte()
    case_4_3(<!TYPE_MISMATCH, DEBUG_INFO_CONSTANT!>t<!>)
    <!DEBUG_INFO_CONSTANT!>t<!><!UNSAFE_CALL!>.<!><!MISSING_DEPENDENCY_CLASS, MISSING_DEPENDENCY_CLASS!>inv<!>()
}

fun case_5(value1: Int?, value2: Int?) {
    if (case_5_1(value1)) {
        <!DEBUG_INFO_CONSTANT!>value1<!><!UNSAFE_CALL!>.<!>inv()
        if (case_5_2(<!DEBUG_INFO_CONSTANT!>value1<!>)) {
            <!DEBUG_INFO_SMARTCAST!>value1<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_1(value1)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value1<!>.inv()<!>
        }
    }
    if (!case_5_3(value2)) {
        <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!>inv()
        if (!case_5_4(<!DEBUG_INFO_CONSTANT!>value2<!>)) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_1(value2)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value2<!>.inv()<!>
        }
    }
    if (case_5_5(value2) != null) {
        <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!>inv()
        if (case_5_6(<!DEBUG_INFO_CONSTANT!>value2<!>) != null) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_1(value2)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value2<!>.inv()<!>
        }
    }
}

fun case_6(value1: Number?, value2: Number?) {
    when {
        case_6_1(value1) -> {
            value1<!UNSAFE_CALL!>.<!>toByte()
            when { case_6_2(value1) -> value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>() }
        }
    }
    when {
        !case_6_3(value2) -> {
            value2<!UNSAFE_CALL!>.<!>toByte()
            when { !case_6_4(value2) -> value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>() }
        }
    }
    when {
        case_6_5(value2) != null -> {
            value2<!UNSAFE_CALL!>.<!>toByte()
            when { case_6_6(value2) != null -> value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>() }
        }
    }
}

fun case_7(value1: Any?, value2: Any?) {
    if (case_7_1(value1)) {
        value1.<!UNRESOLVED_REFERENCE!>length<!>
        if (case_7_2(value1)) <!DEBUG_INFO_SMARTCAST!>value1<!>.length
    }
    if (!case_7_3(value2)) {
        value2.<!UNRESOLVED_REFERENCE!>length<!>
        if (!case_7_4(value2)) <!DEBUG_INFO_SMARTCAST!>value2<!>.length
    }
    if (case_7_5(value2) != null) {
        value2.<!UNRESOLVED_REFERENCE!>length<!>
        if (case_7_6(value2) != null) <!DEBUG_INFO_SMARTCAST!>value2<!>.length
    }
}

fun case_8(value1: Any?, value2: Any?) {
    if (case_8_1(value1)) {
        value1?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()
        if (case_8_2(<!TYPE_MISMATCH!>value1<!>)) {
            <!DEBUG_INFO_CONSTANT!>value1<!><!UNSAFE_CALL!>.<!>toByte()
            if (case_8_3(<!TYPE_MISMATCH, DEBUG_INFO_CONSTANT!>value1<!>)) <!DEBUG_INFO_CONSTANT!>value1<!><!UNSAFE_CALL!>.<!><!MISSING_DEPENDENCY_CLASS, MISSING_DEPENDENCY_CLASS!>inv<!>()
        }
    }
    if (!case_8_4(value2)) {
        value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()
        if (!case_8_5(<!TYPE_MISMATCH!>value2<!>)) {
            <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!>toByte()
            if (!case_8_6(<!TYPE_MISMATCH, DEBUG_INFO_CONSTANT!>value2<!>)) <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!><!MISSING_DEPENDENCY_CLASS, MISSING_DEPENDENCY_CLASS!>inv<!>()
        }
    }
    if (case_8_7(value2) == null) {
        value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()
        if (case_8_8(<!TYPE_MISMATCH!>value2<!>) != null) {
            <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!>toByte()
            if (case_8_9(<!TYPE_MISMATCH, DEBUG_INFO_CONSTANT!>value2<!>) != null) <!DEBUG_INFO_CONSTANT!>value2<!><!UNSAFE_CALL!>.<!><!MISSING_DEPENDENCY_CLASS, MISSING_DEPENDENCY_CLASS!>inv<!>()
        }
    }
}
