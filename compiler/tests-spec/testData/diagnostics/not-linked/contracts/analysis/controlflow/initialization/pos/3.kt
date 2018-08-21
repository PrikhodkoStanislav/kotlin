// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS
// !WITH_ENUM_CLASSES

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 3
 DESCRIPTION: with compelx control flow inside/outside lambda of contract function
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1(value1: _EnumClass?) {
    val value2: Int

    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value1) {
        _EnumClass.NORTH -> funWithExactlyOnceCallsInPlace { value2 = 1 }
        _EnumClass.SOUTH -> funWithExactlyOnceCallsInPlace { value2 = 2 }
        _EnumClass.WEST -> funWithExactlyOnceCallsInPlace { value2 = 3 }
        _EnumClass.EAST -> funWithExactlyOnceCallsInPlace { value2 = 4 }
        null -> funWithExactlyOnceCallsInPlace { value2 = 5 }
    }<!>

    value2.inc()
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_2(value1: Any?) {
    val value2: Int

    funWithAtMostOnceCallsInPlace {
        if (value1 is String) {
            value2 = 0
        } else if (value1 == null) {
            value2 = 1
        } else {
            funWithExactlyOnceCallsInPlace { value2 = 2 }
            value2.dec()
        }
        value2.dec()
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
class case_3(value1: Any?) {
    var value2: Int

    init {
        if (value1 is String) {
            funWithExactlyOnceCallsInPlace { value2 = 0 }
            value2.div(10)
        } else if (value1 == null) {
            funWithAtLeastOnceCallsInPlace { value2 = 1 }
            value2.div(10)
        } else {
            value2 = 2
        }

        value2.div(10)
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_4(value1: _EnumClassSingle?) {
    var value2: Int

    funWithAtMostOnceCallsInPlace {
        <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value1) {
            _EnumClassSingle.EVERYTHING -> {
                funWithExactlyOnceCallsInPlace { value2 = 1 }
                ++value2
            }
            null -> {
                funWithAtLeastOnceCallsInPlace { value2 = 2 }
                --value2
            }
        }<!>
        value2.minus(5)
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_5() {
    var value2: Int

    try {
        funWithAtLeastOnceCallsInPlace { value2 = 10 }
    } catch (e: Exception) {
        funWithExactlyOnceCallsInPlace { value2 = 1 }
    }

    value2++
}