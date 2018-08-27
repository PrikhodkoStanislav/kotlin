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
    val value_1: Int

    funWithExactlyOnceCallsInPlace { value_1 = 10 }

    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce
    smartInit:var
    smartcast:inited
 */
fun case_2() {
    var value_1: Int
    var value_2: Int

    funWithExactlyOnceCallsInPlace { value_1 = 10 }
    funWithAtLeastOnceCallsInPlace { value_2 = 10 }

    value_1.dec()
    value_2.div(10)
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
    val value_1: Int
    var value_2: Int
    var value_3: Int

    init {
        funWithExactlyOnceCallsInPlace { value_1 = 1 }
        funWithExactlyOnceCallsInPlace { value_2 = 2 }
        funWithAtLeastOnceCallsInPlace { value_3 = 3 }
    }
}
