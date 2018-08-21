// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, callsInPlace
 NUMBER: 4
 DESCRIPTION: Contract with this in first parameter of callsInPlace.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26294
 */

import kotlin.internal.contracts.*

inline fun <T : Function<*>> T.myLet(block: () -> Unit) {
    contract {
        callsInPlace(this@myLet, InvocationKind.EXACTLY_ONCE)
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    (this@myLet as kotlin.reflect.KFunction<*>).call()
}
