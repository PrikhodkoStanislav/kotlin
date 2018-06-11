/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 3: All remaining conditions and expressions are not evaluated.
 */

fun whenIntWithTrueCaseTest(value: Int): Int {
    when {
        true -> return 1
        value == 2 -> return 2
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

fun whenIntWithDuplicateTrueCaseTest(): Int {
    when {
        true -> return 1
        true -> return 2
    }

    return -1
}

fun whenWithTrueAndElseTest(): Int {
    when {
        true -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun whenWithComplexTrueTest(): Int {
    when {
        9 == 1 || true == false || !!!true == false && -100 == -100 -> return 1
        false -> return 2
        else -> return 3
    }

    <!UNREACHABLE_CODE!>return -1<!>
}