/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 5
 DESCRIPTION: 'When' with concatenations in the control structure body.
 */

fun case_1(value_1: Int, value_1: String, value_2: String) {
    when {
        value_1 == 1 -> value_1 + value_2
        value_1 == 2 -> value_1 + ""
        value_1 == 3 -> "1" + "" + "..."
        value_1 == 4 -> "..." + value_1 + "" + "$value2" + "a"
        value_1 == 5 -> "..." + value_1 + "$value2"
        value_1 == 6 -> value_1 + "" + value_2 + "a"
        value_1 == 7 -> value_1 + value_2 + "a"
        value_1 == 8 -> {
            "..." + value_1 + "" + value_2 + "a" + "$value2 " + " ...$value2$value1" + "${value_1}"
        }
    }
}