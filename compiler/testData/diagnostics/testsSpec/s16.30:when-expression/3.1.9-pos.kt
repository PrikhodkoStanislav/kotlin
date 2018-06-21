/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 9
 DESCRIPTION: When with elvis operator expression in the control structure bodies.
 */

fun foo(value: Int, value1: String?, value2: String?, value3: String?) {
    when {
        value == 1 -> value1 ?: <!UNUSED_EXPRESSION!>true<!>
        value == 2 -> value1 ?: value2 ?: <!UNUSED_EXPRESSION!>true<!>
        value == 3 -> value1 ?: value2 ?: value3 ?: <!UNUSED_EXPRESSION!>true<!>
        value == 4 -> value1!! <!USELESS_ELVIS!>?: <!UNUSED_EXPRESSION!>true<!><!>
        value == 5 -> {
            value2 ?: value3 ?: <!UNUSED_EXPRESSION!>true<!>
        }
    }
}