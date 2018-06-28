/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 2: It has an else entry;
 NUMBER: 1
 DESCRIPTION: Check when exhaustive via else entry (when without bound value).
 */

fun test1(value: Int): Int = when {
    value == 0 -> 1
    value > 0 && value <= 10 -> 2
    value > 10 && value <= 100 -> 3
    value > 100 -> 4
    else -> 5
}

fun test2(): Int = when {
    true -> 1
    else -> 2
}

fun test3(): Int = when {
    else -> 1
}