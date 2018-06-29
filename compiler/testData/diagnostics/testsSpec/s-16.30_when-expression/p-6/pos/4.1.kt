/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 4: The bound expression is of type kotlin.Boolean and the conditions contain both:
 NUMBER: 1
 DESCRIPTION: Check when exhaustive via boolean bound value and evaluating to value true and false.
 */

// CASE DESCRIPTION: Checking for exhaustive in 'when' (both boolean value covered).
fun case_1(value: Boolean): Int = when(value) {
    true -> 1
    false -> 2
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (both boolean value as complex expression covered).
fun case_2(value: Boolean): Int = when(value) {
    true && false && ((true || false)) || true && !!!false && !!!true -> 1
    true && false && ((true || false)) || true && !!!false -> 2
}
