// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 8
 DESCRIPTION: Smartcast using more of the various returns effects on the same values.
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun <T> T?.case_3(value_1: Int?, value_2: Boolean): Boolean {
    contract {
        returns(true) implies (value_1 == null)
        returns(false) implies (value_2)
    }

    return value_1 == null
}

fun case_4(value_1: Number, value_2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value_2 !is Boolean?)
        returns(false) implies (value_1 !is Int)
        returnsNotNull() implies (block == null)
    }

    return <!SENSELESS_COMPARISON!>value_1 == null<!>
}

fun case_5(value_1: Number, value_2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value_2 !is Boolean?)
        returns(false) implies (value_1 !is Int)
        returns(null) implies (block == null)
    }

    return <!SENSELESS_COMPARISON!>value_1 == null<!>
}

fun <T> T?.case_6(value_1: Number, value_2: Any?): Boolean? {
    contract {
        returns(true) implies (value_2 !is Boolean?)
        returns(false) implies (value_1 !is Int)
        returnsNotNull() implies (this@case_6 == null)
    }

    return <!SENSELESS_COMPARISON!>value_1 == null<!>
}

fun <T> T?.case_7(value_1: Number, value_2: Any?): Boolean? {
    contract {
        returns(true) implies (value_2 !is Boolean?)
        returns(false) implies (value_1 !is Int)
        returns(null) implies (this@case_7 == null)
    }

    return <!SENSELESS_COMPARISON!>value_1 == null<!>
}

// FILE: usages.kt

import contracts.*

fun case_1(value_1: Any?) {
    funWithReturns(value_1 !is Number?)
    println(value_1?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    if (funWithReturnsTrue(value_1 !is Number)) {
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
        if (funWithReturnsNotNull(value_1 is Int) == null) println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
}

fun case_2(value_1: Any?) {
    if (!funWithReturnsFalse(value_1 !is Number?)) {
        println(value_1?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
        funWithReturns(value_1 !is Number)
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
        if (funWithReturnsNotNull(value_1 !is Int) != null) println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
}

fun case_3(value_1: Int?, value_2: Any?) {
    if (!value_1.case_3(value_1, value_2 !is Number)) {
        println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    } else {
        <!DEBUG_INFO_CONSTANT!>value_1<!><!UNSAFE_CALL!>.<!>inv()
    }
}

fun case_4(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (contracts.case_4(value_1, value_2, value_3) == true) {
        value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xor<!>(false)
    } else if (contracts.case_4(value_1, value_2, value_3) == false) {
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    } else if (contracts.case_4(value_1, value_2, value_3) != null) {
        <!UNSAFE_IMPLICIT_INVOKE_CALL, DEBUG_INFO_CONSTANT!>value_3<!>()
    }
}

fun case_5(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (contracts.case_5(value_1, value_2, value_3) == true) {
        value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xor<!>(false)
    } else if (contracts.case_5(value_1, value_2, value_3) == false) {
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    } else if (contracts.case_5(value_1, value_2, value_3) == null) {
        <!UNSAFE_IMPLICIT_INVOKE_CALL, DEBUG_INFO_CONSTANT!>value_3<!>()
    }
}

fun case_6(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (value_3.case_6(value_1, value_2) == true) {
        value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xor<!>(false)
    } else if (value_3.case_6(value_1, value_2) == false) {
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    } else if (value_3.case_6(value_1, value_2) != null) {
        <!UNSAFE_IMPLICIT_INVOKE_CALL, DEBUG_INFO_CONSTANT!>value_3<!>()
    }
}

fun case_7(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (value_3.case_7(value_1, value_2) == true) {
        value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xor<!>(false)
    } else if (value_3.case_7(value_1, value_2) == false) {
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    } else if (value_3.case_7(value_1, value_2) == null) {
        <!UNSAFE_IMPLICIT_INVOKE_CALL, DEBUG_INFO_CONSTANT!>value_3<!>()
    }
}
