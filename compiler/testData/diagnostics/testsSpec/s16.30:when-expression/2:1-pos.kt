// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 2
 SENTENCE 1: When expression is alike a conditional expression in the sense that it allows several different control structure bodies (cases) to be evaluated depending on boolean conditions.
 */

/*
 Not exhaustive when expression (of the type kotlin.Unit).

 The same as the following conditional expression without else branch:

 if (value == 0) {
    return 1
 } else if (value > 0 && value <= 10) {
    return 2
 } else if (value > 10 && value <= 100) {
    return 3
 } else if (value > 100) {
    return 4
 }
 */
fun conditionsWithInt(value: Int): Int {
    when {
        value == 0 -> return 1
        value > 0 && value <= 10 -> return 2
        value > 10 && value <= 100 -> return 3
        value > 100 -> return 4
    }

    return -1
}

/*
 Exhaustive when expression.

 The same as the following conditional expression:

 if (value == 0) {
    return 1
 } else if (value > 0 && value <= 10) {
    return 2
 } else if (value > 10 && value <= 100) {
    return 3
 } else {
    return 4
 }
 */
fun conditionsWithIntAndElseBranch(value: Int): Int {
    when {
        value == 0 -> return 1
        value > 0 && value <= 10 -> return 2
        value > 10 && value <= 100 -> return 3
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 Exhaustive when expression with storing in variable and comparison with if
 */
fun conditionsWithIntAndElseBranchAndIfComparison(value: Int): Boolean {
    val whenValue = when {
        value == 0 -> 1
        value > 0 && value <= 10 -> 2
        value > 10 && value <= 100 -> 3
        else -> 4
    }

    val ifValue = if (value == 0) 1
        else if (value > 0 && value <= 10) 2
        else if (value > 10 && value <= 100) 3
        else 4

    whenValue checkType { _<Int>() }
    ifValue checkType { _<Int>() }

    return whenValue == ifValue // must be true
}

/*
 The same as the following conditional expression:

 if (!value) {
    return 1
 }
 */
fun conditionWithBool(value: Boolean): Int {
    when {
        !value -> return 1
    }

    return -1
}

/*
 The same as the following conditional expression:

 if (value) {
    return 1
 } else {
    return 2
 }
 */
fun conditionWithBoolAndElseBranch(value: Boolean): Int {
    when {
        value -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 Exhaustive when expression with storing in variable and comparison with if
 */
fun conditionWithBoolAndElseBranchAndIfComparison(value: Boolean): Boolean {
    val whenValue = when {
        value -> 1
        else -> 2
    }

    val ifValue = if (value) 1 else 2

    whenValue checkType { _<Int>() }
    ifValue checkType { _<Int>() }

    return whenValue == ifValue // must be true
}

/*
 The same as the following conditional expression:

 if (value == 0) {
    return 0
 else if (value.isEmpty()) {
    return 1
 } else if (value == "a") {
    return 2
 } else if (true) {
    return 3
 }
 */
fun conditionsWithString(value: String?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!>.isEmpty() -> return 1
        value == "a" -> return 2
        true -> return 3
    }

    return -1
}
