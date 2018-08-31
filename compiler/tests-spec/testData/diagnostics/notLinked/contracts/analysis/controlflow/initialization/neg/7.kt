// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 7
 DESCRIPTION: Smart initialization with correspond contract function with default value before lambda.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26444
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun case_1(x: Double = 1.0, block: () -> Unit): Double {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return x
}

// FILE: usages.kt

import contracts.*

fun case_1() {
    val value_1: Int
    contracts.case_1 { <!CAPTURED_VAL_INITIALIZATION!>value_1<!> = 10 }
    <!UNINITIALIZED_VARIABLE!>value_1<!>.inc()
}
