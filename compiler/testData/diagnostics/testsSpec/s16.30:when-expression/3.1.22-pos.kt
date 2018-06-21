/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 22
 DESCRIPTION: When with continue expression in the control structure bodies.
 */

fun foo(value: Int) {
    loop1@ while (true) {
        loop2@ while (true) {
            when {
                value == 1 -> continue@loop1
                value == 2 -> {
                    continue@loop2
                }
            }
        }
    }
}