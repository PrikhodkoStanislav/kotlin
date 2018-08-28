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
        returns(true) implies (value_1 != null && this@case_2 != null && value_2 is Boolean?)
        returns(false) implies (value_1 != null && this@case_2 != null && value_2 !is Boolean?)
        returns(null) implies (value_1 != null && this@case_2 == null && value_2 is Boolean?)
        returns(false) implies (value_1 != null && this@case_2 == null && value_2 !is Boolean?)
        returns(null) implies (value_1 == null && this@case_2 != null && value_2 is Boolean?)
        returns(false) implies (value_1 == null && this@case_2 != null && value_2 !is Boolean?)
        returns(null) implies (value_1 == null && this@case_2 == null && value_2 is Boolean?)
        returns(false) implies (value_1 == null && this@case_2 == null && value_2 !is Boolean?)
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

    when (value_1.case_2(value_1, value_2) { value_3 = 10 }) {
        true -> {
            println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>xor<!>(true))
            println(<!UNINITIALIZED_VARIABLE!>value_3<!>)
            println(value_1<!UNSAFE_CALL!>.<!>inv())
        }
        false -> value_1<!UNSAFE_CALL!>.<!>inv()
        else -> println(value_3)
    }
}
