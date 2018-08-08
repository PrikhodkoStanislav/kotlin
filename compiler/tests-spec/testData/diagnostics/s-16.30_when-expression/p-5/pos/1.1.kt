/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 1: The else entry is also special in the sense that it must be the last entry in the expression, otherwise a compiler error must be generated.
 NUMBER: 1
 DESCRIPTION: 'When' without bound value and with else branch in the last position.
 */

// CASE DESCRIPTION: 'When' used as statement
fun case_1(value: Int): String {
    when {
        value == 1 -> return ""
        value == 2 -> return ""
        value > 2 && value <= 10 -> return ""
        value == 11 -> return ""
        value > 11 -> return ""
        value > -4 || value < -100 && value > -1000 || value == 11 -> return ""
        value != -3 && value != -4 && value != -5 -> return ""
        value > -3 -> return ""
        else -> return ""
    }
}

// CASE DESCRIPTION: 'When' used as expression
fun case_2(value: Int): String = when {
    value == 1 -> ""
    value == 2 -> ""
    value > 2 && value <= 10 -> ""
    value == 11 -> ""
    value > 11 -> ""
    value > -4 || value < -100 && value > -1000 || value == 11 -> ""
    value != -3 && value != -4 && value != -5 -> ""
    value > -3 -> ""
    else -> ""
}
