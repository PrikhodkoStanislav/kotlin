/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 22
 DESCRIPTION: 'When' with return expression in the control structure body.
 */

fun case_1(value_1: Int): String {
    when {
        value_1 == 1 -> return ""
        value_1 == 2 -> (return "")
        value_1 == 3 -> {
            return ""
        }
    }

    return ""
}