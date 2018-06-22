/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 13
 DESCRIPTION: When with postfix operator expression in the control structure bodies.
 */

fun foo(value: Int, value1: Int, value2: Int, value3: Boolean?, value4: Int?) {
    var mutableValue1 = value1
    var mutableValue2 = value2

    when(value) {
        1 -> <!UNUSED_CHANGED_VALUE!>mutableValue1++<!>
        2 -> <!UNUSED_CHANGED_VALUE!>mutableValue2--<!>
        3 -> <!UNUSED_CHANGED_VALUE!>mutableValue1--<!> - <!UNUSED_CHANGED_VALUE!>mutableValue2++<!>
        5 -> mutableValue1++ + <!UNUSED_CHANGED_VALUE!>mutableValue1--<!>
        5 -> !value3!!
        7 -> {
            value4!! - <!UNUSED_CHANGED_VALUE!>mutableValue1--<!> - <!UNUSED_CHANGED_VALUE!>mutableValue2++<!>
        }
    }
}