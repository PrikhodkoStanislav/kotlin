// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 1
 DESCRIPTION: Unreachable code detection based on the local functions or labdas combined with contract functions with callsInPlace effect
 */

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect
fun case_1(cond: Boolean) {
    while (cond) {
        funWithExacltyOnceCallsInPlace {
            <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>break<!>
        }
        println("1")
    }

    loop@ for (i in 0..10) {
        funWithExacltyOnceCallsInPlace {
            <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>break@loop<!>
        }
        println("1")
    }
}

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect
fun case_2(cond: Boolean) {
    for (i in 0..10) {
        funWithExacltyOnceCallsInPlace {
            <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>continue<!>
        }
        println("1")
    }

    loop@ while (cond) {
        funWithExacltyOnceCallsInPlace {
            <!BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY!>continue@loop<!>
        }
        println("1")
    }
}
