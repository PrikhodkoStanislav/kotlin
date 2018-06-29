/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 2: It has an else entry;
 NUMBER: 1
 DESCRIPTION: Check when exhaustive via else entry (when without bound value).
 */

// CASE DESCRIPTION: Checking for exhaustive in 'when' (several value check branches and 'else' branch).
fun case_1(value: Int): Int = when {
    value == 0 -> 1
    value > 0 && value <= 10 -> 2
    value > 10 && value <= 100 -> 3
    value > 100 -> 4
    else -> 5
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (value check branch and 'else' branch).
fun case_2(): Int = when {
    true -> 1
    else -> 2
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (only 'else' branch).
fun case_3(): Int = when {
    else -> 1
}