/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [2] Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: 'When' without bound value_1 and with invalid 'else' branch.
 */

// CASE DESCRIPTION: 'When' with only one invalid 'else' branch.
fun case_1() {
    when {
        else ->
    }
}

// CASE DESCRIPTION: 'When' with only two invalid 'else' branches.
fun case_2() {
    when {
        else ->
        else ->
    }
}

// CASE DESCRIPTION: 'When' with two not 'else' valid branches and invalid 'else' branch.
fun case_3(value_1: Int) {
    when {
        value_1 == 1 -> println("1")
        value_1 == 2 -> println("2")
        else ->
    }
}

// CASE DESCRIPTION: 'When' with one not 'else' valid branch and invalid 'else' branch.
fun case_4(value_1: Int) {
    when {
        value_1 == 1 -> println("!")
        else ->
    }
}