// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, unreachable_code
 NUMBER: 3
 DESCRIPTION: Unreachable code detection based on the nested contract functions with callsInPlace effect
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: unreachable code detection with 'exactly once' calls in place effect
inline fun case_1_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
fun case_1() {
    case_1_funWithContract {
        throw Exception()
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

// CASE DESCRIPTION: unreachable code detection with 'at least once' calls in place effect
inline fun case_2_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
    block()
}
fun case_2() {
    case_2_funWithContract {
        throw Exception()
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

// CASE DESCRIPTION: unreachable code with 'exactly once' calls in place effect and non-local return
inline fun case_3_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
fun case_3() {
    case_3_funWithContract {
        return
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

// CASE DESCRIPTION: unreachable code with 'at least once' calls in place effect and non-local return
inline fun case_4_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
}
fun case_4() {
    case_4_funWithContract {
        return
    }
    <!UNREACHABLE_CODE!>println("1")<!>
}

// CASE DESCRIPTION: unreachable code with 'at least once' calls in place effect and explicit labeled return to nested function
inline fun case_5_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
}
fun case_5(args: Array<String>) {
    fun case_5_nestedFun_1() {
        case_5_funWithContract {
            return@case_5_nestedFun_1
        }
        <!UNREACHABLE_CODE!>println("1")<!>
    }
    fun case_5_nestedFun_3() {
        args.forEach {
            case_6_funWithContract {
                return@forEach
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
    fun case_5_nestedFun_5() {
        fun case_5_nestedFun_6() {
            case_6_funWithContract {
                return@case_5_nestedFun_6
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
        println("1")
    }
}

// CASE DESCRIPTION: unreachable code with 'at least once' calls in place effect and explicit labeled return to lambda
inline fun case_6_funWithContract(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}
fun case_6(args: Array<String>) {
    args.forEach {
        case_6_funWithContract {
            return@forEach
        }
        <!UNREACHABLE_CODE!>println("1")<!>
    }
    args.forEach {
        fun case_6_nestedFun_2() {
            case_6_funWithContract {
                return@case_6_nestedFun_2
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
    args.forEach {
        fun case_6_nestedFun_3() {
            case_6_funWithContract {
                return
            }
            <!UNREACHABLE_CODE!>println("1")<!>
        }
    }
}
