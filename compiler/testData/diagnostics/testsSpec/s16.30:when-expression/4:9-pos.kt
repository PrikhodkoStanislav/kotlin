/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 9: The else condition, which works the exact same way as it would in the form without bound expression.
 */

fun withElseBranch(value: Int?): Int = when (value) {
    0 -> 0
    1 -> 1
    2 -> 2
    else -> 3
}