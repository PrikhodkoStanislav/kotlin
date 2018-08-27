/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 14
 DESCRIPTION: 'When' with indexing expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: List<Int>, value_2: List<List<List<List<Int>>>>?) {
    when {
        value_1 == 1 -> value_1[0]
        value_1 == 2 -> value_2!![0][1]
        value_1 == 3 -> value_2!![0][1][-1]
        value_1 == 4 -> {
            value_2!![0][0][0][0]
        }
    }
}