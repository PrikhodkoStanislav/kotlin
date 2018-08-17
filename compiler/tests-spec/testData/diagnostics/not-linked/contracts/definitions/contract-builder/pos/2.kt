// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE
// !WITH_FUNS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract-builder
 NUMBER: 2
 DESCRIPTION: Contract is first statement in control flow terms, but not in tokens order terms.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26153
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: contract in return expression
internal inline fun case_1(block: () -> Unit) {
    return contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
}

// CASE DESCRIPTION: empty contract as function result expression
fun case_2() = contract { }

// CASE DESCRIPTION: first statement is assignment with contract
inline fun case_3(block: () -> Unit) {
    val r = contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

// CASE DESCRIPTION: contract in parentheses
inline fun case_4(block: () -> Unit) {
    (contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    })
    return block()
}

// CASE DESCRIPTION: label before contract
inline fun case_5(block2: () -> Unit) {
    <!REDUNDANT_LABEL_WARNING!>test@<!> contract {
        callsInPlace(block2, InvocationKind.EXACTLY_ONCE)
    }
    return block2()
}

// CASE DESCRIPTION: contract as Exception argement in the throw expression
inline fun case_6(block: () -> Unit) {
    throw Exception(contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }.toString())
}

// CASE DESCRIPTION: call custom function with contract as argument
inline fun case_7(block: () -> Unit) {
    _funWithAnyArg(contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    })
}

inline fun case_8_exactlyOnceContractBuilder(block2: () -> Unit) = {
    contract {
        callsInPlace(block2, InvocationKind.EXACTLY_ONCE)
    }
}

// CASE DESCRIPTION: use custom function to contract constructing
inline fun case_8(block2: () -> Unit) {
    case_8_exactlyOnceContractBuilder(block2)()
    return block2()
}
