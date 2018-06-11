/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 */

fun whenIntTest(value: Int): Int {
    when {
        value == 1 -> return 1
        value == 2 -> return 2
        value > 2 && value <= 10 -> return 3
        value == 11 -> return 4
        value > 11 -> return 5
        value > -4 || value < -100 && value > -1000 || value == 11 -> return 7
        value != -3 && value != -4 && value != -5 -> return 8
        value > -3 -> return 9
    }

    return -1
}

fun whenIntWithUnrachableCodeTest(value: Int): Int {
    when {
        value == 1 -> return 1
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
