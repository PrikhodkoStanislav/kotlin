/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value and not allowed (if when used as expression) return in the control structure body.
 */

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_1(value: Int) = when {
    value == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_2(value: Int) = when {
    value == 1 -> {}
    value == 2 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_3(value: Int) = when {
    value == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    value == 2 -> {}
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_4(value: Int) = when {
    value == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    value == 2 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun <!IMPLICIT_NOTHING_RETURN_TYPE!>case_5<!>(value: Int) = <!NO_ELSE_IN_WHEN!>when<!> {
    value == 1 -> <!RETURN_TYPE_MISMATCH, RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
}
