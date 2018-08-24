// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 6
 DESCRIPTION: Check for lack of unreachable code report when in complex control flow of contract function lambda not all branches are doing non-local return
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    unrechableCode
    return:local,nonlocal
    smartcast:notNull
    throw
    when:exhaustive
    if:else
 */
fun case_1(b: Boolean?, c: Boolean) {
    funWithExactlyOnceCallsInPlace {
        if (b == null) {
            return
        }

        try {
            <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (<!DEBUG_INFO_SMARTCAST!>b<!>) {
                true -> {
                    println(1)
                    return
                }
                false -> {
                    println(2)
                    throw Exception()
                }
            }<!>
        } catch (e: Exception) {
            if (c) {
                return@funWithExactlyOnceCallsInPlace
            } else {
                return
            }
        }
        <!UNREACHABLE_CODE!>println(3)<!>
    }
    println(3)
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,atLeastOnce,exactlyOnce
    unrechableCode
    return
    throw
    when:exhaustive
    if:else
 */
fun case_2(b: Boolean?, c: Boolean) {
    funWithAtLeastOnceCallsInPlace {
        when (b) {
            true -> {
                println(1)
                return
            }
            else -> {
                if (b == null) {
                    funWithExactlyOnceCallsInPlace {
                        when {
                            c == true -> throw Exception()
                            else -> funWithAtMostOnceCallsInPlace { return }
                        }
                        println(3)
                    }
                    println(3)
                } else {
                    throw Exception()
                }
                println(3)
            }
        }
        println(3)
    }
    println(3)
}
