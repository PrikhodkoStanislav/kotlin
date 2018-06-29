/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and 'when entry' with range expression, but wuthout contains operator.
 */

// CASE DESCRIPTION: 'When' with one contains operator.
fun case_1(value: Int): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with two contains operators.
fun case_2(value: Int): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return 1
        in<!SYNTAX!><!> -> return 2
    }

    return -1
}
