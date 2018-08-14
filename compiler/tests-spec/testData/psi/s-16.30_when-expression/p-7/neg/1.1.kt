/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [1] Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and type test condition, but missed type in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with one type checking operator.
fun case_1(value: Any): String {
    when (value) {
        is -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with two type checking operators.
fun case_2(value: Any): String {
    when (value) {
        is -> return ""
        is -> return ""
    }

    return ""
}
