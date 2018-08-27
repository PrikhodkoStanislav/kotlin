/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 13
 DESCRIPTION: 'When' with postfix operator expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: Int, value_2: Int, value_3: Boolean?, value_4: Int?) {
    var mutableValue1 = value_1
    var mutableValue2 = value_2

    when {
        value_1 == 1 -> <!UNUSED_CHANGED_VALUE!>mutableValue1++<!>
        value_1 == 2 -> <!UNUSED_CHANGED_VALUE!>mutableValue2--<!>
        value_1 == 3 -> <!UNUSED_CHANGED_VALUE!>mutableValue1--<!> - <!UNUSED_CHANGED_VALUE!>mutableValue2++<!>
        value_1 == 5 -> mutableValue1++ + <!UNUSED_CHANGED_VALUE!>mutableValue1--<!>
        value_1 == 5 -> !value_3!!
        value_1 == 7 -> {
            value_4!! - <!UNUSED_CHANGED_VALUE!>mutableValue1--<!> - <!UNUSED_CHANGED_VALUE!>mutableValue2++<!>
        }
    }
}