/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 6
 SENTENCE: [1] When expression with bound value_1 (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value_1, but use different syntax for conditions.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value_1 and empty 'when condition'.
 */

fun case_1(value_1: Int) {
    when (value_1) {
        -> { println(1) }
    }
}