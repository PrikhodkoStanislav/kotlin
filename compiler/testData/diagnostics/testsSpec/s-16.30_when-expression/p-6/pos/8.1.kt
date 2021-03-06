/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 8: The bound expression is of an Enum classes type and all enumerated values are checked for equality using constant conditions;
 NUMBER: 1
 DESCRIPTION: Check when exhaustive when all enumerated values are checked.
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

// CASE DESCRIPTION: Checking for exhaustive 'when' (all enum values covered).
fun case_1(dir: Direction): String = when (dir) {
    Direction.EAST -> ""
    Direction.NORTH -> ""
    Direction.SOUTH -> ""
    Direction.WEST -> ""
}

// CASE DESCRIPTION: Checking for exhaustive 'when' (single enum value covered).
fun case_2(value: Anything): String = when (value) {
    Anything.EVERYTHING -> ""
}
