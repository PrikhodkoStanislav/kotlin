/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 21
 DESCRIPTION: 'When' with bound value and throw expression in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and only one throw expression.
fun case_1(value: Any?): Int = when (value) {
    throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
    <!UNREACHABLE_CODE!>else -> 2<!>
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and only one throw expression.
fun case_2(value: Any?): Int {
    when (value) {
        throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and several throw expressions.
fun case_3(value: Any?): Int = when (value) {
    throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
    <!UNREACHABLE_CODE!>throw Exception("Ex") -> 2<!>
    <!UNREACHABLE_CODE!>else -> 4<!>
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and several throw expressions.
fun case_4(value: Any?): Int {
    when (value) {
        throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
        <!UNREACHABLE_CODE!>throw Exception("Ex") -> return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
