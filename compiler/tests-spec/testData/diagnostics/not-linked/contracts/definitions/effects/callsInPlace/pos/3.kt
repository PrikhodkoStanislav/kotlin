// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 3
 DESCRIPTION: functions with contract and callsInPlace with dynamic InvocationKind.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26152
 */

import kotlin.internal.contracts.*

internal val invocationKind: InvocationKind = InvocationKind.EXACTLY_ONCE

internal object SampleObject {
    val invocationKind = InvocationKind.EXACTLY_ONCE
}

// CASE DESCRIPTION: invocationKind as function argument
internal inline fun case_1(invocationKind: InvocationKind, block: () -> Unit) {
    contract {
        callsInPlace(block, invocationKind)
    }
    return block()
}

// CASE DESCRIPTION: invocationKind as function argument with generic type (InvocationKind as super-type)
inline fun <T : InvocationKind> case_2(invocationKind: T, block: () -> Unit) {
    contract {
        callsInPlace(block, invocationKind)
    }
    return block()
}

// CASE DESCRIPTION: invocationKind as top-level val
internal inline fun case_3(block: () -> Unit) {
    contract {
        callsInPlace(block, invocationKind)
    }
    return block()
}

// CASE DESCRIPTION: invocationKind as val from object
internal inline fun case_4(block: () -> Unit) {
    contract {
        callsInPlace(block, SampleObject.invocationKind)
    }
    return block()
}

