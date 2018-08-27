// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -UNUSED_VARIABLE
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 1
 DESCRIPTION: Unreachable code detection based on the callsInPlace effect
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    throw
    unrechableCode
 */
fun case_1() {
    funWithExactlyOnceCallsInPlace {
        throw Exception()
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
    throw
    unrechableCode
 */
fun case_2() {
    funWithAtLeastOnceCallsInPlace {
        throw Exception()
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    return:nonlocal
    unrechableCode
 */
fun case_3() {
    funWithExactlyOnceCallsInPlace {
        return
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
    return:nonlocal
    unrechableCode
 */
fun case_4() {
    funWithAtLeastOnceCallsInPlace {
        return
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
    return:labeled,nonlocal
    unrechableCode
    fun:nested
    lambda
 */
fun case_5(args: Array<String>) {
    fun case_5_nestedFun_1() {
        funWithAtLeastOnceCallsInPlace {
            return@case_5_nestedFun_1
        }
        <!UNREACHABLE_CODE!>println("1")<!>
    }
    fun case_5_nestedFun_3() {
        args.forEach {
            funWithAtLeastOnceCallsInPlace {
                return@forEach
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
    fun case_5_nestedFun_5() {
        fun case_5_nestedFun_6() {
            funWithAtLeastOnceCallsInPlace {
                return@case_5_nestedFun_6
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
        println("1")
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    return:labeled,nonlocal
    unrechableCode
    fun:nested
    lambda
 */
fun case_6(args: Array<String>) {
    args.forEach {
        funWithExactlyOnceCallsInPlace {
            return@forEach
        }
        <!UNREACHABLE_CODE!>println("1")<!>
    }
    args.forEach {
        fun case_6_nestedFun_2() {
            funWithExactlyOnceCallsInPlace {
                return@case_6_nestedFun_2
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
    args.forEach {
        fun case_6_nestedFun_3() {
            funWithExactlyOnceCallsInPlace {
                return
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
}

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect and assignment function contract result
fun case_7() {
    <!UNREACHABLE_CODE!>val value_1 =<!> funWithExactlyOnceCallsInPlace {
        throw Exception()
        <!UNREACHABLE_CODE!>println(1)<!>
    }
    <!UNREACHABLE_CODE!>println(value_1)<!>
}

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect and use function contract result as argument

fun case_8() {
    <!UNREACHABLE_CODE!>println(<!>funWithExactlyOnceCallsInPlace { return; <!UNREACHABLE_CODE!>println(1)<!> }<!UNREACHABLE_CODE!>)<!>
    <!UNREACHABLE_CODE!>return<!>
}
