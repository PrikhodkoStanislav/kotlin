// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: descriptions, contract-builder
 NUMBER: 5
 DESCRIPTION: Functions with contracts and external contract builder.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26186
 */

import kotlin.internal.contracts.*

internal inline fun contractBuilder(block: () -> Unit): ContractBuilder.() -> Unit = {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
}

// CASE DESCRIPTION: ContractBuilder as contract argument in the parentheses
internal inline fun case_1(block: () -> Unit) {
    contract(contractBuilder(block))
    return block()
}

// CASE DESCRIPTION: ContractBuilder as contract argument in the parentheses and used explicitly name parameter
internal inline fun case_2(block: () -> Unit) {
    contract(builder = contractBuilder(block))
    return block()
}
