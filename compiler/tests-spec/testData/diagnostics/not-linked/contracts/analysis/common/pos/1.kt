// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, common
 NUMBER: 1
 DESCRIPTION: Analysis by contracts with mixed various effects.
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

// CASE KEYWORDS: inlineFunction, returnsTrue, notNullCheck, callsInPlace, atLeastOnce, 2Effects
inline fun case_1(value1: Int?, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
        returns(true) implies (value1 != null)
    }

    block()

    return value1 != null
}

// CASE KEYWORDS: inlineFunction, extensionFunction, returnsNotNull, returnsFalse, notNullCheck, nullableBooleanTypeCheck, callsInPlace, exactlyOnce, 4Effects, thisNotNullCheck
inline fun <T> T?.case_2(value1: Int?, value2: Any?, block: () -> Unit): Boolean? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returnsNotNull() implies (value1 != null)
        returns(false) implies (value2 is Boolean?)
        returns(false) implies (this@case_2 != null)
    }

    block()

    return value1 != null
}

// FILE: usages.kt

import contracts.*

// CASE KEYWORDS: notNullSmartcast, smartValInitialization, smartValUsage
fun case_1(value1: Int?) {
    var value3: Int
    if (contracts.case_1(value1) { value3 = 10 }) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        println(value3)
    } else {
        println(value3)
    }
}

// CASE KEYWORDS: notNullSmartcast, nullableBooleanSmartCast, smartValInitialization, smartValUsage
fun case_2(value1: Int?, value2: Any?) {
    var value3: Int
    if (value1.case_2(value1, value2) { value3 = 10 } != null) {
        <!DEBUG_INFO_SMARTCAST!>value1<!>.inv()
        println(value3)
    } else {
        if (value1.case_2(value1, value2) { value3 = 10 } == false) {
            println(<!DEBUG_INFO_SMARTCAST!>value2<!>?.xor(true))
            println(value3)
            println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
        }
    }
}
