/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: when-expression
 PARAGRAPH: 7
 SENTENCE: [3] Contains test condition: containment operator followed by an expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value_1 and 'when condition' with range expression, but without contains operator.
 */

// CASE DESCRIPTION: 'When' with one contains operator.
fun case_1(value_1: Int): String {
    when (value_1) {
        in -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with two contains operators.
fun case_2(value_1: Int): String {
    when (value_1) {
        in -> return ""
        in -> return ""
    }

    return ""
}
