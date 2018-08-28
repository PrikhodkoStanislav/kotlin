/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 6
 SENTENCE: [1] When expression with bound value_1 (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value_1, but use different syntax for conditions.
 NUMBER: 3
 DESCRIPTION: 'When' with bound value_1 and with invalid list of the conditions in 'when entry'.
 */

fun case_1(value_1: Int, value_2: _BasicTypesProvider): String {
    when (value_1) {
        -10000, value_2.getInt(11), Int.MIN_VALUE, -> return ""
        21, , -> return ""
        , , -> return ""
        , value_2.getInt(11) -> return ""
        value_2.getInt(11) Int.MIN_VALUE -> return ""
        value_2.getInt(11) 200 -> return ""
    }

    return ""
}
