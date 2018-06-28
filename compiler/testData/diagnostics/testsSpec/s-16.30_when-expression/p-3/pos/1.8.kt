/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 8
 DESCRIPTION: When with try expression in the control structure bodies.
 */

fun foo(value: Int, value1: String, value2: String) {
    when {
        value == 1 -> try {
            <!UNUSED_EXPRESSION!>4<!>
        } catch (e: Exception) {
            <!UNUSED_EXPRESSION!>5<!>
        }
        value == 2 -> try {
            throw Exception()
        } catch (e: Exception) {
            <!UNUSED_EXPRESSION!>value1<!>
        } finally {
            <!UNUSED_EXPRESSION!>7<!>
        }
        value == 3 -> try {
            try {
                throw Exception()
            } catch (e: Exception) {
                <!UNUSED_LAMBDA_EXPRESSION!>{value2}<!>
            }
        } catch (e: Exception) {
            <!UNUSED_LAMBDA_EXPRESSION!>{2}<!>
        }
        value == 4 -> {
            try {
                <!UNUSED_EXPRESSION!>4<!>
            } catch (e: Exception) {
                <!UNUSED_EXPRESSION!>5<!>
            }
        }
    }
}