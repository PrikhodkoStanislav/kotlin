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
fun case_5(value: Int?) {
    if (case_5_funWithContract_1(value)) {
        <!DEBUG_INFO_SMARTCAST!>value<!>.inv()
        if (case_5_funWithContract_2(value)) {
            <!DEBUG_INFO_SMARTCAST!>value<!>.<!UNREACHABLE_CODE!>inv()<!>
            <!UNREACHABLE_CODE!>case_5_funWithContract_1(value)<!>
            <!UNREACHABLE_CODE!><!DEBUG_INFO_SMARTCAST!>value<!>.inv()<!>
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
fun case_6(t: Number?) {
    when {
        case_6_funWithContract_1(t) -> {
            <!DEBUG_INFO_SMARTCAST!>t<!>.toByte()
            when {
                case_6_funWithContract_2(t) -> <!DEBUG_INFO_SMARTCAST!>t<!>.inv()
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
        returns() implies (value !is String)
    }
    return value !is String
}
fun case_7(t: Any?) {
    if (case_7_funWithContract_1(t)) {
        <!DEBUG_INFO_SMARTCAST!>t<!>.length
        if (case_7_funWithContract_2(t)) <!DEBUG_INFO_SMARTCAST!>t<!>.length
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
fun case_8(t: Any?) {
    if (case_8_funWithContract_1(t)) {
        <!DEBUG_INFO_SMARTCAST!>t<!>?.toByte()
        if (case_8_funWithContract_2(<!DEBUG_INFO_SMARTCAST!>t<!>)) {
            <!DEBUG_INFO_SMARTCAST!>t<!>.toByte()
            if (case_8_funWithContract_3(<!DEBUG_INFO_SMARTCAST!>t<!>)) {
                <!DEBUG_INFO_SMARTCAST!>t<!>.inv()
            }
        }
    }
}
