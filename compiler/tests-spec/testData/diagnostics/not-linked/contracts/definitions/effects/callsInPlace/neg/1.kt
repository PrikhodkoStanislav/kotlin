// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 7
 DESCRIPTION: Check for lack of callsInPlace effect on the not last function parameter lambda.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26229
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: two callsInPlace effects — on the first and last function parameter lambda
private inline fun case_1_funWithContract(block1: () -> Unit, block2: () -> Unit) {
    contract {
        callsInPlace(block1, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block2, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
private inline fun case_1() {
    val t1: Int
    val t2: Int
    funWithContract({
        t1 = 11 // reassignment
    }) {
        t2 = 10
    }
    t1.inc() // must be initialized
    t2.inc()
}

// CASE DESCRIPTION: one callsInPlace effects on the not last function parameter lambda
private inline fun case_2_funWithContract(block1: () -> Unit, number: Int) {
    contract {
        callsInPlace(block1, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
private inline fun case_2() {
    val t1: Int
    case_2_funWithContract({
        t1 = 11 // reassignment
    }, 100)
    t1.inc() // must be initialized
}
