/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: Simple when with bound value and type test condition (with sealed classes).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

fun foo1(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
}

fun foo2(value: Expr): Int {
    <!NON_EXHAUSTIVE_WHEN_ON_SEALED_CLASS!>when<!> (value) {
        is Const -> return 1
        is Sum -> return 2
    }

    return -1
}

fun bar1(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    else -> 3
}

fun bar2(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}
