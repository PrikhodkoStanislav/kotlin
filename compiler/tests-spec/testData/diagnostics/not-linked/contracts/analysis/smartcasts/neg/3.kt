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
fun case_1(value1: Any?, value2: Any?) {
    contract { returns() implies (value1 !is String || value2 !is Number) }
    if (value1 is String && value2 is Number) throw Exception()
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
fun case_2(value1: Any?, value2: Any?) {
    contract { returns() implies (value1 !is String || value2 != null) }
    if (value1 is String && value2 == null) throw Exception()
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
fun case_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?) {
    contract { returns() implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    if (!(value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null)) throw Exception()
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
fun case_4_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_4_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_4_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
}
fun case_4_4(value1: Any?, value2: Any?): Boolean? {
    contract { returns(null) implies (value1 !is String || value2 !is Number) }
    return value1 !is String || value2 !is Number
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
fun case_5_1(value1: Any?, value2: Any?): Boolean {
    contract { returns(true) implies (value1 !is String || value2 != null) }
    return value1 !is String || value2 != null
}
fun case_5_2(value1: Any?, value2: Any?): Boolean {
    contract { returns(false) implies (value1 !is String || value2 != null) }
    return value1 !is String || value2 != null
}
fun case_5_3(value1: Any?, value2: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 is String && value2 == null) }
    return value1 !is String || value2 != null
}
fun case_5_4(value1: Any?, value2: Any?): Boolean? {
    contract { returns(null) implies (value1 is String && value2 == null) }
    return value1 !is String || value2 != null
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
fun case_6_1(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(true) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_6_2(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(false) implies (value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_6_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean? {
    contract { returnsNotNull() implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
}
fun case_6_4(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean? {
    contract { returns(null) implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null) }
    return value1 !is Float? || value1 == null || value2 == null || value3 == null || value4 == null
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
fun case_1(value1: Any?, value2: Any?) {
    contracts.case_1(value1, value2)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
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
fun case_2(value1: Any?, value2: Any?) {
    contracts.case_2(value1, value2)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
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
    fun case_3(value1: Any?, value2: Number?) {
        val o = case_3_class()
        contracts.case_3(value1, value2, o.prop_1, this.prop_1)
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
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
fun case_4(value1: Any?, value2: Any?) {
    if (contracts.case_4_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_4_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_4_3(value1, value2) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_4_4(value1, value2) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
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
fun case_5(value1: Any?, value2: Any?) {
    if (contracts.case_5_1(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!contracts.case_5_2(value1, value2)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_5_3(value1, value2) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (contracts.case_5_4(value1, value2) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
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
    fun case_6(value1: Any?, value2: Number?) {
        val o = case_6_class()
        if (contracts.case_6_1(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (!contracts.case_6_2(value1, value2, o.prop_1, this.prop_1)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_6_3(value1, value2, o.prop_1, this.prop_1) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (contracts.case_6_4(value1, value2, o.prop_1, this.prop_1) != null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}
