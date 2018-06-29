/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 3
 DESCRIPTION: Checking for not exhaustive when when covered by all enumerated values, but no null check (or with no null check, but not covered by all enumerated values).
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

fun test1(value: Direction?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
    Direction.SOUTH -> 2
    Direction.NORTH -> 3
    Direction.WEST -> 3
}

fun test2(value: Direction?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
    Direction.SOUTH -> 2
    Direction.NORTH -> 3
    null -> 3
}

fun test3(value: Anything): Int = <!NO_ELSE_IN_WHEN!>when<!>(<!UNUSED_EXPRESSION!>value<!>) {}

fun test4(value: Anything?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Anything.EVERYTHING -> 1
}

fun test5(value: Anything?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    null -> 1
}
