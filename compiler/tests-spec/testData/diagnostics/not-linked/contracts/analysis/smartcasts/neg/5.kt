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

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:string
                disjunction
                nullCheck:receiver
    fun:extension:generic,nullable
 */
fun <T> T?.case_1() {
    contract { returns() implies (this@case_1 == null || this@case_1 !is String) }
    if (this@case_1 is String) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:int
                disjunction
                nullCheck:receiver
    fun:extension
        generic:upperBound
        nullable
 */
fun <T : Number?> T.case_2() {
    contract { returns() implies (this@case_2 !is Int || <!SENSELESS_COMPARISON!>this@case_2 == null<!>) }
    if (!(this !is Int || <!SENSELESS_COMPARISON!>this == null<!>)) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:int,number
                disjunction
                nullCheck:receiver
    fun
        inline
        extension
            generic:upperBound,reified
            nullable
 */
inline fun <reified T : Any?> T?.case_3() {
    contract { returns() implies (this@case_3 !is Number || this@case_3 !is Int || <!SENSELESS_COMPARISON!>this@case_3 == null<!>) }
    if (!(this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>)) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:string
                disjunction
                nullCheck:receiver
        returnsFalse
            implies
                invertTypeCheck:string
                disjunction
                nullCheck:receiver
        returnsNotNull
            implies
                invertTypeCheck:string
                disjunction
                nullCheck:receiver
        returnsNull
            implies
                invertTypeCheck:string
                disjunction
                nullCheck:receiver
    fun:extension:generic,nullable
 */
fun <T> T?.case_4_1(): Boolean {
    contract { returns(true) implies (this@case_4_1 == null || this@case_4_1 !is String) }
    return this == null || this !is String
}
fun <T> T?.case_4_2(): Boolean {
    contract { returns(false) implies (this@case_4_2 == null || this@case_4_2 !is String) }
    return this == null || this !is String
}
fun <T> T?.case_4_3(): Boolean? {
    contract { returnsNotNull() implies (this@case_4_3 == null || this@case_4_3 !is String) }
    return this == null || this !is String
}
fun <T> T?.case_4_4(): Boolean? {
    contract { returns(null) implies (this@case_4_4 == null || this@case_4_4 !is String) }
    return this == null || this !is String
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:int
                disjunction
                nullCheck:receiver
        returnsFalse
            implies
                invertTypeCheck:int
                disjunction
                nullCheck:receiver
        returnsNotNull
            implies
                invertTypeCheck:int
                disjunction
                nullCheck:receiver
        returnsNull
            implies
                invertTypeCheck:int
                disjunction
                nullCheck:receiver
    fun:extension
        generic:upperBound
        nullable
 */
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
fun <T : Number?> T.case_5_4(): Boolean? {
    contract { returns(null) implies (this@case_5_4 !is Int || <!SENSELESS_COMPARISON!>this@case_5_4 == null<!>) }
    return this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:number,int
                disjunction
                nullCheck:receiver
        returnsFalse
            implies
                invertTypeCheck:number,int
                disjunction
                nullCheck:receiver
        returnsNotNull
            implies
                invertTypeCheck:number,int
                disjunction
                nullCheck:receiver
        returnsNull
            implies
                invertTypeCheck:number,int
                disjunction
                nullCheck:receiver
    fun
        inline
        extension
            generic:upperBound,reified
            nullable
 */
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
inline fun <reified T : Any?> T?.case_6_4(): Boolean? {
    contract { returns(null) implies (this@case_6_4 is Number && this@case_6_4 is Int && <!SENSELESS_COMPARISON!>this@case_6_4 != null<!>) }
    return this !is Number || this !is Int || <!SENSELESS_COMPARISON!>this == null<!>
}

// FILE: usages.kt

import contracts.*

/*
 CASE KEYWORDS:
    effectsUsage
        returns:
            invertTypeCheck:string
            disjunction
            nullCheck:receiver
    smartcast:notNull,string
 */
fun case_1(value1: Any?) {
    value1.case_1()
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:
            invertTypeCheck:int
            disjunction
            nullCheck:receiver
    smartcast:notNull,int
 */
fun case_2(value1: Number?) {
    value1.case_2()
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:
            invertTypeCheck:number,int
            disjunction
            nullCheck:receiver
    smartcast:notNull,int
 */
fun case_3(value1: Any?) {
    value1.case_3()
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:string
            disjunction
            nullCheck:receiver
        returnsFalse
            invertTypeCheck:string
            disjunction
            nullCheck:receiver
        returnsNotNull
            invertTypeCheck:string
            disjunction
            nullCheck:receiver
        returnsNull
            invertTypeCheck:string
            disjunction
            nullCheck:receiver
    smartcast:notNull,string
 */
fun case_4(value1: Any?, value2: Any?, value3: Any?) {
    when { value1.case_4_1() -> println(value1.<!UNRESOLVED_REFERENCE!>length<!>) }
    when { !value2.case_4_2() -> println(value2.<!UNRESOLVED_REFERENCE!>length<!>) }
    when { value3.case_4_3() != null -> println(value3.<!UNRESOLVED_REFERENCE!>length<!>) }
    when { value3.case_4_4() == null -> println(value3.<!UNRESOLVED_REFERENCE!>length<!>) }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:int
            disjunction
            nullCheck:receiver
        returnsFalse
            invertTypeCheck:int
            disjunction
            nullCheck:receiver
        returnsNotNull
            invertTypeCheck:int
            disjunction
            nullCheck:receiver
        returnsNull
            invertTypeCheck:int
            disjunction
            nullCheck:receiver
    smartcast:notNull,int
 */
fun case_5(value1: Number?, value2: Number?, value3: Number?) {
    if (value1.case_5_1()) println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (!value2.case_5_2()) println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_5_3() != null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_5_4() == null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:number,int
            disjunction
            nullCheck:receiver
        returnsFalse
            invertTypeCheck:number,int
            disjunction
            nullCheck:receiver
        returnsNotNull
            invertTypeCheck:number,int
            disjunction
            nullCheck:receiver
        returnsNull
            invertTypeCheck:number,int
            disjunction
            nullCheck:receiver
    smartcast:notNull,int
 */
fun case_6(value1: Any?, value2: Any?, value3: Any?) {
    if (value1.case_6_1()) println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (!value2.case_6_2()) println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_6_3() == null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
    if (value3.case_6_4() != null) println(value3.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>())
}
