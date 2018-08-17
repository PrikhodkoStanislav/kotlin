// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 1
 DESCRIPTION: functions with contract and callsInPlace effects with different invocation kinds.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: callsInPlace with exactly once invocation kind
inline fun case_1(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: callsInPlace with at most once invocation kind
inline fun case_2(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: callsInPlace with at least once invocation kind
inline fun case_3(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: callsInPlace with unknown invocation kind
inline fun case_4(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }
    return block()
}
