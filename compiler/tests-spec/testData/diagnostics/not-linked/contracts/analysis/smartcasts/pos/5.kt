// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 5
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions on receiver.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_1_funWithContract() {
    contract {
        returns() implies (this@case_1_funWithContract != null && this@case_1_funWithContract is String)
    }
    if (this@case_1_funWithContract !is String) throw Exception()
}
fun case_1(value1: Any?) {
    value1.case_1_funWithContract()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T : Number?> T.case_2_funWithContract() {
    contract {
        returns() implies (this@case_2_funWithContract is Int && <!SENSELESS_COMPARISON!>this@case_2_funWithContract != null<!>)
    }
    if (!(this is Int && <!SENSELESS_COMPARISON!>this != null<!>)) throw Exception()
}
fun case_2(value1: Number?) {
    value1.case_2_funWithContract()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun <reified T : Any?> T?.case_3_funWithContract() {
    contract {
        returns() implies (this@case_3_funWithContract is Number && this@case_3_funWithContract is Int && <!SENSELESS_COMPARISON!>this@case_3_funWithContract != null<!>)
    }
    if (!(this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>)) throw Exception()
}
fun case_3(value1: Any?) {
    value1.case_3_funWithContract()
    println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_4_funWithContract_1 != null && this@case_4_funWithContract_1 is String)
    }
    return this != null && this is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_4_funWithContract_2 != null && this@case_4_funWithContract_2 is String)
    }
    return this != null && this is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_4_funWithContract_3 != null && this@case_4_funWithContract_3 is String)
    }
    return this != null && this is String
}
fun case_4(value1: Any?, value2: Any?, value3: Any?) {
    when {
        value1.case_4_funWithContract_1() -> println(<!DEBUG_INFO_SMARTCAST!>value1<!>.length)
    }
    when {
        !value2.case_4_funWithContract_2() -> println(<!DEBUG_INFO_SMARTCAST!>value2<!>.length)
    }
    when {
        value3.case_4_funWithContract_3() != null -> println(<!DEBUG_INFO_SMARTCAST!>value3<!>.length)
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun <T : Number?> T.case_5_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_5_funWithContract_1 is Int && <!SENSELESS_COMPARISON!>this@case_5_funWithContract_1 != null<!>)
    }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun <T : Number?> T.case_5_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_5_funWithContract_2 is Int && <!SENSELESS_COMPARISON!>this@case_5_funWithContract_2 != null<!>)
    }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun <T : Number?> T.case_5_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_5_funWithContract_3 is Int && <!SENSELESS_COMPARISON!>this@case_5_funWithContract_3 != null<!>)
    }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun case_5(value1: Number?, value2: Number?, value3: Number?) {
    if (value1.case_5_funWithContract_1())
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    if (!value2.case_5_funWithContract_2())
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.inv())
    if (value3.case_5_funWithContract_3() != null)
        println(<!DEBUG_INFO_SMARTCAST!>value3<!>.inv())
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
inline fun <reified T : Any?> T?.case_6_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_6_funWithContract_1 is Number && this@case_6_funWithContract_1 is Int && <!SENSELESS_COMPARISON!>this@case_6_funWithContract_1 != null<!>)
    }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
inline fun <reified T : Any?> T?.case_6_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_6_funWithContract_2 is Number && this@case_6_funWithContract_2 is Int && <!SENSELESS_COMPARISON!>this@case_6_funWithContract_2 != null<!>)
    }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
inline fun <reified T : Any?> T?.case_6_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_6_funWithContract_3 is Number && this@case_6_funWithContract_3 is Int && <!SENSELESS_COMPARISON!>this@case_6_funWithContract_3 != null<!>)
    }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun case_6(value1: Any?, value2: Any?, value3: Any?) {
    if (value1.case_6_funWithContract_1())
        println(<!DEBUG_INFO_SMARTCAST!>value1<!>.inv())
    if (!value2.case_6_funWithContract_2())
        println(<!DEBUG_INFO_SMARTCAST!>value2<!>.inv())
    if (value3.case_6_funWithContract_3() != null)
        println(<!DEBUG_INFO_SMARTCAST!>value3<!>.inv())
}
