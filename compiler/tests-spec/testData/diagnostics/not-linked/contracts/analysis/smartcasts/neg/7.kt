// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 7
 DESCRIPTION: Using equality with null in function contract (returns effect) and invert condition ('not' operator).
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-1982
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T : Any?> T?.case_4_funWithContract() {
    contract {
        returns() implies (this@case_4_funWithContract is Number || this@case_4_funWithContract is Int)
    }
    if (!(this is Number || this is Int)) throw Exception()
}
fun case_4(value1: Any?) {
    value1.case_4_funWithContract()
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun <reified T : Any?> T?.case_4_funWithContract(value2: Number, value3: Any?, value4: String?) {
    contract {
        returns() implies ((this@case_4_funWithContract is Number || this@case_4_funWithContract is Int) && value2 is Int && value3 != null && value3 is Number && value4 != null)
    }
    if (!((this is Number || this is Int) && value2 is Int && value3 != null && value3 is Number && value4 != null)) throw Exception()
}
fun case_4(value1: Any?, value2: Number, value3: Any?, value4: String?) {
    value1.case_4_funWithContract(value2, value3, value4)
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}
