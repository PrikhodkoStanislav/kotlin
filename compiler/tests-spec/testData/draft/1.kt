/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value_1 and not allowed (if when used as expression) return in the control structure body.
 TODO: Move to section, which will be say about fun asiignment
 */

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_1(value_1: Int) = when {
    value_1 == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_2(value_1: Int) = when {
    value_1 == 1 -> {}
    value_1 == 2 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_3(value_1: Int) = when {
    value_1 == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    value_1 == 2 -> {}
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_4(value_1: Int) = when {
    value_1 == 1 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    value_1 == 2 -> <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
    else -> {}
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun <!IMPLICIT_NOTHING_RETURN_TYPE!>case_5<!>(value_1: Int) = <!NO_ELSE_IN_WHEN!>when<!> {
    value_1 == 1 -> <!RETURN_TYPE_MISMATCH, RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!>
}
