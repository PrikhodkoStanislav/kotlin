/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and 'when condition' with range expression, but wuthout contains operator.
 */

// CASE DESCRIPTION: 'When' with one contains operator.
fun case_1(value: Int): String {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with two contains operators.
fun case_2(value: Int): String {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return ""
        in<!SYNTAX!><!> -> return ""
    }

    return ""
}
