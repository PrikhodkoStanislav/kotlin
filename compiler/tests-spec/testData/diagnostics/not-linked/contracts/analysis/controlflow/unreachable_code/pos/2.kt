// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 2
 DESCRIPTION: Check for lack of unreachable code report when 'at most once' and 'unknown' calls in place effect used.
 */

// CASE DESCRIPTION: lack of unreachable code with 'exactly once' calls in place effect
fun case_1() {
    funWithAtMostOnceCallsInPlace {
        throw Exception()
    }
    funWithAtMostOnceCallsInPlace {
        return
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'at least once' calls in place effect
fun case_2() {
    funWithUnknownCallsInPlace {
        throw Exception()
    }
    funWithUnknownCallsInPlace {
        return
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'exactly once' calls in place effect and local return
fun case_3() {
    funWithExacltyOnceCallsInPlace {
        return@funWithExacltyOnceCallsInPlace
    }
    println("1")
    funWithExacltyOnceCallsInPlace {
        fun nestedFun() {
            return@nestedFun
        }
    }
    println("1")
    fun case_3_nestedFun_1() {
        fun case_3_nestedFun_2() {
            funWithExacltyOnceCallsInPlace {
                return@case_3_nestedFun_2
            }
        }
        println("1")
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'at least once' calls in place effect and local return
fun case_4() {
    funWithAtLeastOnceCallsInPlace {
        return@funWithAtLeastOnceCallsInPlace
    }
    println("1")
    funWithAtLeastOnceCallsInPlace {
        fun nestedFun() {
            return@nestedFun
        }
    }
    println("1")
    fun case_4_nestedFun_1() {
        fun case_4_nestedFun_2() {
            funWithAtLeastOnceCallsInPlace {
                return@case_4_nestedFun_2
            }
        }
        println("1")
    }
    println("1")
}
