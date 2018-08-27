/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 4
 DESCRIPTION: 'When' with different variants of the comparison expression in the control structure body.
 */

fun case_1(
    value_1: Int,
    value_1: Byte,
    value_2: Short,
    value_3: Int,
    value_4: Long,
    value_5: Float,
    value_6: Double,
    value_7: Char
) {
    when {
        value_1 == 1 -> value_1 >= 100
        value_1 == 2 -> value_2 < 32000
        value_1 == 3 -> value_3 <= 11
        value_1 == 4 -> value_4 > 1243124124431443L
        value_1 == 5 -> value_5 < -.000001f
        value_1 == 6 -> value_6 >= 10.0
        value_1 == 7 -> value_7 <= 254.toChar()
        value_1 == 15 -> {
            value_1 < -100 || value_2 > 10 && value_3 <= 320302 || !(value_4 > -0L) && value_5 <= .1F || !!!(value_6 > -.999999999) && value_7 < 10.toChar()
        }
    }
}