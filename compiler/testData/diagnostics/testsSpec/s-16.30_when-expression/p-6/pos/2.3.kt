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

// CASE DESCRIPTION: Checking for redundant 'else' branch (all enum values covered).
fun case_1(value: Direction): Int = when(value) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (all enum values and null value covered).
fun case_2(value: Direction?): Int = when(value) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> 5
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 6
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (single enum value covered).
fun case_3(value: Anything): Int = when(value) {
    Anything.EVERYTHING -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (single enum value and null value covered).
fun case_4(value: Anything?): Int = when(value) {
    Anything.EVERYTHING -> 1
    null -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (both boolean value covered).
fun case_5(value: Boolean): Int = when(value) {
    true -> 1
    false -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (both boolean value and null value covered).
fun case_6(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    null -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (all sealed class subtypes covered).
fun case_7(value: Expr): Int = when(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (all sealed class subtypes and null value covered).
fun case_8(value: Expr?): Int = when(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    null -> 4
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (single sealed class subtype covered).
fun case_9(value: Expr2): Int = when(value) {
    is Const2 -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (single sealed class subtype and null value covered).
fun case_10(value: Expr2?): Int = when(value) {
    is Const2 -> 1
    null -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (sealed class itself covered).
fun case_11(value: Expr2): Int = when(value) {
    <!USELESS_IS_CHECK!>is Expr2<!> -> 1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

// CASE DESCRIPTION: Checking for redundant 'else' branch (sealed class itself and null value covered).
fun case_12(value: Expr2?): Int = when(value) {
    is Expr2 -> 1
    null -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}