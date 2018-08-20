// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 4
 DESCRIPTION: Functions with contract and builder lambda in parentheses.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: empty contract
inline fun case_1(block: () -> Unit) {
    contract({ })
    return block()
}

// CASE DESCRIPTION: empty contract and used explicitly name parameter
inline fun case_2(block: () -> Unit) {
    contract(builder = { })
    return block()
}

// CASE DESCRIPTION: contract with one effect
inline fun case_3(block: () -> Unit) {
    contract({ callsInPlace(block, InvocationKind.EXACTLY_ONCE) })
    return block()
}

// CASE DESCRIPTION: contract with one effect and explicitly used name parameter
inline fun case_4(block: () -> Unit) {
    contract(builder = { callsInPlace(block, InvocationKind.EXACTLY_ONCE) })
    return block()
}
