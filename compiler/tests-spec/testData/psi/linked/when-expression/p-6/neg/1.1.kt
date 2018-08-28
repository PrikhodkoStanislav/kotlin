/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 6
 SENTENCE: [1] When expression with bound value_1 (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value_1, but use different syntax for conditions.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value_1 and empty control structure body.
 */

fun case_1(value_1: Int) {
    when (value_1) {
        1 ->
    }
}