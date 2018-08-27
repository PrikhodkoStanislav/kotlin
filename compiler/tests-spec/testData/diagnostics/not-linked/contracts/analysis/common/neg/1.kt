// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, common
 NUMBER: 1
 DESCRIPTION: Analysis by contracts with mixed various effects.
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

/*
 CASE KEYWORDS:
    fun:inline
    effectsDefinition
        2
        returns:true:implies:notNullCheck
        callsInPlace:atMostOnce
 */
inline fun case_1(value_1: Int?, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        returns(true) implies (value_1 != null)
    }

    block()

    return value_1 != null
}

/*
 CASE KEYWORDS:
    fun:inline,extension
    effectsDefinition
        4
        returnsNotNull:implies:notNullCheck
        returns:false:implies:nullableBooleanTypeCheck,receiverNotNullCheck
        callsInPlace:atMostOnce
 */
inline fun <T> T?.case_2(value_1: Int?, value_2: Any?, block: () -> Unit): Boolean? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        returnsNotNull() implies (value_1 != null)
        returns(false) implies (value_2 is Boolean?)
        returns(false) implies (this@case_2 != null)
    }

    block()

    if (value_1 == null) return null
    if (value_2 is Boolean? || this != null) return false

    return true
}

// FILE: usages.kt

import contracts.*

/*
 CASE KEYWORDS:
    effectsUsage
        returns:true
        callsInPlace:atMostOnce
    uninitialized:val
    unsafeCall
    smartInit:val
    smartcast:notNull
    if:else
 */
fun case_1(value_1: Int?) {
    val value_2: Int
    if (contracts.case_1(value_1) { value_2 = 10 }) {
        println(<!UNINITIALIZED_VARIABLE!>value_2<!>)
    } else {
        value_1<!UNSAFE_CALL!>.<!>inv()
        println(value_2)
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsNotNull
        returns:false
        callsInPlace:atMostOnce
        nested
    smartInit:var
    smartcast:notNull,nullableBoolean
    uninitialized:val
    unsafeCall
    if:else,nested
 */
fun case_2(value_1: Int?, value_2: Any?) {
    var value_3: Int
    if (value_1.case_2(value_1, value_2) { value_3 = 10 } != null) {
        if (value_1.case_2(value_1, value_2) { value_3 = 10 } == false) {
            println(<!DEBUG_INFO_SMARTCAST!>value_2<!>?.xor(true))
            println(<!UNINITIALIZED_VARIABLE!>value_3<!>)
            println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
        }
        println(value_3)
    } else {
        value_1<!UNSAFE_CALL!>.<!>inv()
    }
}
