// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 1
 DESCRIPTION: simple val/var assinments
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1() {
    val value: Int

    funWithExactlyOnceCallsInPlace { value = 10 }

    value.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_2() {
    var value1: Int
    var value2: Int

    funWithExactlyOnceCallsInPlace { value1 = 10 }
    funWithAtLeastOnceCallsInPlace { value2 = 10 }

    value1.dec()
    value2.div(10)
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
class case_3 {
    val value1: Int
    var value2: Int
    var value3: Int

    init {
        funWithExactlyOnceCallsInPlace { value1 = 1 }
        funWithExactlyOnceCallsInPlace { value2 = 2 }
        funWithAtLeastOnceCallsInPlace { value3 = 3 }
    }
}
