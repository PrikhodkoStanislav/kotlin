// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 4
 DESCRIPTION: Unreachable code detection based on the nested contract functions with callsInPlace effect
 */

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect
fun case_1() {
    funWithExacltyOnceCallsInPlace {
        funWithExacltyOnceCallsInPlace {
            funWithExacltyOnceCallsInPlace {
                throw Exception()
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
        <!UNREACHABLE_CODE!>println("2")<!>
    }
    <!UNREACHABLE_CODE!>println("3")<!>
}

// CASE DESCRIPTION: unreachable code detection with 'at least once' calls in place effect
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

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect
fun case_3() {
    funWithExacltyOnceCallsInPlace {
        funWithExacltyOnceCallsInPlace {
            funWithExacltyOnceCallsInPlace {
                return<!LABEL_NAME_CLASH!>@funWithExacltyOnceCallsInPlace<!>
            }
            println("1")
        }
        println("2")
    }
    println("3")
}
