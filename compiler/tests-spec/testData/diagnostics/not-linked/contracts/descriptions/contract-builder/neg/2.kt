// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: descriptions, contract-builder
 NUMBER: 5
 DESCRIPTION: Functions with contract and external contract builder.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26186
 */

internal inline fun effectBuilder(block: () -> Unit) =
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)

internal inline fun case_1(block: () -> Unit) {
    contract { effectBuilder(block) }
    return block()
}
