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

// CASE DESCRIPTION: Checking for exhaustive in 'when' (both enum values and null value covered).
fun case_1(dir: Direction?): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> 5
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (single enum value and null value covered).
fun case_2(value: Anything?): Int = when (value) {
    Anything.EVERYTHING -> 1
    null -> 2
}
