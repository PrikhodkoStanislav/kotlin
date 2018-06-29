/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 3
 DESCRIPTION: Check when exhaustive when possible subtypes of the sealed class are covered and contains a null check.
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

// CASE DESCRIPTION: Checking for exhaustive in 'when' (all sealed class subtypes and null value covered).
fun case_1(expr: Expr1?): Int = when (expr) {
    is Const1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.number
    is Sum1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.e1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.e2
    is Mul1 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1 + <!DEBUG_INFO_SMARTCAST!>expr<!>.m2
    null -> 1
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (sealed class itself and null value covered).
fun case_2(expr: Expr1?): Int = when (expr) {
    is Expr1 -> 1
    null -> 2
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (all sealed class with methods subtypes and null value covered).
fun case_3(expr: Expr2?): Int = when (expr) {
    is Const2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m1()
    is Sum2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m2()
    is Mul2 -> <!DEBUG_INFO_SMARTCAST!>expr<!>.m3()
    null -> 1
}

// CASE DESCRIPTION: Checking for exhaustive in 'when' (all objects covered using implicit equality operator and null value covered).
fun case_4(expr: Expr3?): Int = when (expr) {
    Const1O -> 1
    Sum1O -> 2
    Mul1O -> 3
    null -> 4
}
