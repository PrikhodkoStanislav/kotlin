// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION: XX.XX Contracts
 CATEGORY: declarations
 NUMBER: 1
 DESCRIPTION: Check that contracts does not supported for lambdas not in the last position or with parentheses.
 */

import kotlin.internal.contracts.*

inline fun funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

inline fun funWithContract(block: () -> Unit, number: Int) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: lambdas with non-null assertions and exactly once call contract.
fun case_1(arg: Int?) {
    funWithContractExactlyOnce { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and at least once call contract.
fun case_2(arg: Int?) {
    funWithContractAtLeastOnce { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with not-null assignment and exactly once call contract.
fun case_3(arg: Boolean?) {
    funWithContractExactlyOnce { arg = false }

    arg.not()
}

// CASE DESCRIPTION: lambdas with not-null assignment and at least once call contract.
fun case_4(arg: Int?) {
    funWithContractAtLeastOnce { arg = true }

    arg.not()
}
