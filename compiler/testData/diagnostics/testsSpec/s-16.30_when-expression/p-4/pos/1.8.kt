/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 8
 DESCRIPTION: 'When' with try expression in the control structure body.
 */

fun case_1(value: Int, value1: String, value2: String) {
    when(value) {
        1 -> try {
            <!UNUSED_EXPRESSION!>4<!>
        } catch (e: Exception) {
            <!UNUSED_EXPRESSION!>5<!>
        }
        2 -> try {
            throw Exception()
        } catch (e: Exception) {
            <!UNUSED_EXPRESSION!>value1<!>
        } finally {
            <!UNUSED_EXPRESSION!>7<!>
        }
        3 -> try {
            try {
                throw Exception()
            } catch (e: Exception) {
                <!UNUSED_LAMBDA_EXPRESSION!>{value2}<!>
            }
        } catch (e: Exception) {
            <!UNUSED_LAMBDA_EXPRESSION!>{2}<!>
        }
        4 -> {
            try {
                <!UNUSED_EXPRESSION!>4<!>
            } catch (e: Exception) {
                <!UNUSED_EXPRESSION!>5<!>
            }
        }
    }
}