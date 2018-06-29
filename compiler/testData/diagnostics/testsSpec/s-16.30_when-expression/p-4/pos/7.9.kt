/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 9
 DESCRIPTION: 'When' with bound value and elvis operator expressions in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and several entries.
fun case_1(value: Boolean, value1: Int?): Int = when (value) {
    value1 ?: true -> 1
    value1!! > 100 -> 2
    value1 <!USELESS_ELVIS!>?: false<!> -> 3
    else -> 4
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and several entries.
fun case_2(value: Boolean, value1: Int?): Int {
    when (value) {
        value1 ?: true -> return 1
        value1!! > 100 -> return 2
        value1 <!USELESS_ELVIS!>?: false<!> -> return 3
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and only one entry.
fun case_3(value: Boolean, value1: Int?): Int = when (value) {
    value1 ?: true -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and only one entry.
fun case_4(value: Boolean, value1: Int?): Int {
    when (value) {
        value1 ?: true -> return 1
    }

    return -1
}