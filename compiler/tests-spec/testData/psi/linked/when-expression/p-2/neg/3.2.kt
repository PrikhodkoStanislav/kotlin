/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 2
 SENTENCE: [3] When expression has two different forms: with bound value_1 and without it.
 NUMBER: 2
 DESCRIPTION: Empty 'when' with missed 'when entries' section.
 */

// CASE DESCRIPTION: 'When' with bound value_1.
fun case_1(value_1: Int) {
    when (value_1)
}

// CASE DESCRIPTION: 'When' without bound value_1, but with parentheses.
fun case_2() {
    when ()
}

// CASE DESCRIPTION: 'When' without bound value_1 and parentheses.
fun case_3() {
    when
}
