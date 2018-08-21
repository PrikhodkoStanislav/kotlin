// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 1
 DESCRIPTION: Smartcast using returns effect with simple type checking conditions.
 */

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1(value: Any?) {
    funWithReturns(value is String)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_2(value: Any?) {
    funWithReturnsAndTypeCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in implies parameter)
fun case_3(value: String?) {
    funWithReturnsAndNotNullCheck(value)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>.length)
}

object case_4_object {
    val prop_1: Int? = 10
}
fun case_4() {
    funWithReturnsAndInvertCondition(case_4_object.prop_1 == null)
    <!DEBUG_INFO_SMARTCAST!>case_4_object.prop_1<!>.inc()
}
