// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 17
 DESCRIPTION: 'When' with fun literal in the control structure body.
 */

fun case_1(value_1: Int) {
    val fun_1 = fun(): Int {
        return 0
    }

    when {
        value_1 == 1 -> fun() {}
        value_1 == 2 -> fun(): Boolean {
            return when {
                else -> true
            }
        }
        value_1 == 3 -> fun_1
        value_1 == 4 -> {
            fun() {fun() {fun() {}}}
        }
    }
}