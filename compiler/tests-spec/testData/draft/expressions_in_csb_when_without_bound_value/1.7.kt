// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 7
 DESCRIPTION: 'When' with if expressions in the control structure body.
 */

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

sealed class Expr
data class _SealedChild1(val number: Int) : Expr()
data class _SealedChild2(val e1: Int, val e2: Int) : Expr()
data class _SealedChild3(val m1: Int, val m2: Int) : Expr()

fun case_1(value_1: Int, value_1: Int, value_2: Boolean?, value_3: Direction?, value_4: Expr?) {
    when {
        value_1 == 1 -> if (value_1 > 1000) "1"
            else if (value_1 > 100) "2"
            else if (value_1 > 10 || value_1 < -10) "3"
            else "4"
        value_1 == 2 -> if (!value_2!!) "1"
            else if (<!DEBUG_INFO_SMARTCAST!>value_2<!>) "2"
        value_1 == 3 -> if (value_2 == null) "1"
            else if (value_2 == true) "2"
            else if (value_2 == false) "3"
        value_1 == 4 -> if (value_3!! == Direction.WEST) "1"
            else if (value_3 == Direction.SOUTH) "2"
            else if (value_3 == Direction.NORTH) "3"
            else if (value_3 == Direction.EAST) "4"
        value_1 == 5 -> if (value_3 == null) "1"
            else if (value_3 == Direction.WEST) "2"
            else if (value_3 == Direction.SOUTH) "3"
            else if (value_3 == Direction.NORTH) "4"
            else if (value_3 == Direction.EAST) "5"
        value_1 == 6 -> if (value_4 is _SealedChild1) "1"
            else if (value_4 is _SealedChild2) "2"
            else if (value_4 is _SealedChild3) "3"
        value_1 == 7 -> {
            if (value_4 == null) {
                "1"
            } else if (value_4 is _SealedChild1) {
                "2"
            } else if (value_4 is _SealedChild2) {
                "3"
            } else if (value_4 is _SealedChild3) {
                "4"
            }
        }
    }
}