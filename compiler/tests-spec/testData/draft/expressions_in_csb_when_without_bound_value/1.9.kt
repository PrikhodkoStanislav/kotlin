// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 9
 DESCRIPTION: 'When' with elvis operator expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: String?, value_2: String?, value_3: String?) {
    when {
        value_1 == 1 -> value_1 ?: true
        value_1 == 2 -> value_1 ?: value_2 ?: true
        value_1 == 3 -> value_1 ?: value_2 ?: value_3 ?: true
        value_1 == 4 -> value_1!! <!USELESS_ELVIS!>?: true<!>
        value_1 == 5 -> {
            value_2 ?: value_3 ?: true
        }
    }
}