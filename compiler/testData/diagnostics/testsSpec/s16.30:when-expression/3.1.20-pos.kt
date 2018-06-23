/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 20
 DESCRIPTION: When with this expression in the control structure bodies.
 */

class A {
    val prop1 = 1
    var prop2 = 2
    val lambda1 = {1}

    fun fun1(): Int {
        return 1
    }

    fun foo(value: Int) {
        when {
            value == 1 -> <!UNUSED_EXPRESSION!>this<!>
            value == 2 -> ((<!UNUSED_EXPRESSION!>this<!>))
            value == 3 -> this::prop1.get()
            value == 4 -> this.prop1
            value == 5 -> this.lambda1()
            value == 6 -> this::lambda1.get()()
            value == 7 -> this.fun1()
            value == 8 -> this::fun1.invoke()
        }
    }
}