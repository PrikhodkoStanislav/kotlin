// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER
// !WITH_CLASSES

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: definitions, effects, returns
 NUMBER: 3
 DESCRIPTION: Returns effect with simple conditions.
 */

import kotlin.internal.contracts.*

// CASE DESCRIPTION: return effect with some condition (in function parameter)
fun case_1(cond: Boolean) {
    contract {
        returns() implies (cond)
    }
    if (!cond) throw Exception()
}

// CASE DESCRIPTION: return effect with invert some condition (in function parameter)
fun case_2(cond: Boolean) {
    contract {
        returns(false) implies (!cond)
    }
    if (!cond) throw Exception()
}

fun Boolean.case_3(): Boolean {
    contract {
        returns() implies (!this@case_3)
    }
    return this == null
}

fun Boolean.case_4(): Boolean {
    contract {
        returns() implies (this@case_4)
    }
    return this == null
}

fun <T : Boolean>T.case_5(): Boolean {
    contract {
        returns(true) implies (this@case_4)
    }
    return this == null
}

fun <T : Boolean>T.case_6(): Boolean {
    contract {
        returns(null) implies (!this@case_4)
    }
    return this == null
}

// CASE DESCRIPTION: return effect with type checking condition
fun case_5(value: Any?) {
    contract {
        returns() implies (value is String)
    }
    if (value !is String) throw Exception()
}

// CASE DESCRIPTION: return effect with invert type checking condition
fun case_6(value: Any?) {
    contract {
        returns() implies (value !is String)
    }
    if (value is String?) throw Exception()
}

fun Any?.case_7(): Boolean {
    contract {
        returns() implies (this@case_7 is Number)
    }
    return this == null
}

fun Any?.case_8(): Boolean {
    contract {
        returns() implies (this@case_8 !is Float?)
    }
    return this == null
}

fun <T>T.case_9(): Boolean {
    contract {
        returns() implies (this@case_4 is Iterable)
    }
    return this == null
}

fun <T : Number?>T.case_10(): Boolean {
    contract {
        returns(null) implies (this@case_4 !is Byte?)
    }
    return this == null
}

// CASE DESCRIPTION: return effect with null-check condition
fun case_5(value: Any?) {
    contract {
        returns() implies (value == null)
    }
    if (value is String?) throw Exception()
}

// CASE DESCRIPTION: return effect with invert null-check condition
fun case_6(value: Any?) {
    contract {
        returns(null) implies (value != null)
    }
    if (value is String?) throw Exception()
}

fun Char?.case_8(): Boolean {
    contract {
        returns() implies (this@case_8 == null)
    }
    return this == null
}

fun String.case_8(): Boolean {
    contract {
        returns(true) implies (this@case_8 != null)
    }
    return this == null
}

fun <T : Number>T?.case_8(): Boolean {
    contract {
        returns() implies (this@case_8 == null)
    }
    return this == null
}

object Obj : _ClassLevel3() {
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    fun <K : Number?>K.case_10(): Boolean {
        contract {
            returns(false) implies (this@Obj !is _ClassLevel1)
        }
        return this == null
    }
}
