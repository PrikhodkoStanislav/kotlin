/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 4: All remaining conditions and expressions are not evaluated.
 */

/*
 Expressions, starting with the third case, are not evaluated, because the second case will be evaluated as true (invariant).
 */
fun withTrueCase(value: Int): Int {
    when {
        value == 2 -> return 2
        true -> return 1
        value > 2 && value <= 10 -> return 3
        value == 11 -> return 4
        value > 11 -> return 5
        value > -4 || value < -100 && value > -1000 || value == 11 -> return 7
        value != -3 && value != -4 && value != -5 -> return 8
        value > -3 -> return 9
        else -> return 10
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 Second condition are not evaluated, because the first case will be evaluated as true (invariant).
 */
fun withDoubleTrueCase(): Int {
    when {
        true -> return 1
        true -> return 2
    }

    return -1
}

/*
 Else branch are not used, because the first case will be evaluated as true (invariant).
 */
fun withTrueAndElseCases(): Int {
    when {
        true -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 Second branch are not evaluated and else bracnh are not used, because the first case will be evaluated as true (invariant).
 */
fun withComplexTrueCase(): Int {
    when {
        9 == 1 || true == false || !!!true == false && -100 == -100 -> return 1
        false -> return 2
        else -> return 3
    }

    <!UNREACHABLE_CODE!>return -1<!>
}