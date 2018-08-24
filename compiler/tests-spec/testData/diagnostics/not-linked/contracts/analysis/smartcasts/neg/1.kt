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
fun case_1(value: Any?) {
    funWithReturns(value is String)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean:notNullCheck
    smartcast:notNull
 */
fun case_2(value: Int?) {
    funWithReturns(value != null)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            boolean:nullCheck
    smartcast:constant,null
 */
fun case_3(value: Int?) {
    funWithReturns(value == null)
    println(<!DEBUG_INFO_CONSTANT!>value<!>)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns
            typeCheck:string
    smartcast:string
 */
fun case_4(value: Any?) {
    funWithReturnsAndTypeCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:notNullCheck
    smartcast:notNull
 */
fun case_5(value: String?) {
    funWithReturnsAndNotNullCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

/*
 CASE KEYWORDS:
    effectsUsage
        returns:nullCheck
    smartcast:constant,null
 */
fun case_6(value: String?) {
    funWithReturnsAndNullCheck(value)
    println(<!DEBUG_INFO_CONSTANT!>value<!>)
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
        returnsNotNull
            boolean
                typeCheck:string
            invertBoolean
                invertTypeCheck:string
    smartcast:string
    if
 */
fun case_8(value: Any?) {
    if (!funWithReturnsTrue(value is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!funWithReturnsTrueAndInvertCondition(value !is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalse(value is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsFalseAndInvertCondition(value !is String)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (funWithReturnsNotNull(value is String) == null) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNotNull(value is String) != null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
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
    smartcast:null,notNull
    if
 */
fun case_9(value: String?) {
    if (!funWithReturnsTrue(value != null)) println(value<!UNSAFE_CALL!>.<!>length)
    if (!funWithReturnsTrueAndInvertCondition(value == null)) println(value<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsFalse(value != null)) println(value<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsFalseAndInvertCondition(value == null)) println(value<!UNSAFE_CALL!>.<!>length)
    if (funWithReturnsNotNull(value != null) == null) println(value<!UNSAFE_CALL!>.<!>length)
    if ((funWithReturnsNotNull(value != null) == null)) println(value<!UNSAFE_CALL!>.<!>length)
    if ((funWithReturnsNotNullAndInvertCondition(value == null) == null)) println(value<!UNSAFE_CALL!>.<!>length)
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
    smartcast:string
    if
 */
fun case_10(value: Any?) {
    if (!funWithReturnsTrueAndTypeCheck(value)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!!funWithReturnsFalseAndTypeCheck(value)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!(funWithReturnsNotNullAndTypeCheck(value) != null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
    if (!!(funWithReturnsNotNullAndTypeCheck(value) == null)) println(value.<!UNRESOLVED_REFERENCE!>length<!>)
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
fun case_11(value: Number?) {
    if (!funWithReturnsTrueAndNotNullCheck(value)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (!funWithReturnsTrueAndNullCheck(value)) println(value)
    if (funWithReturnsFalseAndNotNullCheck(value)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (funWithReturnsFalseAndNullCheck(value)) println(value)
    if ((funWithReturnsNotNullAndNotNullCheck(value) == null)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (!!!(funWithReturnsNotNullAndNotNullCheck(value) != null)) println(value<!UNSAFE_CALL!>.<!>toByte())
    if (!!(funWithReturnsNotNullAndNullCheck(value) == null)) println(value)
}
