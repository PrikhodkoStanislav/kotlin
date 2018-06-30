/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 10
 DESCRIPTION: 'When' with bound value and range expressions in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression).
fun case_1(value: Any?, value1: Int, value2: Long): String = when (value) {
    -100..-1000 -> ""
    0..0 -> ""
    -100..9 -> ""
    10..100 -> ""
    101..value1 -> ""
    value1..value2 -> ""
    value2..102301239123L -> ""
    null -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement).
fun case_2(value: Any?, value1: Int, value2: Long): String {
    when (value) {
        -100..-1000 -> return ""
        0..0 -> return ""
        -100..9 -> return ""
        10..100 -> return ""
        101..value1 -> return ""
        value1..value2 -> return ""
        value2..102301239123L -> return ""
        null -> return ""
    }

    return ""
}
