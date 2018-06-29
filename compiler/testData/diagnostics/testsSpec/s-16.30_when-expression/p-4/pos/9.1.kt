/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 9: The else condition, which works the exact same way as it would in the form without bound expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and else branch.
 */

// CASE DESCRIPTION: Simple when with bound value, with 'else' branch and expression as when condition.
fun case_1(value: Int?): Int = when (value) {
    0 -> 0
    1 -> 1
    2 -> 2
    else -> 3
}

// CASE DESCRIPTION: Simple when with bound value, with 'else' branch and type test as when condition.
fun case_2(value: Any): Int = when (value) {
    is Int -> 0
    is Boolean -> 1
    is String -> 2
    else -> 3
}

// CASE DESCRIPTION: Simple when with bound value, with 'else' branch and range test as when condition.
fun case_2(value: Int): Int = when (value) {
    in -10..10 -> 0
    in 11..1000 -> 1
    in 1000..Int.MAX_VALUE -> 2
    else -> 3
}