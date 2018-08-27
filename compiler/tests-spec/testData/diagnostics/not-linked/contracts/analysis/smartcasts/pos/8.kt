// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

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
        returns(true) implies (value_1 != null)
        returns(false) implies (value_2)
    }

    return value_1 != null
}

fun case_4(value_1: Number, value_2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value_2 is Boolean?)
        returns(false) implies (value_1 is Int)
        returnsNotNull() implies (block != null)
    }

    return <!SENSELESS_COMPARISON!>value_1 != null<!>
}

fun <T> T?.case_5(value_1: Number, value_2: Any?): Boolean? {
    contract {
        returns(true) implies (value_2 is Boolean?)
        returns(false) implies (value_1 is Int)
        returnsNotNull() implies (this@case_5 != null)
    }

    return <!SENSELESS_COMPARISON!>value_1 != null<!>
}

fun case_6(value_1: Number, value_2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value_2 is Boolean?)
        returns(false) implies (value_1 is Int)
        returns(null) implies (block != null)
    }

    return <!SENSELESS_COMPARISON!>value_1 != null<!>
}

fun <T> T?.case_7(value_1: Number, value_2: Any?): Boolean? {
    contract {
        returns(true) implies (value_2 is Boolean?)
        returns(false) implies (value_1 is Int)
        returns(null) implies (this@case_7 != null)
    }

    return <!SENSELESS_COMPARISON!>value_1 != null<!>
}

fun case_8(value_1: Boolean): Boolean {
    contract {
        returns(true) implies (value_1)
        returns(false) implies (!value_1)
    }

    return value_1
}

fun case_9(value_1: Boolean): Boolean? {
    contract {
        returns(null) implies (value_1)
        returns(false) implies (!value_1)
    }

    return value_1
}

// FILE: usages.kt

import contracts.*

fun case_1(value_1: Any?) {
    funWithReturns(value_1 is Number?)
    println(<!DEBUG_INFO_SMARTCAST!>value_1<!>?.toByte())
    if (funWithReturnsTrue(value_1 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.toByte())
        if (funWithReturnsNotNull(value_1 is Int) != null) println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
}

fun case_2(value_1: Any?) {
    if (!funWithReturnsFalse(value_1 is Number?)) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>?.toByte())
        funWithReturns(value_1 is Number)
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.toByte())
        if (funWithReturnsNotNull(value_1 is Int) != null) println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
}

fun case_3(value_1: Int?, value_2: Any?) {
    if (!value_1.case_3(value_1, value_2 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value_2<!>.toByte())
    } else {
        <!DEBUG_INFO_SMARTCAST!>value_1<!>.inv()
    }
}

fun case_4(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (contracts.case_4(value_1, value_2, value_3) == true) {
        <!DEBUG_INFO_SMARTCAST!>value_2<!>?.xor(false)
    } else if (contracts.case_4(value_1, value_2, value_3) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    } else if (contracts.case_4(value_1, value_2, value_3) != null) {
        <!DEBUG_INFO_SMARTCAST!>value_3<!>()
    }
}

fun case_5(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (value_3.case_5(value_1, value_2) == true) {
        <!DEBUG_INFO_SMARTCAST!>value_2<!>?.xor(false)
    } else if (value_3.case_5(value_1, value_2) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    } else if (value_3.case_5(value_1, value_2) != null) {
        <!DEBUG_INFO_SMARTCAST!>value_3<!>()
    }
}

fun case_6(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (contracts.case_6(value_1, value_2, value_3) == true) {
        <!DEBUG_INFO_SMARTCAST!>value_2<!>?.xor(false)
    } else if (contracts.case_6(value_1, value_2, value_3) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    } else if (contracts.case_6(value_1, value_2, value_3) == null) {
        <!DEBUG_INFO_SMARTCAST!>value_3<!>()
    }
}

fun case_7(value_1: Number, value_2: Any?, value_3: (() -> Unit)?) {
    if (value_3.case_7(value_1, value_2) == true) {
        <!DEBUG_INFO_SMARTCAST!>value_2<!>?.xor(false)
    } else if (value_3.case_7(value_1, value_2) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    } else if (value_3.case_7(value_1, value_2) == null) {
        <!DEBUG_INFO_SMARTCAST!>value_3<!>()
    }
}

fun case_8(value_1: Number) {
    if (contracts.case_8(value_1 is Int)) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
    if (contracts.case_8(value_1 !is Int)) {

    } else {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
    if (!contracts.case_8(value_1 !is Int)) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
}

fun case_9(value_1: Number) {
    if (contracts.case_9(value_1 is Int) == null) {
        println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
    }
}
