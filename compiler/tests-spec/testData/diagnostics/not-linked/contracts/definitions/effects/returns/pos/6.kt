// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 6
 DESCRIPTION: Returns effect with type checking with reified parameter
 */

import kotlin.internal.contracts.*

fun <T : Number?> T.case_1() {
    contract {
        returns() implies (<!USELESS_IS_CHECK!>this@case_1 is T<!>)
    }
    if (!(<!USELESS_IS_CHECK!>this@case_1 is T<!>)) throw Exception()
}

fun <T : Number, K : <!FINAL_UPPER_BOUND!>String<!>> T?.case_1(value: K?) {
    contract {
        returns() implies (this@case_1 is T && value is K)
    }
    if (!(this@case_1 is T && value is K)) throw Exception()
}
