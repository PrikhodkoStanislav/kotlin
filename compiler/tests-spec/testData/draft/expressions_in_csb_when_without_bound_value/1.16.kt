// !WITH_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 16
 DESCRIPTION: 'When' with property access expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: _Class, value_2: _Class?) {
    when {
        value_1 == 1 -> value_1.prop_1
        value_1 == 2 -> value_2?.prop_1
        value_1 == 3 -> value_1::prop_1.get()
        value_1 == 4 -> {
            value_2!!::prop_3.get()
        }
    }
}