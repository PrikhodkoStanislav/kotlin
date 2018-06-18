/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 5
 SENTENCE 4: The bound expression is of type kotlin.Boolean and the conditions contain both:
 */

fun withTrueAndFalse(value: Boolean): Int = when(value) {
    true -> 1
    false -> 2
}

fun withTrueAndFalseAndElse(value: Boolean): Int = when(value) {
    true -> 1
    false -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun withComplexTrueAndFalse(value: Boolean): Int = when(value) {
    true && false && ((true || false)) || true && !!!false && !!!true -> 1
    true && false && ((true || false)) || true && !!!false -> 2
}
