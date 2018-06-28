/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 1
 DESCRIPTION: Check when exhaustive when boolean values are checked and contains a null check.
 */

fun test1(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    null -> 3
}

fun test2(value: Boolean?): Int = when(value) {
    true && false && ((true || false)) || true && !!!false && !!!true -> 1
    true && false && ((true || false)) || true && !!!false -> 2
    null -> 3
}
