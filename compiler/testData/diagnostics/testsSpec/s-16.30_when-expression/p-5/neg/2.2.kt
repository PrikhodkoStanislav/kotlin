// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 2: If the expression is not exhaustive, it has type kotlin.Unit and the whole construct may not be used as an expression, but only as a statement.
 NUMBER: 2
 DESCRIPTION: Using not exhaustive when (boolean) as expression.
 */

fun test1(value: Boolean): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    true -> 1
}

fun test2(value: Boolean?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    true -> 1
    false -> 2
}

fun test3(value: Boolean?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    true -> 1
    null -> 2
}
