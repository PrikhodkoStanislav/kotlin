/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 16
 DESCRIPTION: When with property access expression in the control structure bodies.
 */

class A {
    val prop1 = 1
    val prop2 = 2
    val prop3 = 3
}

fun foo(value: Int, value1: A, value2: A?) {
    when(value) {
        1 -> value1.prop1
        2 -> value2?.prop1
        3 -> value1::prop1.get()
        4 -> {
            value2!!::prop3.get()
        }
    }
}