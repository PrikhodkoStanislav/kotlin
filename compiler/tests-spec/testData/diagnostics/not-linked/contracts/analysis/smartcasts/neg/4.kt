// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 4
 DESCRIPTION: Smartcast using returns effect with simple type checking and not-null conditions on receiver (extention function).
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:string
    fun:extension:generic
 */
fun <T> T.case_1() {
    contract { returns() implies (this@case_1 !is String) }
    if (this is String) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:int
    fun:extension:generic:upperBound
 */
fun <T : Number> T.case_2() {
    contract { returns() implies (this@case_2 !is Int) }
    if (this is Int) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns:implies:nullCheck,notNullCheck
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_1() {
    contract { returns() implies (this@case_3_1 == null) }
    if (this != null) throw Exception()
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_3_2() {
    contract { returns() implies (this@case_3_2 != null) }
    if (this == null) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns:implies:nullCheck,notNullCheck
    fun:extension:generic,nullable
 */
fun <T : String?> T.case_4_1() {
    contract { returns() implies (this@case_4_1 == null) }
    if (this != null) throw Exception()
}
fun <T : String?> T.case_4_2() {
    contract { returns() implies (this@case_4_2 != null) }
    if (this == null) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue:implies:invertTypeCheck:string
        returnsFalse:implies:invertTypeCheck:string
        returnsNotNull:implies:invertTypeCheck:string
        returnsNull:implies:invertTypeCheck:string
    fun:extension:generic
 */
fun <T> T.case_5_1(): Boolean {
    contract { returns(true) implies (this@case_5_1 !is String) }
    return this !is String
}
fun <T> T.case_5_2(): Boolean {
    contract { returns(false) implies (this@case_5_2 !is String) }
    return this !is String
}
fun <T> T.case_5_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_5_3 !is String) }
    return this !is String
}
fun <T> T.case_5_4(): Boolean? {
    contract { returns(null) implies (this@case_5_4 !is String) }
    return this !is String
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue:implies:invertTypeCheck:int
        returnsFalse:implies:invertTypeCheck:int
        returnsNotNull:implies:invertTypeCheck:int
        returnsNull:implies:invertTypeCheck:int
    fun:extension:generic:upperBound
 */
fun <T : Number> T.case_6_1(): Boolean {
    contract { returns(true) implies (this@case_6_1 !is Int) }
    return this !is Int
}
fun <T : Number> T.case_6_2(): Boolean {
    contract { returns(false) implies (this@case_6_2 !is Int) }
    return this !is Int
}
fun <T : Number> T.case_6_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_6_3 !is Int) }
    return this !is Int
}
fun <T : Number> T.case_6_4(): Boolean? {
    contract { returns(null) implies (this@case_6_4 !is Int) }
    return this !is Int
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue:implies:nullCheck
        returnsFalse:implies:notNullCheck
        returnsNotNull:implies:notNullCheck
        returnsNull:implies:notNullCheck
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_1(): Boolean {
    contract { returns(true) implies (this@case_7_1 == null) }
    return this == null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_2(): Boolean {
    contract { returns(false) implies (this@case_7_2 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_7_3 != null) }
    return this != null
}
fun <T : <!FINAL_UPPER_BOUND!>String<!>> T?.case_7_4(): Boolean? {
    contract { returns(null) implies (this@case_7_4 != null) }
    return this != null
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue:implies:nullCheck
        returnsFalse:implies:notNullCheck
        returnsNotNull:implies:notNullCheck
        returnsNull:implies:notNullCheck
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : String?> T.case_8_1(): Boolean {
    contract { returns(true) implies (this@case_8_1 == null) }
    return this == null
}
fun <T : String?> T.case_8_2(): Boolean {
    contract { returns(false) implies (this@case_8_2 != null) }
    return this != null
}
fun <T : String?> T.case_8_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_8_3 != null) }
    return this != null
}
fun <T : String?> T.case_8_4(): Boolean? {
    contract { returns(null) implies (this@case_8_4 != null) }
    return this != null
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsNotNull:implies:notNullCheck
        returnsNull:implies:notNullCheck
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : Number?> T.case_9(): Boolean? {
    contract { returnsNotNull() implies (this@case_9 != null) }
    return this != null
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsNotNull:implies:nullCheck
        returnsNull:implies:nullCheck
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : Number?> T.case_10(): Boolean? {
    contract { returnsNotNull() implies (this@case_10 == null) }
    return this == null
}

// FILE: usages.kt

import contracts.*

/*
 CASE KEYWORDS:
    effectsUsage
        returns:invertTypeCheck:string
    smartcast:string
 */
fun case_1(value_1: Any?) {
    value_1.case_1()
    println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:invertTypeCheck:int
    smartcast:int
 */
fun case_2(value_1: Number) {
    value_1.case_2()
    println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:nullCheck,notNullCheck
    smartcast:notNull,nullConstant
 */
fun case_3(value_1: String?, value_2: String?) {
    value_1.case_3_1()
    println(<!DEBUG_INFO_CONSTANT!>value_1<!><!UNSAFE_CALL!>.<!>length)
    value_2.case_3_2()
    println(value_2)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:nullCheck,notNullCheck
    smartcast:notNull,nullConstant
 */
fun case_4(value_1: String?, value_2: String?) {
    value_1.case_4_1()
    println(<!DEBUG_INFO_CONSTANT!>value_1<!><!UNSAFE_CALL!>.<!>length)
    value_2.case_4_2()
    println(value_2)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue:invertTypeCheck:string
        returnsFalse:invertTypeCheck:string
        returnsNotNull:invertTypeCheck:string
        returnsNull:invertTypeCheck:string
    smartcast:string
    if
 */
fun case_5(value_1: Any?) {
    if (value_1.case_5_1()) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!value_1.case_5_2()) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (value_1.case_5_3() != null) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (value_1.case_5_4() == null) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue:invertTypeCheck:int
        returnsFalse:invertTypeCheck:int
        returnsNotNull:invertTypeCheck:int
        returnsNull:invertTypeCheck:int
    smartcast:int
    when
 */
fun case_6(value_1: Number) {
    when { value_1.case_6_1() -> println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { !value_1.case_6_2() -> println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { value_1.case_6_3() != null -> println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
    when { value_1.case_6_4() != null -> println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()) }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue:nullCheck
        returnsFalse:notNullCheck
        returnsNotNull:notNullCheck
        returnsNull:notNullCheck
    smartcast:notNull,nullConstant
    if
 */
fun case_7(value_1: String?, value_2: String?) {
    if (value_1.case_7_1()) println(<!DEBUG_INFO_CONSTANT!>value_1<!><!UNSAFE_CALL!>.<!>length)
    if (value_2.case_7_2()) println(value_2)
    if (!(value_2.case_7_3() == null)) println(value_2)
    if (value_2.case_7_3() == null) println(value_2)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue:nullCheck
        returnsFalse:notNullCheck
        returnsNotNull:notNullCheck
        returnsNull:notNullCheck
    smartcast:notNull,nullConstant
    when
 */
fun case_8(value_1: String?, value_2: String?) {
    when { value_1.case_8_1() -> println(<!DEBUG_INFO_CONSTANT!>value_1<!><!UNSAFE_CALL!>.<!>length) }
    when { value_2.case_8_2() -> println(value_2) }
    when { !(value_2.case_8_3() == null) -> println(value_2) }
    when { value_2.case_8_3() == null -> println(value_2) }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsNotNull:notNullCheck
        returnsNull:notNullCheck
    smartcast:notNull,nullConstant
    if
 */
fun case_9(value_1: Number?) {
    if (value_1?.case_9() == null) println(value_1<!UNSAFE_CALL!>.<!>toByte())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsNotNull:nullCheck
        returnsNull:nullCheck
    smartcast:notNull,nullConstant
    unreachableCode
    if
 */
fun case_10(value_1: Number?) {
    if (value_1?.case_10() == null) {
        println(value_1<!UNSAFE_CALL!>.<!>toByte())
    } else {
        <!UNREACHABLE_CODE!>println(<!><!DEBUG_INFO_SMARTCAST!>value_1<!><!UNREACHABLE_CODE!>.toByte())<!>
    }
}
