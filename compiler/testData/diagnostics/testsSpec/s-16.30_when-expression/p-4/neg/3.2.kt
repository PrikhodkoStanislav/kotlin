/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and type test condition, but missed type in 'when entry'.
 */

// CASE DESCRIPTION: 'When' with one type checking operator.
fun case_1(value: Any): Int {
    when (value) {
        is<!SYNTAX!><!> -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with two type checking operators.
fun case_2(value: Any): Int {
    when (value) {
        is<!SYNTAX!><!> -> return 1
        is<!SYNTAX!><!> -> return 2
    }

    return -1
}
