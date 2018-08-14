// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION: XX.XX Contracts
 CATEGORY: analysys, smartcasts
 NUMBER: 1
 DESCRIPTION: Check smartcasts after non-null assertions or assignment in lambdas with contract and 'exactly once' or 'at least once' CallsInPlace effects.
 UNEXPECTED BEHAVIOUR
 */

inline fun funWithContractExactlyOnce(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

inline fun funWithContractAtLeastOnce(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    return block()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1(arg: Int?) {
    funWithContractExactlyOnce { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'at least once' CallsInPlace effect.
fun case_2(arg: Int?) {
    funWithContractAtLeastOnce { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with not-null assignment and 'exactly once' CallsInPlace effect.
fun case_3(arg: Boolean?) {
    funWithContractExactlyOnce { arg = false }

    arg.not()
}

// CASE DESCRIPTION: lambdas with not-null assignment and 'at least once' CallsInPlace effect.
fun case_4(arg: Int?) {
    funWithContractAtLeastOnce { arg = true }

    arg.not()
}
