// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 1
 DESCRIPTION: Simple val/var assignments in contract functions with 'call in place' effect and subsequent usages
 */

// CASE DESCRIPTION: val initialization and subsequent usage
fun case_1() {
    val value: Int

    funWithExactlyOnceCallsInPlace { value = 10 }

    value.inc()
}

// CASE DESCRIPTION: var initialization and subsequent usage
fun case_2() {
    var value1: Int
    var value2: Int

    funWithExactlyOnceCallsInPlace { value1 = 10 }
    funWithAtLeastOnceCallsInPlace { value2 = 10 }

    value1.dec()
    value2.div(10)
}

// CASE DESCRIPTION: class val/var fields initialization
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
