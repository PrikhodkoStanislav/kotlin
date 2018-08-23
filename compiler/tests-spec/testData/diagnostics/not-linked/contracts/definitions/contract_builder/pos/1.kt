// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 1
 DESCRIPTION: Functions with contracts written simply.
 */

import kotlin.internal.contracts.*

/*
 CASE DESCRIPTION: empty contract
 DISCUSSION: whether to allow empty nonsensical contracts?
 */
inline fun case_1(block: () -> Unit) {
    contract { }
    return block()
}

/*
 CASE DESCRIPTION: simple contract with one effect
 */
inline fun case_2(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

/*
 CASE DESCRIPTION: simple contract with two effects
 */
inline fun case_3(value: Int?, block: () -> Unit): Boolean {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returns(true) implies (value != null)
    }

    block()

    return value != null
}

/*
 CASE DESCRIPTION: simple contract with four effects
 */
inline fun <T> T?.case_4(value1: Int?, value2: Boolean, value3: Int?, block: () -> Unit): Boolean? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        returns(true) implies (value1 != null)
        returns(false) implies (!value2)
        returnsNotNull() implies (this@case_4 != null && value3 != null)
    }

    block()

    return value1 != null
}
