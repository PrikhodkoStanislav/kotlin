// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 1
 DESCRIPTION: Smartcast using returns effect with simple type checking and not-null conditions.
 */

fun case_1(value: Any?) {
    funWithReturns(value is String)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_2(value: Int?) {
    funWithReturns(value != null)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.inc())
}

fun case_3(value: Int?) {
    funWithReturns(value == null)
    println(<!DEBUG_INFO_CONSTANT!>value<!>)
}

fun case_4(value: Any?) {
    funWithReturnsAndTypeCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_5(value: String?) {
    funWithReturnsAndNotNullCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_6(value: String?) {
    funWithReturnsAndNullCheck(value)
    println(<!DEBUG_INFO_CONSTANT!>value<!>)
}

object case_7_object {
    val prop_1: Int? = 10
}
fun case_7() {
    funWithReturnsAndInvertCondition(case_7_object.prop_1 == null)
    <!DEBUG_INFO_SMARTCAST!>case_7_object.prop_1<!>.inc()
}

fun case_8(value: Any?) {
    if (funWithReturnsTrue(value is String)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (funWithReturnsTrueAndInvertCondition(value !is String)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!funWithReturnsFalse(value is String)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!funWithReturnsFalseAndInvertCondition(value !is String)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (funWithReturnsNotNull(value is String) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!(funWithReturnsNotNull(value is String) == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_9(value: String?) {
    if (funWithReturnsTrue(value != null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (funWithReturnsTrueAndInvertCondition(value == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!funWithReturnsFalse(value != null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!funWithReturnsFalseAndInvertCondition(value == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (funWithReturnsNotNull(value != null) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!(funWithReturnsNotNull(value != null) == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!(funWithReturnsNotNullAndInvertCondition(value == null) == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_10(value: Any?) {
    if (funWithReturnsTrueAndTypeCheck(value)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!funWithReturnsFalseAndTypeCheck(value)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (funWithReturnsNotNullAndTypeCheck(value) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
    if (!(funWithReturnsNotNullAndTypeCheck(value) == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

fun case_11(value: Number?) {
    if (funWithReturnsTrueAndNotNullCheck(value)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
    if (funWithReturnsTrueAndNullCheck(value)) println(<!DEBUG_INFO_CONSTANT!>value<!>)
    if (!funWithReturnsFalseAndNotNullCheck(value)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
    if (!funWithReturnsFalseAndNullCheck(value)) println(<!DEBUG_INFO_CONSTANT!>value<!>)
    if (!(funWithReturnsNotNullAndNotNullCheck(value) == null)) println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
    if (funWithReturnsNotNullAndNotNullCheck(value) != null) println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
    if (funWithReturnsNotNullAndNullCheck(value) != null) println(<!DEBUG_INFO_CONSTANT!>value<!>)
}
