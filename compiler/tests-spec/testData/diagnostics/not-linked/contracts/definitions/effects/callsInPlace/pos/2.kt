// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 2
 DESCRIPTION: functions with contract and duplicate callsInPlace.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26150
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: two callsInPlace effects with same invocation kind
inline fun case_1(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: two callsInPlace effects with different invocation kind
inline fun case_2(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block, InvocationKind.AT_MOST_ONCE) // front-end exception
    }
    return block()
}
