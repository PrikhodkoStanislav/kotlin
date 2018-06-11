/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 4: The else branch is a special branch that evaluates if none of the branches above it evaluated to true.
 */

fun whenWithElseOnlyTest(): Int {
    when {
        else -> return 1
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithTrueBeforeWhenTest(): Int {
    when {
        true -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithFalseBeforeWhenTest(): Int {
    when {
        false -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithComplexTrueBeforeWhenTest(): Int {
    when {
        9 == 1 || true == false || !!!true == false && -100 == -100 -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithComplexFalseBeforeWhenTest(): Int {
    when {
        9 == 1 || true == false || !!!!true == false && -100 == -100 -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithElseNotLastPositionTest(): Int {
    when {
        <!ELSE_MISPLACED_IN_WHEN!>else<!> -> return 1
        <!UNREACHABLE_CODE!>true -> return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithElseDuplicateTest(): Int {
    when {
        <!ELSE_MISPLACED_IN_WHEN!>else<!> -> return 1
        <!UNREACHABLE_CODE!>else -> return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}