// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER -UNUSED_VARIABLE

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, contract_builder
 NUMBER: 7
 DESCRIPTION: Contract isn't in the first position.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26191
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: contract as part of the 'cast' expression and expression before it
fun case_1(value: Int?) {
    println("!")
    contract {
        returns(true) implies (value != null)
    } <!CAST_NEVER_SUCCEEDS!>as<!> ContractBuilder
}

// CASE DESCRIPTION: contract as argument of the 'throw' expression and expression before it
fun case_2(value: Int?) {
    100 + 10
    throw Exception(contract {
        returns(true) implies (value != null)
    }.toString())
}

// CASE DESCRIPTION: contract in 'return' expression and 'for' statement before it
fun case_3(value: Int?) {
    for (i in 0..10) {
        println(i)
    }
    return contract {
        returns(true) implies (value != null)
    }
}

// CASE DESCRIPTION: contract as right side of the assignment statement and other assignment statement before it
fun case_4(value: Int?) {
    val f = 10 - 20
    val g = contract {
        returns(true) implies (value != null)
    }
}

/*
 CASE DESCRIPTION: contract as part of the 'cast' expression and use local variable in implies argument
 DISCUSSION: there is a report in implies about 'only references to parameters', but 'cond' isn't parameter and it's valid implies parameter.
 */
fun case_5(number: Int?): Boolean {
    val cond = number != null
    contract {
        returns(false) implies (cond)
    } <!CAST_NEVER_SUCCEEDS!>as<!> ContractBuilder
    return number == null
}
