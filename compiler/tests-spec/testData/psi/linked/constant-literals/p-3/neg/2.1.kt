/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: constant-literals
 PARAGRAPH: 3
 SENTENCE: [2] These are strong keywords which cannot be used as identifiers unless [escaped][Escaped identifiers].
 NUMBER: 1
 DESCRIPTION: Empty 'when' with missed bound value.
 */

fun case_1() {
    true = false
}

fun case_2() {
    val true = false
}
