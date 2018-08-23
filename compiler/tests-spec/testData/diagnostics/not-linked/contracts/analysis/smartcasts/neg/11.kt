// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 11
 DESCRIPTION: Check smartcasts if double negation used (returnsFalse/invert type checking/not operator).
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26176
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

// CASE KEYWORDS: returnsTrue, numberInvertTypeCheck
fun case_1(x: Any?): Boolean {
    contract { returns(true) implies (x !is Number) }
    return x !is Number
}

// CASE KEYWORDS: returnsTrue, nullableNumberInvertTypeCheck
fun case_2(x: Any?): Boolean {
    contract { returns(true) implies (x !is Number?) }
    return x !is Number?
}

fun case_18_1(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_18_2(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(false) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}
fun case_18_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}

fun case_17_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 != null) }
    return value1 !is String || value2 != null
}
fun case_17_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 != null) }
    return value1 is String && value2 == null
}
fun case_17_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 !is String || value2 != null) }
    return value1 is String && value2 == null
}

fun case_16_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_16_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 !is Number) }
    return value1 is String && value2 is Number
}
fun case_16_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 !is String || value2 !is Number) }
    return value1 is String && value2 is Number
}

fun <T> T.case_19_1(): Boolean {
    contract { returns(true) implies (this@case_19_1 !is String) }
    return this !is String
}
fun <T> T.case_19_2(): Boolean {
    contract { returns(false) implies (this@case_19_2 is String) }
    return this is String
}
fun <T> T.case_19_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_19_3 is String) }
    return this is String
}

fun <T : Number> T.case_20_1(): Boolean {
    contract { returns(true) implies (this@case_20_1 !is Int) }
    return this !is Int
}
fun <T : Number> T.case_20_2(): Boolean {
    contract { returns(false) implies (this@case_20_2 is Int) }
    return this is Int
}
fun <T : Number> T.case_20_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_20_3 is Int) }
    return this is Int
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_1(): Boolean {
    contract { returns(true) implies (this@case_21_1 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_2(): Boolean {
    contract { returns(true) implies (this@case_21_2 == null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_3(): Boolean {
    contract { returns(false) implies (this@case_21_3 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_4(): Boolean {
    contract { returns(false) implies (this@case_21_4 == null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_5(): Boolean? {
    contract { returnsNotNull() implies (this@case_21_5 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_21_6(): Boolean? {
    contract { returnsNotNull() implies (this@case_21_6 == null) }
    return this == null
}

fun <T : String?> T.case_22_1(): Boolean {
    contract { returns(true) implies (this@case_22_1 != null) }
    return this != null
}
fun <T : String?> T.case_22_2(): Boolean {
    contract { returns(true) implies (this@case_22_2 == null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_22_3(): Boolean {
    contract { returns(false) implies (this@case_22_3 != null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_22_4(): Boolean {
    contract { returns(false) implies (this@case_22_4 == null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_22_5(): Boolean? {
    contract { returnsNotNull() implies (this@case_22_5 != null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_22_6(): Boolean? {
    contract { returnsNotNull() implies (this@case_22_6 == null) }
    return this != null
}

<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_23_1(): Boolean {
    contract { returns(false) implies (this@case_23_1 == null || this@case_23_1 !is String) }
    return this != null && this is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_23_2(): Boolean? {
    contract { returnsNotNull() implies (this@case_23_2 == null || this@case_23_2 !is String) }
    return this != null && this is String
}

fun <T : Number?> T.case_24_1(): Boolean {
    contract { returns(false) implies (this@case_24_1 !is Int || <!SENSELESS_COMPARISON!>this@case_24_1 == null<!>) }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun <T : Number?> T.case_24_2(): Boolean? {
    contract { returnsNotNull() implies (this@case_24_2 !is Int || <!SENSELESS_COMPARISON!>this@case_24_2 == null<!>) }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}

inline fun <reified T : Any?> T?.case_25_1(): Boolean {
    contract { returns(false) implies (this@case_25_1 !is Number || this@case_25_1 !is Int || <!SENSELESS_COMPARISON!>this@case_25_1 == null<!>) }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
inline fun <reified T : Any?> T?.case_25_2(): Boolean? {
    contract { returnsNotNull() implies (this@case_25_2 !is Number || this@case_25_2 !is Int || <!SENSELESS_COMPARISON!>this@case_25_2 == null<!>) }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}

fun <T> T?.case_26_1(value: Int?): Boolean {
    contract { returns(false) implies (this@case_26_1 == null || this@case_26_1 !is String || value == null) }
    return this != null && this is String && value != null
}
fun <T> T?.case_26_2(value: Int?): Boolean? {
    contract { returnsNotNull() implies (this@case_26_2 == null || this@case_26_2 !is String || value == null) }
    return this != null && this is String && value != null
}

// FILE: usages.kt

import contracts.*

// CASE KEYWORDS: numberSmartCast
fun case_1(t: Any?) {
    if (!contracts.case_1(t)) println(t.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) // no smartcast
}

// CASE KEYWORDS: nullableNumberSmartCast
fun case_2(t: Any?) {
    if (!contracts.case_2(t)) println(t?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>()) // no smartcast
}

fun case_3(number: Int?) {
    if (!funWithReturnsTrueAndNullCheck(number)) number<!UNSAFE_CALL!>.<!>inc() // nullable receiver
}

fun case_4(number: Int?) {
    if (!funWithReturnsFalseAndNotNullCheck(number)) number<!UNSAFE_CALL!>.<!>inc()
}

fun case_5(value: Any?) {
    if (!funWithReturnsTrue(value !is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_6(value: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalse(value !is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalseAndInvertCondition(value is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNotNullAndInvertCondition(value !is String) != null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_7(value: Any?) {
    if (!funWithReturnsTrue(value == null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_8(value: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value != null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalse(value == null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalseAndInvertCondition(value != null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_9(value: Any?) {
    if (!funWithReturnsTrueAndInvertTypeCheck(value)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalseAndInvertTypeCheck(value)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_10(value: Number?) {
    if (!funWithReturnsTrueAndNullCheck(value)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (funWithReturnsFalseAndNullCheck(value)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (funWithReturnsFalseAndNotNullCheck(value)) println(value)
    if (!(funWithReturnsNotNullAndNullCheck(value) != null)) println(value)
}

fun case_11(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value1 is String && value2 is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_12(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrue(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsFalse(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_13(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrue(value1 !is String || value2 != null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsFalse(value1 !is Float? || value1 == null || value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNull(value1 !is String || value2 !is Number) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_14(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value1 is Float? && value1 != null && value2 != null)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsFalseAndInvertCondition(value1 is String && value2 is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsFalseAndInvertCondition(value1 is String && value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 is String && value2 is Number) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 is String && value2 == null) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNull(value1 is Float? && value1 != null && value2 != null) == null) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

class case_15_class {
    val prop_1: Int? = 10

    fun case_15(value1: Any?, value2: Number?) {
        val o = case_15_class()
        if (!funWithReturnsTrueAndInvertCondition(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (funWithReturnsFalse(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (funWithReturnsNotNull(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}

fun case_16(value1: Any?, value2: Any?) {
    if (!contracts.case_16_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_16_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!(contracts.case_16_3(value1, value2) != null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_17(value1: Any?, value2: Any?) {
    if (!contracts.case_17_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_17_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_17_3(value1, value2) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

class case_18_class {
    val prop_1: Int? = 10
    fun case_18(value1: Any?, value2: Number?) {
        val o = case_18_class()
        if (contracts.case_18_1(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_18_2(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_18_3(value1, value2, o.prop_1, this.prop_1) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}

fun case_19(value1: Any?) {
    if (!value1.case_19_1()) println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (value1.case_19_2()) println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (value1.case_19_3() == null) println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
}

fun case_20(value1: Number) {
    when {
        !value1.case_20_1() -> println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
    when {
        value1.case_20_2() -> println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
}

fun case_21(value1: String?, value2: String?, value3: String?, value4: String?, value5: String?, value6: String?) {
    if (!value1.case_21_1()) println(value1)
    if (!value2.case_21_2()) println(value2<!UNSAFE_CALL!>.<!>length)
    if (!value3.case_21_3()) println(value3)
        else println(value6<!UNSAFE_CALL!>.<!>length)
    if (!value4.case_21_4()) println(value4<!UNSAFE_CALL!>.<!>length)
        else println(value4)
    if (value5.case_21_5() == null) println(value5)
        else println(value5<!UNSAFE_CALL!>.<!>length)
    if (value6.case_21_6() == null) println(value6<!UNSAFE_CALL!>.<!>length)
        else println(value6)
}

fun case_22(value1: String?, value2: String?, value3: String?, value4: String?, value5: String?, value6: String?) {
    when {
        !value1.case_22_1() -> println(value1)
    }
    when {
        !value2.case_22_2() -> println(value2<!UNSAFE_CALL!>.<!>length)
    }
    when {
        !value3.case_22_3() -> println(value3<!UNSAFE_CALL!>.<!>length)
        value3.case_22_3() -> println(value3)
    }
    when {
        !value4.case_22_4() -> println(value4)
        value4.case_22_4() -> println(value4<!UNSAFE_CALL!>.<!>length)
    }
    when {
        value5.case_22_5() == null ->  println(value5<!UNSAFE_CALL!>.<!>length)
        value5.case_22_5() != null ->  println(value5)
    }
    when {
        value6.case_22_6() == null -> println(value6)
        value6.case_22_6() != null -> println(value6<!UNSAFE_CALL!>.<!>length)
    }
}

fun case_23(value1: Any?, value2: Any?) {
    when {
        value1.case_23_1() -> println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    when {
        value2.case_23_2() == null -> println(value2.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

fun case_24(value1: Number?, value2: Number?) {
    if (value1.case_24_1())
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value2.case_24_2() == null)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun case_25(value1: Any?, value2: Any?) {
    if (value1.case_25_1())
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value2.case_25_2() != null)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun case_26(value1: Any?, value2: Int?, value3: Any?, value4: Int?) {
    when {
        value1.case_26_1(value2) -> {
            println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value2<!UNSAFE_CALL!>.<!>inv())
        }
    }
    when {
        value3.case_26_2(value4) == null -> {
            println(value3.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value4<!UNSAFE_CALL!>.<!>inv())
        }
    }
}

fun case_27(value1: Any?, value2: Any?, value3: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String? || value2 !is Number && value3 !is Float)
    println(value1!!.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
}

fun case_28(value1: Any?, value2: Any?, value3: Any?) {
    funWithReturnsAndInvertCondition(value1 !is String || value2 !is Number || <!USELESS_IS_CHECK!>value3 !is Any?<!>)
    println(value1!!.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
}
