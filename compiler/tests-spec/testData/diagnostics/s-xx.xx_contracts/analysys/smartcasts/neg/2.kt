// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION: XX.XX Contracts
 CATEGORY: analysys, smartcasts
 NUMBER: 2
 DESCRIPTION: Check the lack of smartcasts after non-null assertions or assignment in lambdas with contract and 'at most once' or 'unknown' CallsInPlace effects.
 */

inline fun funWithContractAtMostOnce(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return block()
}

inline fun funWithContractUnknown(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }
    return block()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'at most once' CallsInPlace effect.
fun case_1(arg: Int?) {
    funWithContractAtMostOnce { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'unknown' CallsInPlace effect.
fun case_2(arg: Int?) {
    funWithContractUnknown { arg!! }

    arg.inc()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'at most once' CallsInPlace effect.
fun case_3(arg: Boolean?) {
    funWithContractAtMostOnce { arg = false }

    arg.not()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'unknown' CallsInPlace effect.
fun case_4(arg: Int?) {
    funWithContractUnknown { arg = true }

    arg.not()
}
