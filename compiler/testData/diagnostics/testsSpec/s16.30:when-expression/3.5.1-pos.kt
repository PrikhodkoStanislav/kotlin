/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 5: The else branch is a special branch that evaluates if none of the branches above it evaluated to true.
 NUMBER: 1
 DESCRIPTION: Simple when without bound and with else branch.
 */

fun foo(value: Int): Int = when {
    value == 2 -> 1
    value > 2 && value <= 10 -> 2
    value == 11 -> 3
    value > 11 -> 4
    value > -4 || value < -100 && value > -1000 || value == 11 -> 5
    value != -3 && value != -4 && value != -5 -> 6
    value > -3 -> 7
    else -> 8
}