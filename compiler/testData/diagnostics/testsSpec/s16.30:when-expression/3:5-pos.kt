// !DIAGNOSTICS: -SENSELESS_COMPARISON

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 5: The else branch is a special branch that evaluates if none of the branches above it evaluated to true.
 */

/*
 Else branch will not be evaluated, because one of the higher branches is exactly evaluated.
 */
fun withTrueCaseAndElseNotEvaluated(value: Int): Int {
    when {
        value == 2 -> return 1
        value > 2 && value <= 10 -> return 2
        value == 11 -> return 3
        value > 11 -> return 4
        value > -4 || value < -100 && value > -1000 || value == 11 -> return 5
        value != -3 && value != -4 && value != -5 -> return 6
        value > -3 -> return 7
        !!!(false) && "" == null || !!!(null == 0) -> return 8 // must be true
        else -> return 9
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 Else branch will be exactly evaluated, because the other branches will not be evaluated (false invariants).
 */
fun withTrueCaseAndElseEvaluated(): Int {
    when {
        true && 11 != 11 || 11 != 12 && false -> return 1 // must be false
        !!!(false) && "" == null || !!!!(null == 0) -> return 2 // must be false
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
