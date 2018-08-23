// !LANGUAGE: +AllowContractsForCustomFunctions +UseReturnsEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 8
 DESCRIPTION: Smartcast using some returns effects on the same values.
 */

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_1(value: Any?) {
    funWithReturns(value is Number?)
    println(<!DEBUG_INFO_SMARTCAST!>value<!>?.toByte())
    if (funWithReturnsTrue(value is Number)) {
        println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
        if (funWithReturnsNotNull(value is Int) != null) {
            println(<!DEBUG_INFO_SMARTCAST!>value<!>.inv())
        }
    }
}

// CASE DESCRIPTION: string smartcast using return effect with type checking condition (in function parameter)
fun case_2(value: Any?) {
    if (!funWithReturnsFalse(value is Number?)) {
        println(<!DEBUG_INFO_SMARTCAST!>value<!>?.toByte())
        funWithReturns(value is Number)
        println(<!DEBUG_INFO_SMARTCAST!>value<!>.toByte())
        if (funWithReturnsNotNull(value is Int) != null) {
            println(<!DEBUG_INFO_SMARTCAST!>value<!>.inv())
        }
    }
}
