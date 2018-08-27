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

fun <T> T?.case_3(value1: Int?, value2: Boolean): Boolean {
    contract {
        returns(true) implies (value1 != null)
        returns(false) implies (value2)
    }

    return value1 != null
}

fun case_4(value1: Number, value2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value2 is Boolean?)
        returns(false) implies (value1 is Int)
        returnsNotNull() implies (block != null)
    }

    return <!SENSELESS_COMPARISON!>value1 != null<!>
}

fun <T> T?.case_5(value1: Number, value2: Any?): Boolean? {
    contract {
        returns(true) implies (value2 is Boolean?)
        returns(false) implies (value1 is Int)
        returnsNotNull() implies (this@case_5 != null)
    }

    return <!SENSELESS_COMPARISON!>value1 != null<!>
}

fun case_6(value1: Number, value2: Any?, block: (() -> Unit)?): Boolean? {
    contract {
        returns(true) implies (value2 is Boolean?)
        returns(false) implies (value1 is Int)
        returns(null) implies (block != null)
    }

    return <!SENSELESS_COMPARISON!>value1 != null<!>
}

fun <T> T?.case_7(value1: Number, value2: Any?): Boolean? {
    contract {
        returns(true) implies (value2 is Boolean?)
        returns(false) implies (value1 is Int)
        returns(null) implies (this@case_7 != null)
    }

    return <!SENSELESS_COMPARISON!>value1 != null<!>
}

// FILE: usages.kt

import contracts.*

fun case_1(value: Any?) {
    funWithReturns(value is Number?)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>?.toByte())
    if (funWithReturnsTrue(value is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
        if (funWithReturnsNotNull(value is Int) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.inv())
    }
}

fun case_2(value: Any?) {
    if (!funWithReturnsFalse(value is Number?)) {
        println(<!DEBUG_INFO_SMARTCAST!>value<!>?.toByte())
        funWithReturns(value is Number)
        println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
        if (funWithReturnsNotNull(value is Int) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.inv())
    }
}

fun case_3(value1: Int?, value2: Any?) {
    if (!value1.case_3(value1, value2 is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.toByte())
    } else {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
    }
}

fun case_4(value1: Number, value2: Any?, value3: (() -> Unit)?) {
    if (contracts.case_4(value1, value2, value3) == true) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(false)
    } else if (contracts.case_4(value1, value2, value3) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    } else if (contracts.case_4(value1, value2, value3) != null) {
        <!DEBUG_INFO_SMARTCAST!>value3<!>()
    }
}

fun case_5(value1: Number, value2: Any?, value3: (() -> Unit)?) {
    if (value3.case_5(value1, value2) == true) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(false)
    } else if (value3.case_5(value1, value2) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    } else if (value3.case_5(value1, value2) != null) {
        <!DEBUG_INFO_SMARTCAST!>value3<!>()
    }
}

fun case_6(value1: Number, value2: Any?, value3: (() -> Unit)?) {
    if (contracts.case_6(value1, value2, value3) == true) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(false)
    } else if (contracts.case_6(value1, value2, value3) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    } else if (contracts.case_6(value1, value2, value3) == null) {
        <!DEBUG_INFO_SMARTCAST!>value3<!>()
    }
}

fun case_7(value1: Number, value2: Any?, value3: (() -> Unit)?) {
    if (value3.case_7(value1, value2) == true) {
        <!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(false)
    } else if (value3.case_7(value1, value2) == false) {
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    } else if (value3.case_7(value1, value2) == null) {
        <!DEBUG_INFO_SMARTCAST!>value3<!>()
    }
}
