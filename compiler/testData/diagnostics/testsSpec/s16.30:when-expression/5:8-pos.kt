/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 5
 SENTENCE 8: The bound expression is of an Enum classes type and all enumerated values are checked for equality using constant conditions;
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun withEnum(dir: Direction): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
}

fun withEnumAndElse(dir: Direction): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun withSingleEnum(value: Anything): Int = when (value) {
    Anything.EVERYTHING -> 1
}

fun withSingleEnumWithElse(value: Anything): Int = when (value) {
    Anything.EVERYTHING -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

fun withEnumValueOf(value: Color): Int = when (value) {
    Color.valueOf("0xFF0000") -> 1
    Color.valueOf("0x00FF00") -> 2
    Color.valueOf("0x0000FF") -> 3
    else -> 4
}
