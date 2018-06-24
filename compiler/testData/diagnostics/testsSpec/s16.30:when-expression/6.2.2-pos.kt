/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 2: It has an else entry;
 NUMBER: 2
 DESCRIPTION: Check when exhaustive via else entry (when with bound value).
 */

fun test1(value: Int): Int = when(value) {
    0 -> 1
    1 -> 2
    2 -> 3
    3 -> 4
    else -> 5
}

fun test2(value: Boolean): Int = when(value) {
    true -> 1
    else -> 2
}

fun test3(): Int = when(true) {
    true -> 1
    else -> 2
}

fun test4(value: Int): Int = when(<!UNUSED_EXPRESSION!>value<!>) {
    else -> 1
}