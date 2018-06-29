// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 8: The bound expression is of an Enum classes type and all enumerated values are checked for equality using constant conditions;
 NUMBER: 1
 DESCRIPTION: Checking for not exhaustive when when not covered by all enumerated values.
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

fun test2(value: Direction): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    Direction.EAST -> 1
}

fun test3(value: Direction): Int = <!NO_ELSE_IN_WHEN!>when<!>(<!UNUSED_EXPRESSION!>value<!>) { }

fun test4(value: Anything): Int = <!NO_ELSE_IN_WHEN!>when<!>(<!UNUSED_EXPRESSION!>value<!>) { }
