// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION: XX.XX Contracts
 CATEGORY: declarations
 NUMBER: 1
 DESCRIPTION: Empty 'when' with bound value.
 */

import kotlin.internal.contracts.*

fun myRun(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun implicitCastWithIf() {
    val tt: Int
    myRun {
        tt = 10
    }
}

fun main(args : Array<String>) {
    implicitCastWithIf()
}