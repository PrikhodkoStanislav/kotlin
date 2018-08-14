// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (POSITIVE)

 SECTION: XX.XX Contracts
 CATEGORY: declarations
 NUMBER: 1
 DESCRIPTION: Check that fun with contract and callsInPlace effect (EXACTLY_ONCE) is an inline function.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26126
 */

import kotlin.internal.contracts.*

fun funWithContractExactlyOnce(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun case_1() {
    val tt: Int
    funWithContractExactlyOnce { tt = 10 }
    tt.inc()
}