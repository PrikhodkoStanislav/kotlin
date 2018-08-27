/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 11
 SENTENCE: [3] The bound expression is of type kotlin.Boolean and the conditions contain both:
 NUMBER: 1
 DESCRIPTION: Check when exhaustive via boolean bound value_1 and evaluating to value_1 true and false.
 */

// CASE DESCRIPTION: Checking for exhaustive 'when' (both boolean value_1 covered).
fun case_1(value_1: Boolean): String = when (value_1) {
    true -> ""
    false -> ""
}

// CASE DESCRIPTION: Checking for exhaustive 'when' (both boolean value_1 as complex expression covered).
fun case_2(value_1: Boolean): String = when (value_1) {
    true && false && ((true || false)) || true && !!!false && !!!true -> ""
    true && false && ((true || false)) || true && !!!false -> ""
}
