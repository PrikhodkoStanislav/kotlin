// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [2] Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 3
 DESCRIPTION: 'When' without bound value_1 and not allowed (if when used as expression) return in when entry.
 TODO: Move to section, which will be say about fun asiignment
 */

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_1(<!UNUSED_PARAMETER!>value_1<!>: Int) = when {
    <!RETURN_TYPE_MISMATCH, RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> -> <!UNREACHABLE_CODE!>1<!>
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_2(value_1: Int) = when {
    value_1 == 1 -> {}
    <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> -> <!UNREACHABLE_CODE!>{}<!>
    <!UNREACHABLE_CODE!>else -> {}<!>
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_3(<!UNUSED_PARAMETER!>value_1<!>: Int) = when {
    <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> -> <!UNREACHABLE_CODE!>{}<!>
    <!UNREACHABLE_CODE!>value_1 == 2 -> {}<!>
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun case_4(<!UNUSED_PARAMETER!>value_1<!>: Int) = when {
    <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> -> <!UNREACHABLE_CODE!>{}<!>
    <!UNREACHABLE_CODE!>return -> {}<!>
}

// CASE DESCRIPTION: 'When' with break expression (without label).
fun <!IMPLICIT_NOTHING_RETURN_TYPE!>case_5<!>(<!UNUSED_PARAMETER!>value_1<!>: Int) = when {
    <!RETURN_TYPE_MISMATCH, RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> -> <!RETURN_TYPE_MISMATCH, UNREACHABLE_CODE!>return<!>
    <!UNREACHABLE_CODE!><!RETURN_TYPE_MISMATCH!>return<!> -> <!RETURN_TYPE_MISMATCH!>return<!><!>
}
