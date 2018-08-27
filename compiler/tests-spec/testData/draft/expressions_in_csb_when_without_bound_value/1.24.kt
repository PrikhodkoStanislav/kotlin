/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 24
 */

fun case_1(value_1: Int) {
    loop1@ while (true) {
        loop2@ while (true) {
            when {
                value_1 == 1 -> break@loop1
                value_1 == 2 -> {
                    break@loop2
                }
            }
        }
    }
}