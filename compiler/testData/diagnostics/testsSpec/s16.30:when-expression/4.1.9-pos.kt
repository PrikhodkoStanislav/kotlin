/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 9
 DESCRIPTION: When with elvis operator expression in the control structure bodies.
 */

fun foo(value: Int, value1: String?, value2: String?, value3: String?) {
    when(value) {
        1 -> value1 ?: <!UNUSED_EXPRESSION!>true<!>
        2 -> value1 ?: value2 ?: <!UNUSED_EXPRESSION!>true<!>
        3 -> value1 ?: value2 ?: value3 ?: <!UNUSED_EXPRESSION!>true<!>
        4 -> value1!! <!USELESS_ELVIS!>?: <!UNUSED_EXPRESSION!>true<!><!>
        5 -> {
            value2 ?: value3 ?: <!UNUSED_EXPRESSION!>true<!>
        }
    }
}