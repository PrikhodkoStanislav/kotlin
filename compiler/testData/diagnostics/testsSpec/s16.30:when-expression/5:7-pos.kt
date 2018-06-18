/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 5
 SENTENCE 7: The bound expression is of a sealed class type and all its possible subtypes are covered using type test conditions of this expression;
 */

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

fun withSimpleSealedOnDataClasses(expr: Expr1): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
}

fun withItselfSealed(expr: Expr1): Int = when (expr) {
    <!USELESS_IS_CHECK!>is Expr1<!> -> 10
}

fun withSimpleSealedOnDataClassesAndElse(expr: Expr1): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}

fun withSealed(expr: Expr2): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
}

fun withSealedAndElse(expr: Expr2): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}

fun withSimpleSealedExectlyCheck(expr: Expr3): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
}

fun withSimpleSealedExectlyCheckAndElse(expr: Expr3): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 0
}
