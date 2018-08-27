// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 20
 DESCRIPTION: 'When' with this expression in the control structure body.
 */

class A {
    val prop_1 = 1
    val lambda_1 = {1}
    fun fun_1(): Int { return 1 }

    fun case_1(value_1: Int) {
        when {
            value_1 == 1 -> this
            value_1 == 2 -> ((this))
            value_1 == 3 -> this::prop_1.get()
            value_1 == 4 -> this.prop_1
            value_1 == 5 -> this.lambda_1()
            value_1 == 6 -> this::lambda_1.get()()
            value_1 == 7 -> this.fun_1()
            value_1 == 8 -> this::fun_1.invoke()
        }
    }
}