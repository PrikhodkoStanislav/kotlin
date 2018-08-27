// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 2
 DESCRIPTION: 'When' with different variants of the logical expressions in the control structure body.
 */

fun case_1(value_1: Int, value_1: Boolean, value_2: Boolean, value_3: String, value_4: Any?) {
    val value_5 = true
    val value_6 = false

    when {
        value_1 == 1 -> !value_1
        value_1 == 2 -> value_5 && value_6 || value_1 && !value_3.isEmpty()
        value_1 == 3 -> value_1 || value_2
        value_1 == 4 -> true && value_2
        value_1 == 5 -> !!(!!(value_4 == null))
        value_1 == 6 -> value_1 || !!!value_5
        value_1 == 7 -> value_1 && !!true && value_5 && value_6
        value_1 == 8 -> !!!!!!!!!value_2
        value_1 == 9 -> !!!!value_6
        value_1 == 10 -> {
            value_6 && value_1 || value_5 || value_6 && !!!false
        }
    }
}