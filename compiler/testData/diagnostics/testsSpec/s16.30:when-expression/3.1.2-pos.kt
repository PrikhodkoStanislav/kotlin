/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 2
 DESCRIPTION: When with different variants of the logical expressions in the control structure bodies.
 */

fun foo(value: Int, value1: Boolean, value2: Boolean, value3: String, value4: Any?) {
    val value3 = true
    val value4 = false

    when {
        value == 1 -> !value1
        value == 2 -> <!UNUSED_EXPRESSION!>value3 && value4 || value1 && !value3.isEmpty()<!>
        value == 3 -> <!UNUSED_EXPRESSION!>value1 || value2<!>
        value == 4 -> <!UNUSED_EXPRESSION!>true && value2<!>
        value == 5 -> !!(!!value4 == null)
        value == 6 -> value1 || !!!value3
        value == 7 -> <!UNUSED_EXPRESSION!>value1 && !!true && value3 && value4<!>
        value == 8 -> !!!!!!!!!value2
        value == 9 -> !!!!value4
        value == 10 -> {
            <!UNUSED_EXPRESSION!>value4 && value1 || value3 || value4 && !!!false<!>
        }
    }
}