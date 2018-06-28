/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 7
 DESCRIPTION: When with if expressions in the control structure bodies.
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

fun foo(value: Int, value1: Int, value2: Boolean?, value3: Direction?, value4: Expr?) {
    when {
        value == 1 -> if (value1 > 1000) <!UNUSED_EXPRESSION!>"1"<!>
            else if (value1 > 100) <!UNUSED_EXPRESSION!>"2"<!>
            else if (value1 > 10 || value1 < -10) <!UNUSED_EXPRESSION!>"3"<!>
            else <!UNUSED_EXPRESSION!>"4"<!>
        value == 2 -> if (!value2!!) <!UNUSED_EXPRESSION!>"1"<!>
            else if (<!DEBUG_INFO_SMARTCAST!>value2<!>) <!UNUSED_EXPRESSION!>"2"<!>
        value == 3 -> if (value2 == null) <!UNUSED_EXPRESSION!>"1"<!>
            else if (value2 == true) <!UNUSED_EXPRESSION!>"2"<!>
            else if (value2 == false) <!UNUSED_EXPRESSION!>"3"<!>
        value == 4 -> if (value3!! == Direction.WEST) <!UNUSED_EXPRESSION!>"1"<!>
            else if (value3 == Direction.SOUTH) <!UNUSED_EXPRESSION!>"2"<!>
            else if (value3 == Direction.NORTH) <!UNUSED_EXPRESSION!>"3"<!>
            else if (value3 == Direction.EAST) <!UNUSED_EXPRESSION!>"4"<!>
        value == 5 -> if (value3 == null) <!UNUSED_EXPRESSION!>"1"<!>
            else if (value3 == Direction.WEST) <!UNUSED_EXPRESSION!>"2"<!>
            else if (value3 == Direction.SOUTH) <!UNUSED_EXPRESSION!>"3"<!>
            else if (value3 == Direction.NORTH) <!UNUSED_EXPRESSION!>"4"<!>
            else if (value3 == Direction.EAST) <!UNUSED_EXPRESSION!>"5"<!>
        value == 6 -> if (value4 is Const) <!UNUSED_EXPRESSION!>"1"<!>
            else if (value4 is Sum) <!UNUSED_EXPRESSION!>"2"<!>
            else if (value4 is Mul) <!UNUSED_EXPRESSION!>"3"<!>
        value == 7 -> {
            if (value4 == null) {
                <!UNUSED_EXPRESSION!>"1"<!>
            } else if (value4 is Const) {
                <!UNUSED_EXPRESSION!>"2"<!>
            } else if (value4 is Sum) {
                <!UNUSED_EXPRESSION!>"3"<!>
            } else if (value4 is Mul) {
                <!UNUSED_EXPRESSION!>"4"<!>
            }
        }
    }
}