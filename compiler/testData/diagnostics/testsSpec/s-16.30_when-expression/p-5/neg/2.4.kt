// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 2: If the expression is not exhaustive, it has type kotlin.Unit and the whole construct may not be used as an expression, but only as a statement.
 NUMBER: 4
 DESCRIPTION: Using not exhaustive when (enum classes) as expression.
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

fun test1(value: Direction): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
    Direction.SOUTH -> 2
    Direction.NORTH -> 3
}

fun test2(value: Direction?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
    Direction.SOUTH -> 2
    Direction.NORTH -> 3
    Direction.WEST -> 3
}

fun test3(value: Direction?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
    Direction.SOUTH -> 2
    Direction.NORTH -> 3
    null -> 3
}

fun test4(value: Anything): Int = <!NO_ELSE_IN_WHEN!>when<!>(<!UNUSED_EXPRESSION!>value<!>) {

}

fun test5(value: Anything?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Anything.EVERYTHING -> 1
}

fun test6(value: Anything?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    null -> 1
}

