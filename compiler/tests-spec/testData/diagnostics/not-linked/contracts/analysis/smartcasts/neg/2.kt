// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 2
 DESCRIPTION: Smartcast using returns effect with complex type checking and not-null conditions as paremeter of contract function.
 */

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean
                invertTypeCheck:string,number
                disjunction
    smartcast:string,number
 */
fun case_1(value1: Any?, value2: Any?) {
    funWithReturns(value1 !is String || value2 !is Number)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean
                typeCheck:string,number
                conjunction
    smartcast:string,number
 */
fun case_2(value1: Any?, value2: Any?) {
    funWithReturnsAndInvertCondition(value1 is String && value2 is Number)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            invertBoolean
                typeCheck:string
                conjunction
                nullCheck
    smartcast:string,notNull
 */
fun case_3(value1: Any?, value2: Any?) {
    funWithReturnsAndInvertCondition(value1 is String && value2 == null)
    println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
    println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean
                invertTypeCheck:nullableFloat
                disjunction
                nullCheck
    smartcast:float,notNull
 */
fun case_4(value1: Any?, value2: Number?) {
    funWithReturns(value1 !is Float? || value1 == null || value2 == null)
    println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
    println(value2?.toByte())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean
                invertTypeCheck:nullableFloat
                disjunction
                nullCheck:property
    smartcast:float,notNull
    class
 */
class case_5_class {
    val prop_1: Int? = 10

    fun case_5(value1: Any?, value2: Number?) {
        val o = case_5_class()
        funWithReturns(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null)
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
        println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            boolean
                invertTypeCheck:string,number
                disjunction
        returnsFalse
            boolean
                invertTypeCheck:string,number
                disjunction
        returnsNotNull
            boolean
                invertTypeCheck:string,number
                disjunction
        returnsNull
            boolean
                invertTypeCheck:string,number
                disjunction
    smartcast:string,number
 */
fun case_6(value1: Any?, value2: Any) {
    if (funWithReturnsTrue(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!funWithReturnsFalse(value1 !is String || value2 !is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNull(value1 !is String || value2 !is Number) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNull(value1 !is String || value2 !is Number) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertBoolean
                typeCheck:string,number
                conjunction
        returnsFalse
            invertBoolean
                typeCheck:string,number
                conjunction
        returnsNotNull
            invertBoolean
                typeCheck:string,number
                conjunction
        returnsNull
            invertBoolean
                typeCheck:string,number
                conjunction
    smartcast:string,number
 */
fun case_7(value1: Any?, value2: Any?) {
    if (funWithReturnsTrueAndInvertCondition(value1 is String && value2 is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!funWithReturnsFalseAndInvertCondition(value1 is String && value2 is Number)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 is String && value2 is Number) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNullAndInvertCondition(value1 is String && value2 is Number) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertBoolean
                typeCheck:string
                conjunction
                notNullCheck
        returnsFalse
            invertBoolean
                typeCheck:string
                conjunction
                notNullCheck
        returnsNotNull
            invertBoolean
                typeCheck:string
                conjunction
                notNullCheck
        returnsNull
            invertBoolean
                typeCheck:string
                conjunction
                notNullCheck
    smartcast:string,notNull
 */
fun case_8(value1: Any?, value2: Any?) {
    if (funWithReturnsTrueAndInvertCondition(value1 is String && value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (!funWithReturnsFalseAndInvertCondition(value1 is String && value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNotNullAndInvertCondition(value1 is String && value2 == null) != null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
    if (funWithReturnsNullAndInvertCondition(value1 is String && value2 == null) == null) {
        println(value1.<!UNRESOLVED_REFERENCE!>length<!>)
        println(value2?.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>toByte<!>())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertBoolean
                inverTypeCheck:nullableFloat
                disjunction
                nullCheck
        returnsFalse
            invertBoolean
                inverTypeCheck:nullableFloat
                disjunction
                nullCheck
        returnsNotNull
            invertBoolean
                inverTypeCheck:nullableFloat
                disjunction
                nullCheck
        returnsNull
            invertBoolean
                inverTypeCheck:nullableFloat
                disjunction
                nullCheck
    smartcast:string,notNull
 */
fun case_9(value1: Any?, value2: Number?) {
    if (funWithReturnsTrue(value1 !is Float? || value1 == null || value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
    }
    if (!funWithReturnsFalse(value1 !is Float? || value1 == null || value2 == null)) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
    }
    if (funWithReturnsNotNull(value1 is Float? && value1 != null && value2 != null) == null) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
    }
    if (funWithReturnsNull(value1 is Float? && value1 != null && value2 != null) != null) {
        println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
        println(value2?.toByte())
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            invertBoolean
                inverTypeCheck:nullableFloat
                conjunction
                nullCheck:property
        returnsFalse
            invertBoolean
                inverTypeCheck:nullableFloat
                conjunction
                nullCheck:property
        returnsNotNull
            invertBoolean
                inverTypeCheck:nullableFloat
                conjunction
                nullCheck:property
        returnsNull
            invertBoolean
                inverTypeCheck:nullableFloat
                conjunction
                nullCheck:property
    smartcast:float,notNull
    class
 */
class case_10_class {
    val prop_1: Int? = 10

    fun case_10(value1: Any?, value2: Number?) {
        val o = case_10_class()
        if (funWithReturnsTrue(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (!funWithReturnsFalse(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null)) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (funWithReturnsNotNull(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null) != null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (funWithReturnsNull(value1 !is Float? || value1 == null || value2 == null || o.prop_1 == null || this.prop_1 == null) == null) {
            println(value1.<!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>dec<!>())
            println(value2?.toByte())
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}
