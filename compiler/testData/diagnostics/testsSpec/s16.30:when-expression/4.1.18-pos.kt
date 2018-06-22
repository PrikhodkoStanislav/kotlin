/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 18
 DESCRIPTION: When with lambda literal in the control structure bodies.
 */

fun foo(value: Int) {
    val lambda1 = { 0 }

    when(value) {
        1 -> <!UNUSED_EXPRESSION!>lambda1<!>
        2 -> {<!UNUSED_LAMBDA_EXPRESSION!>{{{}}}<!>}
        3 -> <!UNUSED_LAMBDA_EXPRESSION!>{ -> (Int)
            {
                arg: Int -> {
                    { println(arg) }
                }
            }
        }<!>
    }
}