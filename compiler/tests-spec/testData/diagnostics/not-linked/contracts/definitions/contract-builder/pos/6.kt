// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract-builder
 NUMBER: 6
 DESCRIPTION: Functions with contracts and external effect builder.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26186
 */

import kotlin.internal.contracts.*

internal inline fun ContractBuilder.callsInPlaceEffectBuilder(block: () -> Unit) =
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)

internal fun ContractBuilder.returnsEffectBuilder(value: Int?) =
    returns(true) implies (value != null)

// CASE DESCRIPTION: contract with one effect as result of external function in the parentheses using with parameter name
internal inline fun case_1(block: () -> Unit) {
    contract(builder = { callsInPlaceEffectBuilder(block) })
    return block()
}

// CASE DESCRIPTION: contract with one effect as result of external function outside the parentheses
internal inline fun case_2(block: () -> Unit) {
    contract { callsInPlaceEffectBuilder(block) }
    return block()
}

// CASE DESCRIPTION: contract with two effects as result of external functions outside in the parentheses
internal inline fun case_3(value: Int?, block: () -> Unit) {
    contract({ returnsEffectBuilder(value); callsInPlaceEffectBuilder(block) })
    return block()
}
