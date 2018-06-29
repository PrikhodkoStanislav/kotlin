/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 3
 DESCRIPTION: 'When' with bound value and type test condition (with invert type checking operator).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_1(value: Expr): Int = when (value) {
    is Const -> 1
    !is Mul -> 2
    <!USELESS_IS_CHECK!>is Mul<!> -> 3
}

// CASE DESCRIPTION: 'When' with three invert type checking operators on the all sybtypes of the sealed class.
fun case_2(value: Expr): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        !is Const -> return 1
        !is Sum -> return 2
        !is Mul -> return 3
    }<!>

    <!UNREACHABLE_CODE!>return -1<!>
}

// CASE DESCRIPTION: 'When' with direct and invert type checking operators on the same subtype of thee sealed class.
fun case_3(value: Expr): Int = when (value) {
    is Sum -> 1
    !is Sum -> 2
}

// CASE DESCRIPTION: 'When' as statement with direct and invert type checking operators on the same subtype of thee sealed class, and 'else' branch.
fun case_4(value: Expr): Int {
    when (value) {
        is Sum -> return 1
        !is Sum -> return 2
        else -> return 3
    }
}

// CASE DESCRIPTION: 'When' as expression with direct (in the first position) and invert (in the second position) type checking operators on the same subtype of the sealed class, and 'else' branch.
fun case_5(value: Expr): Int = when (value) {
    is Sum -> 1
    !is Sum -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

// CASE DESCRIPTION: 'When' with direct (in the second position) and invert (in the first position) type checking operators on the same subtype of the sealed class, and 'else' branch (redundant).
fun case_6(value: Expr): Int = when (value) {
    !is Const -> 1
    <!USELESS_IS_CHECK!>is Const<!> -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

// CASE DESCRIPTION: 'When' as expression with direct (in the second position) and invert (in the first position) type checking operators on the same subtype of the sealed class.
fun case_7(value: Expr): Int = when (value) {
    !is Const -> 1
    <!USELESS_IS_CHECK!>is Const<!> -> 2
}

// CASE DESCRIPTION: 'When' as statement with direct (in the second position) and invert (in the first position) type checking operators on the same subtype of the sealed class.
fun case_8(value: Expr): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        !is Const -> return 1
        <!USELESS_IS_CHECK!>is Const<!> -> return 2
    }<!>
}

// CASE DESCRIPTION: 'When' with one invert type checking operator on the some subtype of the sealed class, and 'else' branch.
fun case_9(value: Expr): Int = when (value) {
    !is Const -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with one direct type checking operator on the some subtype of the sealed class, and 'else' branch.
fun case_10(value: Expr): Int = when (value) {
    is Const -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with three basic types (Int, Boolean, String) covered and all types other than specified covered via invert type checking operator, and 'else' branch.
fun case_11(value: Any): Int = when (value) {
    is Int -> 1
    is Boolean -> 2
    !is String -> 3
    <!USELESS_IS_CHECK!>is String<!> -> 4
    else -> 5
}

// CASE DESCRIPTION: 'When' with direct and invert type checking operators on the basic type (String).
fun case_12(value: Any): Int = when (value) {
    is String -> 1
    !is String -> 2
    else -> 3
}