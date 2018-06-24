/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 2: It has an else entry;
 NUMBER: 3
 DESCRIPTION: Check when exhaustive via else entry (when with bound value, redundant else).
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

sealed class Expr2
data class Const2(val number: Int) : Expr2()

fun test1(value: Direction): Int = when(value) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun test2(value: Direction?): Int = when(value) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> 5
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 6
}

fun test3(value: Anything): Int = when(value) {
    Anything.EVERYTHING -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun test4(value: Anything?): Int = when(value) {
    Anything.EVERYTHING -> 1
    null -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun test5(value: Direction?): Int = when(value) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> 5
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 6
}

fun test6(value: Boolean): Int = when(value) {
    true -> 1
    false -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun test7(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    null -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}

fun test8(value: Expr): Int = when(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}

fun test9(value: Expr?): Int = when(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    null -> 4
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun test10(value: Expr2): Int = when(value) {
    is Const2 -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

fun test11(value: Expr2?): Int = when(value) {
    is Const2 -> 1
    null -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun test12(value: Expr2): Int = when(value) {
    <!USELESS_IS_CHECK!>is Expr2<!> -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

fun test13(value: Expr2?): Int = when(value) {
    is Expr2 -> 1
    null -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}