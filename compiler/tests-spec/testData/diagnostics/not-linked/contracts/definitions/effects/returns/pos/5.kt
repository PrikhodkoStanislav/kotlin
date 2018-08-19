// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 4
 DESCRIPTION: Returns effect with various conditions on 'this'.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: return effect with complex condition composed of function Boolean parameters
fun Any?.case_1(): Boolean {
    contract {
        returns(false) implies (this@case_1 != null)
    }
    return this == null
}

// CASE DESCRIPTION: return effect with complex condition composed of type checking conditions of function parameters
fun case_2(value1: Any?, value2: Any?, value3: Any?) {
    contract {
        returns() implies (value1 is String? || value2 !is Int && value3 !is Nothing?)
    }
    if (value !is String) throw Exception()
}

// CASE DESCRIPTION: return effect with complex condition composed of null-check conditions of function parameters
fun case_3(value1: Any?, value2: Any?, value3: Any?) {
    contract {
        returns() implies (value1 == null || value2 != null && value3 == null)
    }
    if (value is String?) throw Exception()
}
