// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS
// !WITH_ENUM_CLASSES

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 3
 DESCRIPTION: Wrong assignments or uninitialized usages in compelx control flow inside/outside lambda of contract function with calls in place effect
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
    uninitialized:val
    smartInit:val
    smartcast:inited
    when:nonExhaustive
 */
fun case_1(value1: _EnumClass?) {
    val value2: Int

    <!NON_EXHAUSTIVE_WHEN!>when<!> (value1) {
        _EnumClass.NORTH -> funWithExactlyOnceCallsInPlace { value2 = 1 }
        _EnumClass.SOUTH -> funWithExactlyOnceCallsInPlace { value2 = 2 }
        _EnumClass.EAST -> funWithExactlyOnceCallsInPlace { value2 = 4 }
        null -> funWithExactlyOnceCallsInPlace { value2 = 5 }
    }

    <!UNINITIALIZED_VARIABLE!>value2<!>.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce
        nested
    uninitialized:val
    smartInit:val
    smartcast:inited
    if:else,elseIf
 */
fun case_2(value1: Any?) {
    val value2: Int

    funWithAtMostOnceCallsInPlace {
        if (value1 is String) {
            value2 = 0
        } else if (value1 == null) {
            value2 = 1
        } else {
            funWithAtMostOnceCallsInPlace { value2 = 2 }
        }
        <!UNINITIALIZED_VARIABLE!>value2<!>.dec()
    }
    value2.dec()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeaseOnce,unknown
    uninitialized:var
    smartInit:var
    smartcast:inited
    if:else,elseIf
    class:
        fields:init,uninitialized
        init
 */
class case_3(value1: Any?) {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>var value2: Int<!>

    init {
        if (value1 is String) {
            funWithUnknownCallsInPlace { value2 = 0 }
            <!UNINITIALIZED_VARIABLE!>value2<!>.div(10)
        } else if (value1 == null) {
            funWithAtLeastOnceCallsInPlace { value2 = 1 }
            value2.div(10)
        } else {
            value2 = 2
        }

        <!UNINITIALIZED_VARIABLE!>value2<!>.div(10)
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,unknown,atMostOnce
        nested
    uninitialized:var
    smartInit:var
    smartcast:inited
    when:exhaustive
 */
fun case_4(value1: _EnumClassSingle?) {
    var value2: Int

    funWithAtMostOnceCallsInPlace {
        when (value1) {
            _EnumClassSingle.EVERYTHING -> {
                funWithExactlyOnceCallsInPlace { value2 = 1 }
                ++value2
            }
            null -> {
                funWithUnknownCallsInPlace { value2 = 2 }
            }
        }
        <!UNINITIALIZED_VARIABLE!>value2<!>.minus(5)
    }
    value2.minus(5)
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atMostOnce,atLeastOnce
    uninitialized:var
    smartInit:var
    smartcast:inited
    try
 */
fun case_5() {
    var value2: Int

    try {
        funWithAtLeastOnceCallsInPlace { value2 = 10 }
    } catch (e: Exception) {
        funWithAtMostOnceCallsInPlace { value2 = 1 }
    }

    <!UNINITIALIZED_VARIABLE!>value2<!>++
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce
    smartInit:var
    smartcast:inited
    try:finally
    throw
 */
fun case_6() {
    var value2: Int

    try {
        funWithAtLeastOnceCallsInPlace { value2 = 10 }
    } catch (e: Exception) {
        throw Exception()
    } finally {
        println(<!UNINITIALIZED_VARIABLE!>value2<!>.inc())
    }

    value2++
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,atMostOnce
    smartInit:var
    smartcast:inited
    try
    return
 */
fun case_7() {
    var value1: Int

    try {
        funWithAtLeastOnceCallsInPlace { value1 = 10 }
    } catch (e: Exception) {
        try {
            funWithAtLeastOnceCallsInPlace { value1 = 10 }
        } catch (e: Exception) {
            funWithAtMostOnceCallsInPlace { value1 = 10 }
        }
    }

    println(<!UNINITIALIZED_VARIABLE!>value1<!>.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,mostOnce
    smartInit:val
    smartcast:inited
 */
fun case_8() {
    val x: Int
    funWithExactlyOnceCallsInPlace outer@ {
        funWithAtMostOnceCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                x = 42
                return@outer
            }
        }
        throw Exception()
    }
    println(x.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atMostOnce,unknown
    smartInit:val
    smartcast:inited
    return:nonlocal
    throw
 */
fun case_9() {
    val x: Int
    funWithExactlyOnceCallsInPlace outer@ {
        funWithAtMostOnceCallsInPlace {
            funWithUnknownCallsInPlace {
                <!VAL_REASSIGNMENT!>x<!> = 42
            }
            return@outer
        }
        throw Exception()
    }
    println(<!UNINITIALIZED_VARIABLE!>x<!>.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,exactlyOnce
    smartInit:val
    smartcast:inited
    return:nonlocal
 */
fun case_10() {
    val x: Int
    funWithExactlyOnceCallsInPlace outer@ {
        funWithAtMostOnceCallsInPlace {
            x = 42
            return@outer
        }
    }
    println(<!UNINITIALIZED_VARIABLE!>x<!>.inc())
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,unknown
    smartInit:var
    smartcast:inited
    return:nonlocal
 */
fun case_11() {
    var x: Int
    funWithAtLeastOnceCallsInPlace outer@ {
        funWithAtMostOnceCallsInPlace {
            x = 41
            return@outer
        }
        funWithUnknownCallsInPlace {
            x = 42
            return@outer
        }
        return@outer
    }
    println(<!UNINITIALIZED_VARIABLE!>x<!>.inc())
}
