/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 3: If the current condition evaluates to true, the corresponding expression is evaluated and the value of when expression is the same as the evaluated expression.
 */

// The function must return 10 in the worst case
fun withTrueCase(value: Int?): Int = when {
    value == null -> 0
    value == 1 -> 1
    value == 2 -> 2
        <!DEBUG_INFO_SMARTCAST!>value<!> > 2 && <!DEBUG_INFO_SMARTCAST!>value<!> <= 10 -> 3
    value == 11 -> 4
        <!DEBUG_INFO_SMARTCAST!>value<!> > 11 -> 5
        <!DEBUG_INFO_SMARTCAST!>value<!> > -4 || <!DEBUG_INFO_SMARTCAST!>value<!> < -100 && <!DEBUG_INFO_SMARTCAST!>value<!> > -1000 || value == 11 -> 7
    value != -3 && value != -4 && value != -5 -> 8
        <!DEBUG_INFO_SMARTCAST!>value<!> > -3 -> 9
    else -> 10 // will be definitely evaluated
}

// The function must return "" in the worst case
fun withTrueCaseAndElse(value: Any?): String = when {
    value == 1 -> value as String
    value == 2 -> value as String
    value == 11 -> value as String
    true -> "" // will be definitely evaluated
    else -> "a"
}

// The function must return 6
fun withComplexBoolCases(): Int = when {
    1 == 2 -> 0
    1 == 1 && 1 >= 5 -> 1
    true && false -> 2
    false || !true -> 3
    false && false && true -> 4
    ((true)) && (false) || ((false)) -> 5
    true == true -> 6 // will be definitely evaluated
    else -> 7
}

// The function must return 0
fun withBoolCases(): Int {
    val whenValue = when {
        false -> 0
        true -> 1 // will be definitely evaluated
        else -> 2
    }

    return whenValue
}

// The function must return 0 (as well as when expression)
fun withBoolAndElseCases(): Int {
    val whenValue = when {
        false -> {
            1
        }
        true -> { // will be definitely evaluated
            0
        }
        else -> {
            2
        }
    }

    return whenValue
}