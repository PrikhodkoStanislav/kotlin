/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 6: Informally speaking, you can always replace the else branch with literal true and the semantics of the entry would not change.
 NUMBER: 2
 DESCRIPTION: Simple when without bound value and with else branch and true literal branch together it.
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
        true -> return 10
        else -> return 11
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
