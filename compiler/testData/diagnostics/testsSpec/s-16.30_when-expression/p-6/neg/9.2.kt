/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 2
 DESCRIPTION: Checking for not exhaustive when when covered by all possible subtypes, but no null check (or with no null check, but not covered by all possible subtypes).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()
object A: Expr() {}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class with null-check branch, but all possible subtypes not covered.
fun case_1(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    A -> 3
    null -> 4
}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class without null-check branch.
fun case_2(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    A -> 4
}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class without null-check branch and all possible subtypes not covered.
fun case_3(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class without branches.
fun case_4(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(<!UNUSED_EXPRESSION!>value<!>) {}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class with null-check branch, but object not covered.
fun case_5(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    null -> 4
}

// CASE DESCRIPTION: Checking for not exhaustive in 'when' on the Sealed class without null-check branch and only object covered.
fun case_6(value: Expr?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    A -> 1
}
