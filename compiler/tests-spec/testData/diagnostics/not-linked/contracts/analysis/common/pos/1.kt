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

/*
 CASE KEYWORDS:
    fun:inline
    effectsDefinition
        2
        returns:true:implies:notNullCheck
        callsInPlace:exactlyOnce
 */
inline fun case_1(value_1: Int?, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
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
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returns(true) implies (value_1 != null && this@case_2 != null && value_2 is Boolean?)
        returns(false) implies (value_2 !is Boolean?)
        returns(null) implies ((value_1 == null || this@case_2 == null) && value_2 is Boolean?)
    }

    block()

    if (value_1 != null && this != null && value_2 is Boolean?) return true
    if (value_2 !is Boolean?) return false

    return null
}

// FILE: usages.kt

import contracts.*

/*
 CASE KEYWORDS:
    effectsUsage
        returns:true
        callsInPlace:exactlyOnce
    smartInit:val
    smartcast:notNull
    if:else
 */
fun case_1(value_1: Int?) {
    val value_3: Int
    if (contracts.case_1(value_1) { value_3 = 10 }) {
        <!DEBUG_INFO_SMARTCAST!>value_1<!>.inv()
        println(value_3)
    } else {
        println(value_3)
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsNotNull
        returns:false
        callsInPlace:atLeastOnce
        nested
    smartInit:var
    smartcast:notNull,nullableBoolean
    if:else,nested
 */
fun case_2(value_1: Int?, value_2: Int?, value_3: Any?) {
    val value_4: Int

    when (value_1.case_2(value_2, value_3) { value_4 = 10 }) {
        true -> {
            println(<!DEBUG_INFO_SMARTCAST!>value_3<!>?.xor(true))
            println(value_4)
            println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inv())
            println(<!DEBUG_INFO_SMARTCAST!>value_2<!>.inv())
        }
        false -> {
            println(value_4)
            println(value_1)
            println(value_2)
        }
        null -> {
            println(<!DEBUG_INFO_SMARTCAST!>value_3<!>?.xor(true))
            println(value_4)
            println(value_1)
            println(value_2)
        }
    }
    println(value_4)
}
