// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 1
 DESCRIPTION: Smartcast using returns effect with simple type checking and not-null conditions.
 */

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean
                typeCheck:string
    smartcast:string
 */
fun case_1(value_1: Any?) {
    funWithReturns(value_1 is String)
    println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean:notNullCheck
    smartcast:notNull
 */
fun case_2(value_1: Int?) {
    funWithReturns(value_1 != null)
    println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean:nullCheck
    smartcast:constant,null
 */
fun case_3(value_1: Int?) {
    funWithReturns(value_1 == null)
    println(<!DEBUG_INFO_CONSTANT!>value_1<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            typeCheck:string
    smartcast:string
 */
fun case_4(value_1: Any?) {
    funWithReturnsAndTypeCheck(value_1)
    println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:notNullCheck
    smartcast:notNull
 */
fun case_5(value_1: String?) {
    funWithReturnsAndNotNullCheck(value_1)
    println(<!DEBUG_INFO_SMARTCAST!>value_1<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:nullCheck
    smartcast:constant,null
 */
fun case_6(value_1: String?) {
    funWithReturnsAndNullCheck(value_1)
    println(<!DEBUG_INFO_CONSTANT!>value_1<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            invertBoolean
                nullCheck:property
    smartcast:constant,notNull,property
    object
 */
object case_7_object {
    val prop_1: Int? = 10
}
fun case_7() {
    funWithReturnsAndInvertCondition(case_7_object.prop_1 == null)
    <!DEBUG_INFO_SMARTCAST!>case_7_object.prop_1<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            boolean
                typeCheck:string
            invertBoolean
                invertTypeCheck:string
        returnsFalse
            boolean
                typeCheck:string
            invertBoolean
                invertTypeCheck:string
        returnsNull
            boolean
                typeCheck:string
            invertBoolean
                invertTypeCheck:string
        returnsNotNull
            boolean
                typeCheck:string
            invertBoolean
                invertTypeCheck:string
    smartcast:string
    if
 */
fun case_8(value_1: Any?) {
    if (!funWithReturnsTrue(value_1 is String)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!funWithReturnsTrueAndInvertCondition(value_1 !is String)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalse(value_1 is String)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalseAndInvertCondition(value_1 !is String)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsNotNull(value_1 is String) == null) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNotNull(value_1 is String) != null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNull(value_1 is String) == null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsNull(value_1 is String) != null) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            boolean:notNullCheck
            invertBoolean:nullCheck
        returnsFalse
            boolean:notNullCheck
            invertBoolean:nullCheck
        returnsNotNull
            boolean:notNullCheck
            invertBoolean:nullCheck
        returnsNull
            boolean:notNullCheck
            invertBoolean:nullCheck
    smartcast:null,notNull
    if
 */
fun case_9(value_1: String?) {
    if (!funWithReturnsTrue(value_1 != null)) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (!funWithReturnsTrueAndInvertCondition(value_1 == null)) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsFalse(value_1 != null)) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsFalseAndInvertCondition(value_1 == null)) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsNotNull(value_1 != null) == null) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsNotNullAndInvertCondition(value_1 == null) == null) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsNull(value_1 != null) != null) println(value_1<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsNullAndInvertCondition(value_1 == null) != null) println(value_1<!UNSAFE_CALL!>.<!>length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue
            typeCheck:string
        returnsFalse
            typeCheck:string
        returnsNotNull
            typeCheck:string
        returnsNull
            typeCheck:string
    smartcast:string
    if
 */
fun case_10(value_1: Any?) {
    if (!funWithReturnsTrueAndTypeCheck(value_1)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!!funWithReturnsFalseAndTypeCheck(value_1)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNotNullAndTypeCheck(value_1) != null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!!(funWithReturnsNotNullAndTypeCheck(value_1) == null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!!(funWithReturnsNullAndTypeCheck(value_1) != null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNullAndTypeCheck(value_1) == null)) println(value_1.<!UNRESOLVED_REFERENCE!>length<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returnsTrue:notNullCheck,nullCheck
        returnsFalse:notNullCheck,nullCheck
        returnsNotNull:notNullCheck,nullCheck
    smartcast:null,notNull
    if
 */
fun case_11(value_1: Number?) {
    if (!funWithReturnsTrueAndNotNullCheck(value_1)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (!funWithReturnsTrueAndNullCheck(value_1)) println(value_1)
    if (funWithReturnsFalseAndNotNullCheck(value_1)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (funWithReturnsFalseAndNullCheck(value_1)) println(value_1)
    if ((funWithReturnsNotNullAndNotNullCheck(value_1) == null)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (!!!(funWithReturnsNotNullAndNotNullCheck(value_1) != null)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (!!(funWithReturnsNotNullAndNullCheck(value_1) == null)) println(value_1)
    if (!(funWithReturnsNullAndNotNullCheck(value_1) == null)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (!!(funWithReturnsNullAndNotNullCheck(value_1) != null)) println(value_1<!UNSAFE_CALL!>.<!>toByte())
    if (!!!(funWithReturnsNullAndNullCheck(value_1) == null)) println(value_1)
}
