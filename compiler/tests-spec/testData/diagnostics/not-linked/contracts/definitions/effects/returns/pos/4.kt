// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER
// !WITH_CLASSES

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 4
 DESCRIPTION: Returns effect with complex conditions (using logic operators || and &&).
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: return effect with complex condition composed of function Boolean parameters
fun case_1(cond1: Boolean, cond2: Boolean, cond3: Boolean) {
    contract {
        returns() implies (cond && !cond2 || cond3)
    }
    if (!cond) throw Exception()
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

fun Boolean?.case_3(): Boolean {
    contract {
        returns(true) implies (this@case_3 != null && this@case_3)
    }
    return this == null
}

fun <T : Boolean>T?.case_3(): Boolean {
    contract {
        returns(true) implies (this@case_3 != null && this@case_3 !is Nothing && this@case_3)
    }
    return this == null
}

fun <T>T.case_3(): Boolean {
    contract {
        returns(false) implies (this@case_3 is Char || this@case_3 == null) // rudundant second null-check
    }
    return this == null
}

/*
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-1982
 */
fun <T>T?.case_3(): Boolean {
    contract {
        returns() implies (this@case_4 == null || this@case_4 is Boolean? && !this@case_4) // duplicate of null-check
    }
    return this == null
}

class A<T> : _ClassLevel5() {
    inner class B {
        fun <K : Number?>K.case_10(): Boolean {
            contract {
                returns() implies (this@B !is _ClassLevel1 && this@B != null || this@A is _ClassLevel1 && this@case_10 is Float)
            }
            return this == null
        }

        fun case_11(): Boolean {
            contract {
                returns() implies (this@B !is _ClassLevel1 || this@A is _ClassLevel1 || this@B == null)
            }
            return this == null
        }
    }
}
