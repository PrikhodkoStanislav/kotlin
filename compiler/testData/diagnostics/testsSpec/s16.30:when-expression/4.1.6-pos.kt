/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 6
 DESCRIPTION: When with when expression (exhaustive) in the control structure bodies.
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

fun foo(value: Int, value1: Int, value2: Boolean?, value3: Direction?, value4: Expr?) {
    when(value) {
        1 -> when {
            value1 > 1000 -> <!UNUSED_EXPRESSION!>"1"<!>
            value1 > 100 -> <!UNUSED_EXPRESSION!>"2"<!>
            value1 > 10 || value1 < -10 -> <!UNUSED_EXPRESSION!>"3"<!>
            else -> <!UNUSED_EXPRESSION!>"4"<!>
        }
        2 -> when(value2!!) {
            true -> <!UNUSED_EXPRESSION!>"1"<!>
            false -> <!UNUSED_EXPRESSION!>"2"<!>
        }
        3 -> when(value2) {
            true -> <!UNUSED_EXPRESSION!>"1"<!>
            false -> <!UNUSED_EXPRESSION!>"2"<!>
            null -> <!UNUSED_EXPRESSION!>"3"<!>
        }
        4 -> when(value3!!) {
            Direction.WEST -> <!UNUSED_EXPRESSION!>"1"<!>
            Direction.SOUTH -> <!UNUSED_EXPRESSION!>"2"<!>
            Direction.NORTH -> <!UNUSED_EXPRESSION!>"3"<!>
            Direction.EAST -> <!UNUSED_EXPRESSION!>"4"<!>
        }
        5 -> when(value3) {
            Direction.WEST -> <!UNUSED_EXPRESSION!>"1"<!>
            Direction.SOUTH -> <!UNUSED_EXPRESSION!>"2"<!>
            Direction.NORTH -> <!UNUSED_EXPRESSION!>"3"<!>
            Direction.EAST -> <!UNUSED_EXPRESSION!>"4"<!>
            null -> <!UNUSED_EXPRESSION!>"5"<!>
        }
        6 -> when(value4!!) {
            is Const -> <!UNUSED_EXPRESSION!>"1"<!>
            is Sum -> <!UNUSED_EXPRESSION!>"2"<!>
            is Mul -> <!UNUSED_EXPRESSION!>"3"<!>
        }
        7 -> {
            when(value4) {
                is Const -> <!UNUSED_EXPRESSION!>"1"<!>
                is Sum -> <!UNUSED_EXPRESSION!>"2"<!>
                is Mul -> <!UNUSED_EXPRESSION!>"3"<!>
                null -> <!UNUSED_EXPRESSION!>"4"<!>
            }
        }
    }
}