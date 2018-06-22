/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 7: The else entry is also special in the sense that it must be the last entry in the expression, otherwise a compiler error must be generated.
 NUMBER: 1
 DESCRIPTION: Simple when without bound value and with else branch in the last position.
 */

fun foo(value: Int): Int {
    when {
        value == 1 -> return 1
        value == 2 -> return 2
        value > 2 && value <= 10 -> return 3
        value == 11 -> return 4
        value > 11 -> return 5
        value > -4 || value < -100 && value > -1000 || value == 11 -> return 7
        value != -3 && value != -4 && value != -5 -> return 8
        value > -3 -> return 9
        else -> return 11
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
