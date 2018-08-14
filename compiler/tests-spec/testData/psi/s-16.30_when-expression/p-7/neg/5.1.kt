/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [5] Any other expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and not allowed spread operator in 'when condition'.
 */

fun case_1(value: Int, value1: ArrayList<Int>): String {
    when (value) {
        *value1 -> return ""
        *arrayOf("a", "b", "c") -> return ""
        *listOf(null, null, null) -> return ""
    }

    return ""
}
