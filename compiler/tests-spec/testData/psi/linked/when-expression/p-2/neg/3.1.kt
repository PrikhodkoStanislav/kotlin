/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 2
 SENTENCE: [3] When expression has two different forms: with bound value_1 and without it.
 NUMBER: 1
 DESCRIPTION: Empty 'when' with missed bound value_1.
 */

fun case_1() {
    when () {}
}
