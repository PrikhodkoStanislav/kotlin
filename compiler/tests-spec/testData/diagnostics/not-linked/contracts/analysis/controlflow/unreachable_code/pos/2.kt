// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 2
 DESCRIPTION: Check for lack of unreachable code report when 'at most once' and 'unknown' calls in place effect used.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: lack of unreachable code with 'exactly once' calls in place effect
inline fun case_1_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
}
fun case_1() {
    case_1_funWithContract {
        throw Exception()
    }
    case_1_funWithContract {
        return
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'at least once' calls in place effect
inline fun case_2_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }
    block()
}
fun case_2() {
    case_2_funWithContract {
        throw Exception()
    }
    case_1_funWithContract {
        return
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'exactly once' calls in place effect and local return
inline fun case_3_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
fun case_3() {
    case_3_funWithContract {
        return@case_3_funWithContract
    }
    println("1")
    case_3_funWithContract {
        fun nestedFun() {
            return@nestedFun
        }
    }
    println("1")
    fun case_3_nestedFun_1() {
        fun case_3_nestedFun_2() {
            case_3_funWithContract {
                return@case_3_nestedFun_2
            }
        }
        println("1")
    }
    println("1")
}

// CASE DESCRIPTION: lack of unreachable code with 'at least once' calls in place effect and local return
inline fun case_4_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
}
fun case_4() {
    case_4_funWithContract {
        return@case_4_funWithContract
    }
    println("1")
    case_4_funWithContract {
        fun nestedFun() {
            return@nestedFun
        }
    }
    println("1")
    fun case_4_nestedFun_1() {
        fun case_4_nestedFun_2() {
            case_4_funWithContract {
                return@case_4_nestedFun_2
            }
        }
        println("1")
    }
    println("1")
}
