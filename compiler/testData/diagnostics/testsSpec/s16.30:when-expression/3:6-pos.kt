// !DIAGNOSTICS: -SENSELESS_COMPARISON

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 6: Informally speaking, you can always replace the else branch with literal true and the semantics of the entry would not change.
 */

fun withElse(value: Int): Int {
    when {
        value == 1 -> return 1
        value == 2 -> return 2
        value > 2 && value <= 10 -> return 3
        value == 11 -> return 4
        value > 11 -> return 5
        value > -4 || value < -100 && value > -1000 || value == 11 -> return 7
        value != -3 && value != -4 && value != -5 -> return 8
        value > -3 -> return 9
        else -> return 10
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun withTrueInsteadElse(value: Int): Int {
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
    }

    return -1 // why dont have 'UNREACHABLE_CODE' diagnostic?
}

/*
 Whens with the 'true' branch at the end and the 'else' branch at the end must be equivalent (similar semantics).
 */
fun compareWhensWithTrueAndElseCases(): Boolean {
    return when {
        90 == 91 -> 1
        false == !!true -> 2
        11 > 11 -> 3
        "" == null -> 4
        else -> 5
    } == when {
        90 == 91 -> 1
        false == !!true -> 2
        11 > 11 -> 3
        "" == null -> 4
        true -> 5
        else -> 6 // whether its necessary? TYPE_MISMATCH without it (not exhaustive when)
    } // must be 5 == 5 => true
}
