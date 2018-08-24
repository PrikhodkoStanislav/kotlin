// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: definitions, common
 NUMBER: 1
 DESCRIPTION: Check that recursion is forbidden in contract functions with callsInPlace effect.
 */

import kotlin.internal.contracts.*

inline fun case_1(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    block()
    <!RECURSION_IN_INLINE!>case_1<!>(block)
}