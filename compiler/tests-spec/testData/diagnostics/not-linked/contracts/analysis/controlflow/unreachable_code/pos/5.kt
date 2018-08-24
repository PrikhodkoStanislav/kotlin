// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 5
 DESCRIPTION: Unreachable code detection based on the contract functions with complex control flow inside
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    unrechableCode
    return
    throw
    when:exhaustive
    smartcast:notNull
 */
fun case_1(b: Boolean?) {
    funWithExactlyOnceCallsInPlace {
        if (b == null) {
            return
        }

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
        <!UNREACHABLE_CODE!>println(3)<!>
    }
    <!UNREACHABLE_CODE!>println(3)<!>
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
        nested
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
                            else -> funWithAtLeastOnceCallsInPlace { return }
                        }
                        <!UNREACHABLE_CODE!>println(3)<!>
                    }
                    <!UNREACHABLE_CODE!>println(3)<!>
                } else {
                    throw Exception()
                }
                <!UNREACHABLE_CODE!>println(3)<!>
            }
        }
        <!UNREACHABLE_CODE!>println(3)<!>
    }
    <!UNREACHABLE_CODE!>println(3)<!>
}
