// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract-builder
 NUMBER: 8
 DESCRIPTION: Contract with callsInPlace effect on the not last function parameter lambda.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26229
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: two callsInPlace effects — on the first and last function parameter lambda
private inline fun case_1(block1: () -> Unit, block2: () -> Unit) {
    contract {
        callsInPlace(block1, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block2, InvocationKind.EXACTLY_ONCE)
    }
    block1()
    block2()
}

// CASE DESCRIPTION: one callsInPlace effects on the not last function parameter lambda
private inline fun case_2(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
