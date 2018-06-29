/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 12
 DESCRIPTION: 'When' with bound value and prefix expressions in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and increment/decrement operator.
fun case_1(value: Int, value1: Int, value2: Int): Int {
    var value1Mutable = value1
    var value2Mutable = value2

    return when (value) {
        ++value1Mutable -> 1
        --value2Mutable -> 2
        else -> 5
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and increment/decrement operator.
fun case_2(value: Int, value1: Int, value2: Int): Int {
    var value1Mutable = value1
    var value2Mutable = value2

    when (value) {
        ++value1Mutable -> return 1
        --value2Mutable -> return 2
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and not operator.
fun case_3(value: Boolean, value1: Boolean): Int {
    return when (value) {
        !value1 -> 1
        !!!value1 -> 2
        else -> 3
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and not operator.
fun case_4(value: Boolean, value1: Boolean): Int {
    when (value) {
        !value1 -> return 1
        !!!value1 -> return 2
    }

    return -1
}
