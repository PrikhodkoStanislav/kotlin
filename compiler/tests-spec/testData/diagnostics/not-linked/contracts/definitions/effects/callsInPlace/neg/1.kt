// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 1
 DESCRIPTION: Check for lack of callsInPlace effect on the not last function parameter lambda.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26229
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: two callsInPlace effects — on the first and last function parameter lambda
private inline fun case_1_funWithContract(block_1: () -> Unit, block_2: () -> Unit) {
    contract {
        callsInPlace(block_1, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block_2, InvocationKind.EXACTLY_ONCE)
    }
    block_1()
    block_2()
}
private fun case_1() {
    val value_1: Int
    val value_2: Int
    case_1_funWithContract({
        <!CAPTURED_VAL_INITIALIZATION!>value_1<!> = 11
    }) {
        value_2 = 10
    }
    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
    value_2.inc()
}

// CASE DESCRIPTION: one callsInPlace effects on the not last function parameter lambda
private inline fun case_2_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
private fun case_2() {
    val value_1: Int
    case_2_funWithContract({
        <!CAPTURED_VAL_INITIALIZATION!>value_1<!> = 11 // reassignment
    })
    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc() // must be initialized
}
