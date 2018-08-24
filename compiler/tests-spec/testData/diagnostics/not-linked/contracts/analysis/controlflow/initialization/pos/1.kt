// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 1
 DESCRIPTION: Simple val/var assignments in contract functions with 'call in place' effect and subsequent usages
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    smartInit:val
    smartcast:inited
 */
fun case_1() {
    val value: Int

    funWithExactlyOnceCallsInPlace { value = 10 }

    value.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce
    smartInit:var
    smartcast:inited
 */
fun case_2() {
    var value1: Int
    var value2: Int

    funWithExactlyOnceCallsInPlace { value1 = 10 }
    funWithAtLeastOnceCallsInPlace { value2 = 10 }

    value1.dec()
    value2.div(10)
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce
    smartInit:var,val
    class:
        fields:init
        init
 */
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
