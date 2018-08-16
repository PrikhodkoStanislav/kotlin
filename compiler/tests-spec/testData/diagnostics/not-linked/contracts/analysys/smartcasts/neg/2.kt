// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysys, smartcasts
 NUMBER: 2
 DESCRIPTION: Check the lack of smartcasts after non-null assertions or assignment in lambdas with contract and 'at most once' or 'unknown' CallsInPlace effects.
 */

import kotlin.internal.contracts.*

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

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'unknown' CallsInPlace effect.
fun case_2(arg: Int?) {
    funWithContractUnknown { arg!! }

    arg<!UNSAFE_CALL!>.<!>inc()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'at most once' CallsInPlace effect.
fun case_3() {
    val value: Boolean?

    funWithContractAtMostOnce { value = false }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}

// CASE DESCRIPTION: lambdas with non-null assignment and 'unknown' CallsInPlace effect.
fun case_4() {
    val value: Boolean?

    funWithContractUnknown { <!VAL_REASSIGNMENT!>value<!> = true }

    <!UNINITIALIZED_VARIABLE!>value<!><!UNSAFE_CALL!>.<!>not()
}
