// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 7
 DESCRIPTION: Smartcast using some returns effects on the same values.
 */

import kotlin.internal.contracts.*

fun case_1_funWithContract_1(value: Int?) {
    contract {
        returns() implies (value != null)
    }
    if (value == null) throw Exception()
}
fun case_1_funWithContract_2(value: Int?) {
    contract {
        returns() implies (value == null)
    }
    if (value != null) throw Exception()
}
fun case_1(value: Int?) {
    case_1_funWithContract_1(value)
    <!DEBUG_INFO_SMARTCAST!>value<!>.inv()
    case_1_funWithContract_2(value)
    <!DEBUG_INFO_SMARTCAST!>value<!>.<!UNREACHABLE_CODE!>inv()<!>
    <!UNREACHABLE_CODE!>case_1_funWithContract_1(value)<!>
    <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value<!>.inv()<!>
}

fun case_2_funWithContract_1(value: Number?) {
    contract {
        returns() implies (value is Float)
    }
    if (value == null) throw Exception()
}
fun case_2_funWithContract_2(value: Number?) {
    contract {
        returns() implies (value is Int)
    }
    if (value == null) throw Exception()
}
fun case_2(t: Number?) {
    case_2_funWithContract_1(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>.toByte()
    case_2_funWithContract_2(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>.inv()
}

fun case_3_funWithContract_1(value: Any?) {
    contract {
        returns() implies (value is String)
    }
    if (value !is String) throw Exception()
}
fun case_3_funWithContract_2(value: Any?) {
    contract {
        returns() implies (value !is String)
    }
    if (value is String) throw Exception()
}
fun case_3(t: Any?) {
    case_3_funWithContract_1(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>.length
    case_3_funWithContract_2(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>.length
}

fun case_4_funWithContract_1(value: Any?) {
    contract {
        returns() implies (value is Number?)
    }
    if (value !is Number?) throw Exception()
}
fun case_4_funWithContract_2(value: Number?) {
    contract {
        returns() implies (value != null)
    }
    if (value == null) throw Exception()
}
fun case_4_funWithContract_3(value: Number) {
    contract {
        returns() implies (value is Int)
    }
    if (value !is Int) throw Exception()
}
fun case_4(t: Any?) {
    case_4_funWithContract_1(t)
    <!DEBUG_INFO_SMARTCAST!>t<!>?.toByte()
    case_4_funWithContract_2(<!DEBUG_INFO_SMARTCAST!>t<!>)
    <!DEBUG_INFO_SMARTCAST!>t<!>.toByte()
    case_4_funWithContract_3(<!DEBUG_INFO_SMARTCAST!>t<!>)
    <!DEBUG_INFO_SMARTCAST!>t<!>.inv()
}

fun case_5_funWithContract_1(value: Int?): Boolean {
    contract {
        returns(true) implies (value != null)
    }
    return value != null
}
fun case_5_funWithContract_2(value: Int?): Boolean {
    contract {
        returns(true) implies (value == null)
    }
    return value == null
}
fun case_5_funWithContract_3(value: Int?): Boolean {
    contract {
        returns(false) implies (value != null)
    }
    return value != null
}
fun case_5_funWithContract_4(value: Int?): Boolean {
    contract {
        returns(false) implies (value == null)
    }
    return value == null
}
fun case_5_funWithContract_5(value: Int?): Boolean? {
    contract {
        returnsNotNull() implies (value != null)
    }
    return value != null
}
fun case_5_funWithContract_6(value: Int?): Boolean? {
    contract {
        returnsNotNull() implies (value == null)
    }
    return value == null
}
fun case_5(value1: Int?, value2: Int?) {
    if (case_5_funWithContract_1(value1)) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        if (case_5_funWithContract_2(value1)) {
            <!DEBUG_INFO_SMARTCAST!>value1<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_funWithContract_1(value1)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value1<!>.inv()<!>
        }
    }
    if (!case_5_funWithContract_3(value2)) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
        if (!case_5_funWithContract_4(value2)) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_funWithContract_1(value2)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value2<!>.inv()<!>
        }
    }
    if (case_5_funWithContract_5(value2) != null) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
        if (case_5_funWithContract_6(value2) != null) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_funWithContract_1(value2)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value2<!>.inv()<!>
        }
    }
}

fun case_6_funWithContract_1(value: Number?): Boolean {
    contract {
        returns(true) implies (value is Float)
    }
    return value is Float
}
fun case_6_funWithContract_2(value: Number?): Boolean {
    contract {
        returns(true) implies (value is Int)
    }
    return value is Int
}
fun case_6_funWithContract_3(value: Number?): Boolean {
    contract {
        returns(false) implies (value is Float)
    }
    return value is Float
}
fun case_6_funWithContract_4(value: Number?): Boolean {
    contract {
        returns(false) implies (value is Int)
    }
    return value is Int
}
fun case_6_funWithContract_5(value: Number?): Boolean? {
    contract {
        returnsNotNull() implies (value is Float)
    }
    return value is Float
}
fun case_6_funWithContract_6(value: Number?): Boolean? {
    contract {
        returnsNotNull() implies (value is Int)
    }
    return value is Int
}
fun case_6(value1: Number?, value2: Number?) {
    when {
        case_6_funWithContract_1(value1) -> {
            <!DEBUG_INFO_SMARTCAST!>value1<!>.toByte()
            when {
                case_6_funWithContract_2(value1) -> <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
            }
        }
    }
    when {
        !case_6_funWithContract_3(value2) -> {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.toByte()
            when {
                !case_6_funWithContract_4(value2) -> <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
            }
        }
    }
    when {
        case_6_funWithContract_5(value2) != null -> {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.toByte()
            when {
                case_6_funWithContract_6(value2) != null -> <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
            }
        }
    }
}

fun case_7_funWithContract_1(value: Any?): Boolean {
    contract {
        returns(true) implies (value is String)
    }
    return value is String
}
fun case_7_funWithContract_2(value: Any?): Boolean {
    contract {
        returns(true) implies (value !is String)
    }
    return value !is String
}
fun case_7_funWithContract_3(value: Any?): Boolean {
    contract {
        returns(false) implies (value is String)
    }
    return value is String
}
fun case_7_funWithContract_4(value: Any?): Boolean {
    contract {
        returns(false) implies (value !is String)
    }
    return value !is String
}
fun case_7_funWithContract_5(value: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value is String)
    }
    return value is String
}
fun case_7_funWithContract_6(value: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value !is String)
    }
    return value !is String
}
fun case_7(value1: Any?, value2: Any?) {
    if (case_7_funWithContract_1(value1)) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.length
        if (case_7_funWithContract_2(value1)) <!DEBUG_INFO_SMARTCAST!>value1<!>.length
    }
    if (!case_7_funWithContract_3(value2)) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>.length
        if (!case_7_funWithContract_4(value2)) <!DEBUG_INFO_SMARTCAST!>value2<!>.length
    }
    if (case_7_funWithContract_5(value2) != null) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>.length
        if (case_7_funWithContract_6(value2) != null) <!DEBUG_INFO_SMARTCAST!>value2<!>.length
    }
}

fun case_8_funWithContract_1(value: Any?): Boolean {
    contract {
        returns(true) implies (value is Number?)
    }
    return value is Number?
}
fun case_8_funWithContract_2(value: Number?): Boolean {
    contract {
        returns(true) implies (value != null)
    }
    return value != null
}
fun case_8_funWithContract_3(value: Number): Boolean {
    contract {
        returns(true) implies (value is Int)
    }
    return value is Int
}
fun case_8_funWithContract_4(value: Any?): Boolean {
    contract {
        returns(false) implies (value is Number?)
    }
    return value is Number?
}
fun case_8_funWithContract_5(value: Number?): Boolean {
    contract {
        returns(false) implies (value != null)
    }
    return value != null
}
fun case_8_funWithContract_6(value: Number): Boolean {
    contract {
        returns(false) implies (value is Int)
    }
    return value is Int
}
fun case_8_funWithContract_7(value: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value is Number?)
    }
    return value is Number?
}
fun case_8_funWithContract_8(value: Number?): Boolean? {
    contract {
        returnsNotNull() implies (value != null)
    }
    return value != null
}
fun case_8_funWithContract_9(value: Number): Boolean? {
    contract {
        returnsNotNull() implies (value is Int)
    }
    return value is Int
}
fun case_8(value1: Any?, value2: Any?) {
    if (case_8_funWithContract_1(value1)) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>?.toByte()
        if (case_8_funWithContract_2(<!DEBUG_INFO_SMARTCAST!>value1<!>)) {
            <!DEBUG_INFO_SMARTCAST!>value1<!>.toByte()
            if (case_8_funWithContract_3(<!DEBUG_INFO_SMARTCAST!>value1<!>)) {
                <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
            }
        }
    }
    if (!case_8_funWithContract_4(value2)) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.toByte()
        if (!case_8_funWithContract_5(<!DEBUG_INFO_SMARTCAST!>value2<!>)) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.toByte()
            if (!case_8_funWithContract_6(<!DEBUG_INFO_SMARTCAST!>value2<!>)) {
                <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
            }
        }
    }
    if (case_8_funWithContract_7(value2) != null) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.toByte()
        if (case_8_funWithContract_8(<!DEBUG_INFO_SMARTCAST!>value2<!>) != null) {
            <!DEBUG_INFO_SMARTCAST!>value2<!>.toByte()
            if (case_8_funWithContract_9(<!DEBUG_INFO_SMARTCAST!>value2<!>) != null) {
                <!DEBUG_INFO_SMARTCAST!>value2<!>.inv()
            }
        }
    }
}
