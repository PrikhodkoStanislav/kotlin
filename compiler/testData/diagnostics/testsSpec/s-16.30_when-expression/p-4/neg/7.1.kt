/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and non-expressions in 'when entry'.
 */

// CASE DESCRIPTION: 'When' with while statement.
fun case_1(value: Int): Int {
    when (value) {
        <!EXPRESSION_EXPECTED!>while (false) {}<!> -> return 1
        (<!EXPRESSION_EXPECTED!>while (true) {}<!>)<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

// CASE DESCRIPTION: 'When' with do-while statement.
fun case_2(value: Int): Int {
    when (value) {
        <!EXPRESSION_EXPECTED!>do {} while (false)<!> -> return 1
        (<!EXPRESSION_EXPECTED!>do {} while (true)<!>)<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

// CASE DESCRIPTION: 'When' with for statement.
fun case_3(value: Int, value1: List<Int>): Int {
    when (value) {
        <!EXPRESSION_EXPECTED!>for (value2 in value1) {}<!> -> return 1
        (<!EXPRESSION_EXPECTED!>for (value2 in value1) {}<!>) -> return 2
    }

    return -1
}

// CASE DESCRIPTION: 'When' with assignments.
fun case_4(value: Int): Int {
    var <!ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE!>value1<!>: Int
    var value2 = 10

    when (value) {
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!><!UNUSED_VALUE!>value1 =<!> 10<!> -> return 1
        (<!ASSIGNMENT_IN_EXPRESSION_CONTEXT!><!UNUSED_VALUE!>value1 =<!> 10<!>) -> return 2
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>value2 += 10<!> -> return 3
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>value2 -= 10<!> -> return 4
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>value2 *= 10<!> -> return 5
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>value2 /= 10<!> -> return 6
        <!ASSIGNMENT_IN_EXPRESSION_CONTEXT!>value2 %= 10<!> -> return 7
    }

    return -1
}