// !DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_LAMBDA_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 18
 DESCRIPTION: 'When' with lambda literal in the control structure body.
 */

fun case_1(value_1: Int) {
    val lambda1 = { 0 }

    when {
        value_1 == 1 -> lambda1
        value_1 == 2 -> {{{{}}}}
        value_1 == 3 -> { -> (Int)
            {
                arg: Int -> {
                    { println(arg) }
                }
            }
        }
    }
}