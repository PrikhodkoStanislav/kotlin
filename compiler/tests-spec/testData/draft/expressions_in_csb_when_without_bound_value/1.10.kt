/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 10
 DESCRIPTION: 'When' with range expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: Int?, value_2: Int) {
    when {
        value_1 == 1 -> 1..10
        value_1 == 2 -> 1..1
        value_1 == 3 -> 1..-10
        value_1 == 4 -> value_1!!..4
        value_1 == 5 -> 1..value_2
        value_1 == 6 -> {
            11..192391293912931L
        }
    }
}