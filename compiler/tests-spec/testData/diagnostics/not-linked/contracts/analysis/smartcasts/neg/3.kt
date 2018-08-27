// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 3
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions as implies parameter in contract definition.
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
                invertTypeCheck:string,number
                disjunction
 */
fun case_1(value_1: Any?, value_2: Any?) {
    contract { returns() implies (value_1 !is String || value_2 !is Number) }
    if (!(value_1 !is String || value_2 !is Number)) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:string
                disjunction
                notNullCheck
 */
fun case_2(value_1: Any?, value_2: Any?) {
    contract { returns() implies (value_1 !is String || value_2 != null) }
    if (!(value_1 !is String || value_2 != null)) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returns
            implies
                invertTypeCheck:nullableFloat
                disjunction
                nullCheck
 */
fun case_3(value_1: Any?, value_2: Any?, value_3: Any?, value_4: Any?) {
    contract { returns() implies (value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null) }
    if (!(value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null)) throw Exception()
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:string,number
                disjunction
        returnsFalse
            implies
                invertTypeCheck:string,number
                disjunction
        returnsNotNull
            implies
                invertTypeCheck:string,number
                disjunction
        returnsNull
            implies
                invertTypeCheck:string,number
                disjunction
 */
fun case_4_1(value_1: Any?, value_2: Any?): Boolean {
    contract { returns(true) implies (value_1 !is String || value_2 !is Number) }
    return value_1 !is String || value_2 !is Number
}
fun case_4_2(value_1: Any?, value_2: Any?): Boolean {
    contract { returns(false) implies (value_1 !is String || value_2 !is Number) }
    return !(value_1 !is String || value_2 !is Number)
}
fun case_4_3(value_1: Any?, value_2: Any?): Boolean? {
    contract { returnsNotNull() implies (value_1 !is String || value_2 !is Number) }
    return if (value_1 !is String || value_2 !is Number) true else null
}
fun case_4_4(value_1: Any?, value_2: Any?): Boolean? {
    contract { returns(null) implies (value_1 !is String || value_2 !is Number) }
    return if (value_1 !is String || value_2 !is Number) null else true
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:string
                disjunction
                notNullCheck
        returnsFalse
            implies
                invertTypeCheck:string
                disjunction
                notNullCheck
        returnsNotNull
            implies
                typeCheck:string
                conjunction
                nullCheck
        returnsNull
            implies
                typeCheck:string
                conjunction
                nullCheck
 */
fun case_5_1(value_1: Any?, value_2: Any?): Boolean {
    contract { returns(true) implies (value_1 !is String || value_2 != null) }
    return value_1 !is String || value_2 != null
}
fun case_5_2(value_1: Any?, value_2: Any?): Boolean {
    contract { returns(false) implies (value_1 !is String || value_2 != null) }
    return !(value_1 !is String || value_2 != null)
}
fun case_5_3(value_1: Any?, value_2: Any?): Boolean? {
    contract { returnsNotNull() implies (value_1 is String && value_2 == null) }
    return if (value_1 is String && value_2 == null) true else null
}
fun case_5_4(value_1: Any?, value_2: Any?): Boolean? {
    contract { returns(null) implies (value_1 is String && value_2 == null) }
    return if (value_1 is String && value_2 == null) null else true
}

/*
 CASE KEYWORDS:
    effectsDefinition
        1
        returnsTrue
            implies
                invertTypeCheck:nullableFloat
                disjunction
                nullCheck
        returnsFalse
            implies
                invertTypeCheck:nullableFloat
                disjunction
                nullCheck
        returnsNotNull
            implies
                typeCheck:nullableFloat
                conjunction
                notNullCheck
        returnsNull
            implies
                typeCheck:nullableFloat
                conjunction
                notNullCheck
 */
fun case_6_1(value_1: Any?, value_2: Any?, value_3: Any?, value_4: Any?): Boolean {
    contract { returns(true) implies (value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null) }
    return value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null
}
fun case_6_2(value_1: Any?, value_2: Any?, value_3: Any?, value_4: Any?): Boolean {
    contract { returns(false) implies (value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null) }
    return !(value_1 !is Float? || value_1 == null || value_2 == null || value_3 == null || value_4 == null)
}
fun case_6_3(value_1: Any?, value_2: Any?, value_3: Any?, value_4: Any?): Boolean? {
    contract { returnsNotNull() implies (value_1 is Float? && value_1 != null && value_2 != null && value_3 != null && value_4 != null) }
    return if (value_1 is Float? && value_1 != null && value_2 != null && value_3 != null && value_4 != null) true else null
}
fun case_6_4(value_1: Any?, value_2: Any?, value_3: Any?, value_4: Any?): Boolean? {
    contract { returns(null) implies (value_1 is Float? && value_1 != null && value_2 != null && value_3 != null && value_4 != null) }
    return if (value_1 is Float? && value_1 != null && value_2 != null && value_3 != null && value_4 != null) null else true
}

// FILE: usages.kt

import contracts.*

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            invertTypeCheck:string,number
            disjunction
    smartcast:string,number
 */
fun case_1(value_1: Any?, value_2: Any?) {
    contracts.case_1(value_1, value_2)
    println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            invertTypeCheck:string
            disjunction
            notNull
    smartcast:string,notNull
 */
fun case_2(value_1: Any?, value_2: Any?) {
    contracts.case_2(value_1, value_2)
    println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            invertTypeCheck:nullableFloat
            disjunction
            nullCheck
    smartcast:string,notNull,property
    class
 */
class case_3_class {
    val prop_1: Int? = 10
    fun case_3(value_1: Any?, value_2: Number?) {
        val o = case_3_class()
        contracts.case_3(value_1, value_2, o.prop_1, this.prop_1)
        println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value_2?.toByte())
        println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:string,number
            disjunction
        returnsFalse
            invertTypeCheck:string,number
            disjunction
        returnsNotNull
            invertTypeCheck:string,number
            disjunction
        returnsNull
            invertTypeCheck:string,number
            disjunction
    smartcast:string,number
 */
fun case_4(value_1: Any?, value_2: Any?) {
    if (contracts.case_4_1(value_1, value_2)) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_4_2(value_1, value_2)) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_4_3(value_1, value_2) != null) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_4_4(value_1, value_2) == null) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:string
            disjunction
            notNullCheck
        returnsFalse
            invertTypeCheck:string
            disjunction
            notNullCheck
        returnsNotNull
            typeCheck:string
            conjunction
            nullCheck
        returnsNull
            typeCheck:string
            conjunction
            nullCheck
    smartcast:string,notNull
 */
fun case_5(value_1: Any?, value_2: Any?) {
    if (contracts.case_5_1(value_1, value_2)) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_5_2(value_1, value_2)) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_5_3(value_1, value_2) == null) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_5_4(value_1, value_2) != null) {
        println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value_2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertTypeCheck:nullableFloat
            disjunction
            nullCheck
        returnsFalse
            invertTypeCheck:nullableFloat
            disjunction
            nullCheck
        returnsNotNull
            typeCheck:nullableFloat
            conjunction
            notNullCheck
        returnsNull
            typeCheck:nullableFloat
            conjunction
            notNullCheck
    smartcast:nullableFloat,notNull,property
    class
 */
class case_6_class {
    val prop_1: Int? = 10
    fun case_6(value_1: Any?, value_2: Number?) {
        val o = case_6_class()
        if (contracts.case_6_1(value_1, value_2, o.prop_1, this.prop_1)) {
            println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value_2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (!contracts.case_6_2(value_1, value_2, o.prop_1, this.prop_1)) {
            println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value_2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_6_3(value_1, value_2, o.prop_1, this.prop_1) == null) {
            println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value_2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_6_4(value_1, value_2, o.prop_1, this.prop_1) != null) {
            println(value_1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value_2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}
