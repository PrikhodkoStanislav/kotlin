/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 3
 DESCRIPTION: Simple when with bound value and type test condition (with invert type checking operator).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

fun foo0(value: Expr): Int = when (value) {
    is Const -> 1
    !is Mul -> 2
    <!USELESS_IS_CHECK!>is Mul<!> -> 3
}

fun foo1(value: Expr): Int = when (value) {
    is Const -> 1
    !is Mul -> 2
    <!USELESS_IS_CHECK!>is Mul<!> -> 3
}

fun foo2(value: Expr): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        !is Const -> return 1
        !is Sum -> return 2
        !is Mul -> return 3
    }<!>

    <!UNREACHABLE_CODE!>return -1<!>
}

fun bar1(value: Expr): Int = when (value) {
    is Sum -> 1
    !is Sum -> 2
}

fun bar2(value: Expr): Int {
    when (value) {
        is Sum -> return 1
        !is Sum -> return 2
        else -> return 3
    }
}

fun bar3(value: Expr): Int = when (value) {
    is Sum -> 1
    !is Sum -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun bar4(value: Expr): Int = when (value) {
    !is Const -> 1
    <!USELESS_IS_CHECK!>is Const<!> -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun bar5(value: Expr): Int = when (value) {
    !is Const -> 1
    <!USELESS_IS_CHECK!>is Const<!> -> 2
}

fun bar6(value: Expr): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        !is Const -> return 1
        <!USELESS_IS_CHECK!>is Const<!> -> return 2
    }<!>
}

fun bar7(value: Expr): Int = when (value) {
    !is Const -> 1
    else -> 2
}

fun bar8(value: Expr): Int = when (value) {
    is Const -> 1
    else -> 2
}

fun bar9(value: Any): Int = when (value) {
    is Int -> 1
    is Boolean -> 2
    !is String -> 3
    <!USELESS_IS_CHECK!>is String<!> -> 4
    else -> 5
}

fun bar10(value: Any): Int = when (value) {
    is String -> 1
    !is String -> 2
    else -> 3
}