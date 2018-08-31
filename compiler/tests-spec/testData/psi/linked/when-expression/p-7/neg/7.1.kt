/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: when-expression
 PARAGRAPH: 7
 SENTENCE: [7] The else condition, which works the exact same way as it would in the form without bound expression.
 NUMBER: 1
 DESCRIPTION: 'When' with invalid else condition.
 */

// CASE DESCRIPTION: 'When' with only one invalid 'else' branch.
fun case_1(value_1: Int) {
    when (value_1) {
        else ->
    }
}

// CASE DESCRIPTION: 'When' with only two invalid 'else' branches.
fun case_2(value_1: Int) {
    when (value_1) {
        else ->
        else ->
    }
}

// CASE DESCRIPTION: 'When' with two not 'else' valid branches and invalid 'else' branch.
fun case_3(value_1: Int) {
    when (value_1) {
        1 -> println("1")
        2 -> println("2")
        else ->
    }
}

// CASE DESCRIPTION: 'When' with one not 'else' valid branch and invalid 'else' branch.
fun case_4(value_1: Int) {
    when (value_1) {
        1 -> println("!")
        else ->
    }
}