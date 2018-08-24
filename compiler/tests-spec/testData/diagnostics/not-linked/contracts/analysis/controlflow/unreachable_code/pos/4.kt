// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 4
 DESCRIPTION: Unreachable code detection based on the nested contract functions with callsInPlace effect
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nested
    throw
    unrechableCode
 */
fun case_1() {
    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                throw Exception()
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
        <!UNREACHABLE_CODE!>println("2")<!>
    }
    <!UNREACHABLE_CODE!>println("3")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nested
    unrechableCode
    return:nonlocal
    lambda:labeled
 */
fun case_2() {
    funWithAtLeastOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace label_1@ {
            funWithAtLeastOnceCallsInPlace {
                return@label_1
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
        println("2")
    }
    funWithAtLeastOnceCallsInPlace {
        return
    }
    <!UNREACHABLE_CODE!>println("3")<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nested
    unrechableCode
    return:local,labelClash
 */
fun case_3() {
    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                return<!LABEL_NAME_CLASH!>@funWithExactlyOnceCallsInPlace<!>
            }
            println("1")
        }
        println("2")
    }
    println("3")
}
