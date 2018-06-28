/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 2
 DESCRIPTION: Check when exhaustive when enumerated values are checked and contains a null check.
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

fun test1(dir: Direction?): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> 5
}

fun test2(value: Anything?): Int = when (value) {
    Anything.EVERYTHING -> 1
    null -> 2
}

fun test3(value: Color?): Int = when (value) {
    Color.valueOf("0xFF0000") -> 1
    Color.valueOf("0x00FF00") -> 2
    Color.valueOf("0x0000FF") -> 3
    null -> 4
    else -> 5
}
