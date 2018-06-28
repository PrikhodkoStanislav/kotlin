/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 4
 DESCRIPTION: When with bound value and comparison expressions in when entry.
 */

fun test1(value: Boolean, value1: Int, value2: Int): Int = when (value) {
    value1 > 900 -> 1
    value2 < 900 && value1 >= 900 -> 2
    value2 <= 1800 && value1 >= 400 -> 3
    else -> 4
}

fun test2(value: Boolean, value1: Int, value2: Int): Int {
    when (value) {
        value1 > 900 -> return 1
        value2 < 900 && value1 >= 900 -> return 2
        value2 <= 1800 && value1 >= 400 -> return 3
    }

    return -1
}

fun test3(value: Boolean): Int = when (value) {
    1100 > 900 -> 1
    9 < 900 && 111 >= 900 -> 2
}

fun test4(value: Boolean): Int = when (value) {
    1100 > 900 -> 1
    9 < 900 && 111 >= 900 -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun test5(value: Boolean): Int {
    when (value) {
        1100 > 900 -> return 1
    }

    return -1
}

fun test6(value: Boolean): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        1100 > 900 -> return 1
        9 < 900 && 111 >= 900 -> return 2
    }<!>
}

fun test7(value: Boolean): Int {
    when (value) {
        1100 > 900 -> return 1
        9 < 900 && 111 >= 900 -> return 2
        <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> <!UNUSED_EXPRESSION!>3<!>
    }

    return -1
}