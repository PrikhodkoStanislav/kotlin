// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 5
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions on receiver (extension function).
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_1() {
    contract { returns() implies (this@case_1 == null || this@case_1 !is String) }
    if (this@case_1 is String) throw Exception()
}

fun <T : Number?> T.case_2() {
    contract { returns() implies (this@case_2 !is Int || <!SENSELESS_COMPARISON!>this@case_2 == null<!>) }
    if (!(this !is Int || <!SENSELESS_COMPARISON!>this == null<!>)) throw Exception()
}

inline fun <reified T : Any?> T?.case_3() {
    contract { returns() implies (this@case_3 !is Number || this@case_3 !is Int || <!SENSELESS_COMPARISON!>this@case_3 == null<!>) }
    if (!(this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>)) throw Exception()
}

<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_1(): Boolean {
    contract { returns(true) implies (this@case_4_1 == null || this@case_4_1 !is String) }
    return this == null || this !is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_2(): Boolean {
    contract { returns(false) implies (this@case_4_2 == null || this@case_4_2 !is String) }
    return this == null || this !is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_4_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_4_3 == null || this@case_4_3 !is String) }
    return this == null || this !is String
}

fun <T : Number?> T.case_5_1(): Boolean {
    contract { returns(true) implies (this@case_5_1 !is Int || <!SENSELESS_COMPARISON!>this@case_5_1 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}
fun <T : Number?> T.case_5_2(): Boolean {
    contract { returns(false) implies (this@case_5_2 !is Int || <!SENSELESS_COMPARISON!>this@case_5_2 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}
fun <T : Number?> T.case_5_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_5_3 !is Int || <!SENSELESS_COMPARISON!>this@case_5_3 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}

inline fun <reified T : Any?> T?.case_6_1(): Boolean {
    contract { returns(true) implies (this@case_6_1 !is Number || this@case_6_1 !is Int || <!SENSELESS_COMPARISON!>this@case_6_1 == null<!>) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}
inline fun <reified T : Any?> T?.case_6_2(): Boolean {
    contract { returns(false) implies (this@case_6_2 !is Number || this@case_6_2 !is Int || <!SENSELESS_COMPARISON!>this@case_6_2 == null<!>) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}
inline fun <reified T : Any?> T?.case_6_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_6_3 is Number && this@case_6_3 is Int && <!SENSELESS_COMPARISON!>this@case_6_3 != null<!>) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}

// FILE: usages.kt

import contracts.*

fun case_1(value1: Any?) {
    value1.case_1()
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_2(value1: Number?) {
    value1.case_2()
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun case_3(value1: Any?) {
    value1.case_3()
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun case_4(value1: Any?, value2: Any?, value3: Any?) {
    when { value1.case_4_1() -> println(value1.<!UNRESOLVED_REFERENCE!>length<!>) }
    when { !value2.case_4_2() -> println(value2.<!UNRESOLVED_REFERENCE!>length<!>) }
    when { value3.case_4_3() != null -> println(value3.<!UNRESOLVED_REFERENCE!>length<!>) }
}

fun case_5(value1: Number?, value2: Number?, value3: Number?) {
    if (value1.case_5_1()) println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (!value2.case_5_2()) println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_5_3() != null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun case_6(value1: Any?, value2: Any?, value3: Any?) {
    if (value1.case_6_1()) println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (!value2.case_6_2()) println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_6_3() == null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}
