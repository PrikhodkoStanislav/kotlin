/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 5
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 */

fun withTrueAndFalse(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    null -> 3
}

fun withTrueAndFalseAndElse(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    null -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}

fun withTrueAndFalseAndElseAndWithoutNull(value: Boolean?): Int = when(value) {
    true -> 1
    false -> 2
    else -> 4
}

fun withComplexTrueAndFalse(value: Boolean?): Int = when(value) {
    true && false && ((true || false)) || true && !!!false && !!!true -> 1
    true && false && ((true || false)) || true && !!!false -> 2
    null -> 3
}

sealed class Expr1
data class Const1(val number: Int) : Expr1()
data class Sum1(val e1: Int, val e2: Int) : Expr1()
data class Mul1(val m1: Int, val m2: Int) : Expr1()

sealed class Expr2
class Const2() : Expr2() {
    fun m1(): Int {
        return 1
    }
}
class Sum2() : Expr2() {
    fun m2(): Int {
        return 2
    }
}
class Mul2() : Expr2() {
    fun m3(): Int {
        return 3
    }
}

sealed class Expr3
object Const1O : Expr3()
object Sum1O : Expr3()
object Mul1O : Expr3()

fun withSimpleSealedOnDataClasses(expr: Expr1?): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
    null -> -1
}

fun withItselfSealed(expr: Expr1?): Int = when (expr) {
    is Expr1 -> 10
    null -> -1
}

fun withSimpleSealedOnDataClassesAndElse(expr: Expr1?): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
    null -> -1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}

fun withSimpleSealedOnDataClassesAndElseAndWithoutNull(expr: Expr1?): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
    else -> 0
}

fun withSealed(expr: Expr2?): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
    null -> -1
}

fun withSealedAndElse(expr: Expr2?): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
    null -> -1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}

fun withSealedAndElseAndWithoutNull(expr: Expr2?): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
    else -> 0
}

fun withSimpleSealedExectlyCheck(expr: Expr3?): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
    null -> -1
}

fun withSimpleSealedExectlyCheckAndElse(expr: Expr3?): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
    null -> -1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}

fun withSimpleSealedExectlyCheckAndElseAndWithoutNull(expr: Expr3?): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
    else -> 0
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Anything {
    EVERYTHING
}

fun withEnum(dir: Direction?): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> -1
}

fun withEnumAndElse(dir: Direction?): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    null -> -1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 5
}

fun withEnumAndElseWithoutNull(dir: Direction?): Int = when (dir) {
    Direction.EAST -> 1
    Direction.NORTH -> 2
    Direction.SOUTH -> 3
    Direction.WEST -> 4
    else -> 5
}

fun withSingleEnum(value: Anything?): Int = when (value) {
    Anything.EVERYTHING -> 1
    null -> -1
}

fun withSingleEnumWithElse(value: Anything?): Int = when (value) {
    Anything.EVERYTHING -> 1
    null -> -1
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 2
}

fun withSingleEnumWithElseWithoutNull(value: Anything?): Int = when (value) {
    Anything.EVERYTHING -> 1
    else -> 2
}