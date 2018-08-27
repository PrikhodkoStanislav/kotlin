// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, common
 NUMBER: 2
 DESCRIPTION: Check report about use contracts in literal functions or lambdas.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26149
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: contract in literal fun
fun case_1() {
    val literalFunWithContract = fun(block: () -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        return block()
    }

    literalFunWithContract { throw Exception() }
    println("1") // not unreachable code
}

// CASE DESCRIPTION: contract in lambda
fun case_2() {
    val f1 = { block: () -> Unit ->
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        block()
    }

    f1 { throw Exception() }
    println("1") // not unreachable code
}

object case_3 {
    fun case_3(block: () -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        return block()
    }
}

class case_4 {
    fun case_4(block: () -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        return block()
    }
}
