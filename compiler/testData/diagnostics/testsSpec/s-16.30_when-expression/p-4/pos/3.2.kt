/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and type test condition (with sealed class).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

// CASE DESCRIPTION: 'When' with type test condition on the all possible subtypes of the sealed class.
fun case_1(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
}

// CASE DESCRIPTION: 'When' with type test condition on the not all possible subtypes of the sealed class.
fun case_2(value: Expr): Int {
    <!NON_EXHAUSTIVE_WHEN_ON_SEALED_CLASS!>when<!> (value) {
        is Const -> return 1
        is Sum -> return 2
    }

    return -1
}

// CASE DESCRIPTION: 'When' with type test condition on the not all possible subtypes of the sealed class and 'else' branch.
fun case_3(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    else -> 3
}

// CASE DESCRIPTION: 'When' with type test condition on the all possible subtypes of the sealed class and 'else' branch (redundant).
fun case_4(value: Expr): Int = when (value) {
    is Const -> 1
    is Sum -> 2
    is Mul -> 3
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 4
}
