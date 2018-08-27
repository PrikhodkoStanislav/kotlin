/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 12
 DESCRIPTION: 'When' with prefix operator expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: Int, value_2: Int, value_3: Boolean) {
    var mutableValue1 = value_1
    var mutableValue2 = value_2

    when {
        value_1 == 1 -> ++mutableValue1
        value_1 == 2 -> --mutableValue2
        value_1 == 3 -> --mutableValue1 - ++mutableValue2
        value_1 == 5 -> ++mutableValue1 + --mutableValue1
        value_1 == 5 -> !value_3
        value_1 == 6 -> !!!!!!value_3
        value_1 == 7 -> {
            ++mutableValue1 + --mutableValue1
        }
    }
}