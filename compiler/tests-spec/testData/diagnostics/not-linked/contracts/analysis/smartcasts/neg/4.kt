// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 4
 DESCRIPTION: Using equality with null in function contract (returns effect) and invert condition ('not' operator).
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26176
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: contract function with direct condition in implies
fun case_1(number: Int?) {
    if (!funWithReturnsTrueAndNullCheck(number)) {
        number<!UNSAFE_CALL!>.<!>inc() // nullable receiver
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_2(number: Int?) {
    if (!funWithReturnsFalseAndNotNullCheck(number)) {
        number<!UNSAFE_CALL!>.<!>inc()
    }
}

// CASE DESCRIPTION: contract function with direct condition in implies
fun case_3(value: Any?) {
    if (!funWithReturnsTrue(value !is String)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_4(value: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value is String)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (funWithReturnsFalse(value !is String)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (funWithReturnsFalseAndInvertCondition(value is String)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (!(funWithReturnsNotNullAndInvertCondition(value !is String) != null)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_5(value: Any?) {
    if (!funWithReturnsTrue(value == null)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_6(value: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value != null)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (funWithReturnsFalse(value == null)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (funWithReturnsFalseAndInvertCondition(value != null)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_7(value: Any?) {
    if (!funWithReturnsTrueAndInvertTypeCheck(value)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (funWithReturnsFalseAndInvertTypeCheck(value)) {
        println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_8(value: Number?) {
    if (!funWithReturnsTrueAndNullCheck(value)) {
        println(value<!UNSAFE_CALL!>.<!>toByte())
    }
    if (funWithReturnsFalseAndNullCheck(value)) {
        println(value<!UNSAFE_CALL!>.<!>toByte())
    }
    if (funWithReturnsFalseAndNotNullCheck(value)) {
        println(value)
    }
    if (!(funWithReturnsNotNullAndNullCheck(value) != null)) {
        println(value)
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_9(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrueAndInvertCondition(value1 is String && value2 is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_10(value1: Any?, value2: Any?) {
    if (!funWithReturnsTrue(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsFalse(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_11(value1: Any?, value2: Any?) {
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

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_12(value1: Any?, value2: Any?) {
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

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
class case_13_class {
    val prop_1: Int? = 10

    fun case_13(value1: Any?, value2: Number?) {
        val o = case_13_class()
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

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_14_funWithContract_1(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(true) implies (value1 !is String || value2 !is Number)
    }
    return value1 !is String || value2 !is Number
}
fun case_14_funWithContract_2(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(false) implies (value1 !is String || value2 !is Number)
    }
    return value1 is String && value2 is Number
}
fun case_14_funWithContract_3(value1: Any?, value2: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value1 !is String || value2 !is Number)
    }
    return value1 is String && value2 is Number
}
fun case_14(value1: Any?, value2: Any?) {
    if (!case_14_funWithContract_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (case_14_funWithContract_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!(case_14_funWithContract_3(value1, value2) != null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

// CASE DESCRIPTION: contract function with invert condition in implies
fun case_15_funWithContract_1(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(true) implies (value1 !is String || value2 != null)
    }
    return value1 !is String || value2 != null
}
fun case_15_funWithContract_2(value1: Any?, value2: Any?): Boolean {
    contract {
        returns(false) implies (value1 !is String || value2 != null)
    }
    return value1 is String && value2 == null
}
fun case_15_funWithContract_3(value1: Any?, value2: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value1 !is String || value2 != null)
    }
    return value1 is String && value2 == null
}
fun case_15(value1: Any?, value2: Any?) {
    if (!case_15_funWithContract_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (case_15_funWithContract_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (case_15_funWithContract_3(value1, value2) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

fun case_16_funWithContract_1(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract {
        returns() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null)
    }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_16_funWithContract_2(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract {
        returns(false) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null)
    }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}
fun case_16_funWithContract_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean? {
    contract {
        returnsNotNull() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null)
    }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}
class case_16_class {
    val prop_1: Int? = 10
    fun case_16(value1: Any?, value2: Number?) {
        val o = case_16_class()
        if (case_16_funWithContract_1(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (case_16_funWithContract_2(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (case_16_funWithContract_3(value1, value2, o.prop_1, this.prop_1) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}

fun <T> T.case_17_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_17_funWithContract_1 !is String)
    }
    return this !is String
}
fun <T> T.case_17_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_17_funWithContract_2 is String)
    }
    return this is String
}
fun <T> T.case_17_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_17_funWithContract_3 is String)
    }
    return this is String
}
fun case_17(value1: Any?) {
    if (!value1.case_17_funWithContract_1()) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (value1.case_17_funWithContract_2()) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    if (value1.case_17_funWithContract_3() == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

fun <T : Number> T.case_18_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_18_funWithContract_1 !is Int)
    }
    return this !is Int
}
fun <T : Number> T.case_18_funWithContract_2(): Boolean {
    contract {
        returns(false) implies (this@case_18_funWithContract_2 is Int)
    }
    return this is Int
}
fun <T : Number> T.case_18_funWithContract_3(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_18_funWithContract_3 is Int)
    }
    return this is Int
}
fun case_18(value1: Number) {
    when {
        !value1.case_18_funWithContract_1() -> println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
    when {
        value1.case_18_funWithContract_2() -> println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    }
}

fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_19_funWithContract_1 != null)
    }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_2(): Boolean {
    contract {
        returns(true) implies (this@case_19_funWithContract_2 == null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_3(): Boolean {
    contract {
        returns(false) implies (this@case_19_funWithContract_3 != null)
    }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_4(): Boolean {
    contract {
        returns(false) implies (this@case_19_funWithContract_4 == null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_5(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_19_funWithContract_5 != null)
    }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_19_funWithContract_6(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_19_funWithContract_6 == null)
    }
    return this == null
}
fun case_19(value1: String?, value2: String?, value3: String?, value4: String?, value5: String?, value6: String?) {
    if (!value1.case_19_funWithContract_1()) {
        println(value1)
    }
    if (!value2.case_19_funWithContract_2()) {
        println(value2<!UNSAFE_CALL!>.<!>length)
    }
    if (!value3.case_19_funWithContract_3()) {
        println(value3)
    } else {
        println(value6<!UNSAFE_CALL!>.<!>length)
    }
    if (!value4.case_19_funWithContract_4()) {
        println(value4<!UNSAFE_CALL!>.<!>length)
    } else {
        println(value4)
    }
    if (value5.case_19_funWithContract_5() == null) {
        println(value5)
    } else {
        println(value5<!UNSAFE_CALL!>.<!>length)
    }
    if (value6.case_19_funWithContract_6() == null) {
        println(value6<!UNSAFE_CALL!>.<!>length)
    } else {
        println(value6)
    }
}

fun <T : String?> T.case_20_funWithContract_1(): Boolean {
    contract {
        returns(true) implies (this@case_20_funWithContract_1 != null)
    }
    return this != null
}
fun <T : String?> T.case_20_funWithContract_2(): Boolean {
    contract {
        returns(true) implies (this@case_20_funWithContract_2 == null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_20_funWithContract_3(): Boolean {
    contract {
        returns(false) implies (this@case_20_funWithContract_3 != null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_20_funWithContract_4(): Boolean {
    contract {
        returns(false) implies (this@case_20_funWithContract_4 == null)
    }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_20_funWithContract_5(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_20_funWithContract_5 != null)
    }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_20_funWithContract_6(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_20_funWithContract_6 == null)
    }
    return this != null
}
fun case_20(value1: String?, value2: String?, value3: String?, value4: String?, value5: String?, value6: String?) {
    when {
        !value1.case_20_funWithContract_1() -> println(value1)
    }
    when {
        !value2.case_20_funWithContract_2() -> println(value2<!UNSAFE_CALL!>.<!>length)
    }
    when {
        !value3.case_20_funWithContract_3() -> println(value3<!UNSAFE_CALL!>.<!>length)
        value3.case_20_funWithContract_3() -> println(value3)
    }
    when {
        !value4.case_20_funWithContract_4() -> println(value4)
        value4.case_20_funWithContract_4() -> println(value4<!UNSAFE_CALL!>.<!>length)
    }
    when {
        value5.case_20_funWithContract_5() == null ->  println(value5<!UNSAFE_CALL!>.<!>length)
        value5.case_20_funWithContract_5() != null ->  println(value5)
    }
    when {
        value6.case_20_funWithContract_6() == null -> println(value6)
        value6.case_20_funWithContract_6() != null -> println(value6<!UNSAFE_CALL!>.<!>length)
    }
}

<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_21_funWithContract_1(): Boolean {
    contract {
        returns(false) implies (this@case_21_funWithContract_1 == null || this@case_21_funWithContract_1 !is String)
    }
    return this != null && this is String
}
<!NOTHING_TO_INLINE!>inline<!> fun <T> T?.case_21_funWithContract_2(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_21_funWithContract_2 == null || this@case_21_funWithContract_2 !is String)
    }
    return this != null && this is String
}
fun case_21(value1: Any?, value2: Any?) {
    when {
        value1.case_21_funWithContract_1() -> println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    }
    when {
        value2.case_21_funWithContract_2() == null -> println(value2.<!UNRESOLVED_REFERENCE!>length<!>)
    }
}

fun <T : Number?> T.case_22_funWithContract_1(): Boolean {
    contract {
        returns(false) implies (this@case_22_funWithContract_1 !is Int || <!SENSELESS_COMPARISON!>this@case_22_funWithContract_1 == null<!>)
    }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun <T : Number?> T.case_22_funWithContract_2(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_22_funWithContract_2 !is Int || <!SENSELESS_COMPARISON!>this@case_22_funWithContract_2 == null<!>)
    }
    return this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun case_5(value1: Number?, value2: Number?) {
    if (value1.case_22_funWithContract_1())
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value2.case_22_funWithContract_2() == null)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

inline fun <reified T : Any?> T?.case_23_funWithContract_1(): Boolean {
    contract {
        returns(false) implies (this@case_23_funWithContract_1 !is Number || this@case_23_funWithContract_1 !is Int || <!SENSELESS_COMPARISON!>this@case_23_funWithContract_1 == null<!>)
    }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
inline fun <reified T : Any?> T?.case_23_funWithContract_2(): Boolean? {
    contract {
        returnsNotNull() implies (this@case_23_funWithContract_2 !is Number || this@case_23_funWithContract_2 !is Int || <!SENSELESS_COMPARISON!>this@case_23_funWithContract_2 == null<!>)
    }
    return this is Number && this is Int && <!SENSELESS_COMPARISON!>this != null<!>
}
fun case_23(value1: Any?, value2: Any?) {
    if (value1.case_23_funWithContract_1())
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value2.case_23_funWithContract_2() != null)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

fun <T> T?.case_24_funWithContract_1(value: Int?): Boolean {
    contract {
        returns(false) implies (this@case_24_funWithContract_1 == null || this@case_24_funWithContract_1 !is String || value == null)
    }
    return this != null && this is String && value != null
}
fun <T> T?.case_24_funWithContract_2(value: Int?): Boolean? {
    contract {
        returnsNotNull() implies (this@case_24_funWithContract_2 == null || this@case_24_funWithContract_2 !is String || value == null)
    }
    return this != null && this is String && value != null
}
fun case_24(value1: Any?, value2: Int?, value3: Any?, value4: Int?) {
    when {
        value1.case_24_funWithContract_1(value2) -> {
            println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value2<!UNSAFE_CALL!>.<!>inv())
        }
    }
    when {
        value3.case_24_funWithContract_2(value4) == null -> {
            println(value3.<!UNRESOLVED_REFERENCE!>length<!>)
            println(value4<!UNSAFE_CALL!>.<!>inv())
        }
    }
}
