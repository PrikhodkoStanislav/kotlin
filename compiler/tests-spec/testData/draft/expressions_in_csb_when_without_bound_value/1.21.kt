/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 21
 DESCRIPTION: 'When' with throw expression in the control structure body.
 */

fun case_1(value_1: Int) {
    val lambda_1 = {
        throw Exception("Ex")
    }

    when {
        value_1 == 1 -> lambda_1()
        value_1 == 2 -> throw Exception("Ex")
        value_1 == 3 -> {
            try {
                throw Exception("Ex")
            } catch (e: Exception) {

            }
        }
    }
}