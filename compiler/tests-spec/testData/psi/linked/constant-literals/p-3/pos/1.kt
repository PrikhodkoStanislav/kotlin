/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: constant-literals
 PARAGRAPH: 3
 SENTENCE: [1] Keywords true and false denote boolean literals of the same values.
 NUMBER: 1
 DESCRIPTION: Empty 'when' with missed bound value.
 */

fun case_1() {
    true
}

fun case_2() {
    false
}

fun case_3() = true
fun case_4() = false

val case_5 = true
val case_6 = false
