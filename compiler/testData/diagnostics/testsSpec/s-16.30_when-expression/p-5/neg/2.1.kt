// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 2: If the expression is not exhaustive, it has type kotlin.Unit and the whole construct may not be used as an expression, but only as a statement.
 NUMBER: 1
 DESCRIPTION: Using not exhaustive when (without else bracnh) as expression.
 */

fun test1(value: Int): Int = <!NO_ELSE_IN_WHEN!>when<!> {
    value == 1 -> 1
    value == 2 -> 2
    value == 3 -> 3
}

fun test2(value: Int): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    1 -> 1
    2 -> 2
    3 -> 3
}
