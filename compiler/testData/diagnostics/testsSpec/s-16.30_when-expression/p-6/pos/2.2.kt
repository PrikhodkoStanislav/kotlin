/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 2: It has an else entry;
 NUMBER: 2
 DESCRIPTION: Check when exhaustive via else entry (when with bound value).
 */

// CASE DESCRIPTION: Checking for exhaustive in 'when' (several branches).
fun case_1(value: Int): Int = when(value) {
    0 -> 1
    1 -> 2
    2 -> 3
    3 -> 4
    else -> 5
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (value check branch and 'else' branch).
fun case_2(value: Boolean): Int = when(value) {
    true -> 1
    else -> 2
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' with constant bound value (value check branch and 'else' branch).
fun case_3(): Int = when (true) {
    true -> 1
    else -> 2
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (only 'else' branch).
fun case_4(value: Int): Int = when(<!UNUSED_EXPRESSION!>value<!>) {
    else -> 1
}