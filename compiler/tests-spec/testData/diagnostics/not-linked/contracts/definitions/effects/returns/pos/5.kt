// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 5
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
fun case_2(value_1: Any?, value_2: Any?, value_3: Any?) {
    contract {
        returns() implies (value_1 is String? || value_2 !is Int && value_3 !is Nothing?)
    }
    if (!(value_1 is String? || value_2 !is Int && value_3 !is Nothing?)) throw Exception()
}

// CASE DESCRIPTION: return effect with complex condition composed of null-check conditions of function parameters
fun case_3(value_1: Any?, value_2: Any?, value_3: Any?) {
    contract {
        returns() implies (value_1 == null || value_2 != null && value_3 == null)
    }
    if (!(value_1 == null || value_2 != null && value_3 == null)) throw Exception()
}
