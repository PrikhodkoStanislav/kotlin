// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 2: If the expression is not exhaustive, it has type kotlin.Unit and the whole construct may not be used as an expression, but only as a statement.
 NUMBER: 3
 DESCRIPTION: Using not exhaustive when (sealed classes) as expression.
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()
object A: Expr() {}

fun test1(value: Expr): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    A -> 3
}

fun test2(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    A -> 3
    null -> 4
}

fun test3(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    A -> 4
}

fun test4(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    A -> 3
    null -> 4
}

fun test5(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    null -> 4
}
