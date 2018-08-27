// !DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_LAMBDA_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 8
 DESCRIPTION: 'When' with try expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: String, value_2: String) {
    when {
        value_1 == 1 -> try {
            4
        } catch (e: Exception) {
            5
        }
        value_1 == 2 -> try {
            throw Exception()
        } catch (e: Exception) {
            value_1
        } finally {
            7
        }
        value_1 == 3 -> try {
            try {
                throw Exception()
            } catch (e: Exception) {
                {value_2}
            }
        } catch (e: Exception) {
            {2}
        }
        value_1 == 4 -> {
            try {
                4
            } catch (e: Exception) {
                5
            }
        }
    }
}