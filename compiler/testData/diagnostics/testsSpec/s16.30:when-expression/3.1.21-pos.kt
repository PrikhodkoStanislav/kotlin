/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 21
 DESCRIPTION: When with return expression in the control structure bodies.
 */

fun foo(value: Int): Int {
    when {
        value == 1 -> return 1
        value == 2 -> (return 2)
        value == 3 -> {
            return 3
        }
    }

    return -1
}