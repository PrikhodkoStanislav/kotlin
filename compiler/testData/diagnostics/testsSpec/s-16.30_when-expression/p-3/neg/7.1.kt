/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 7: The else entry is also special in the sense that it must be the last entry in the expression, otherwise a compiler error must be generated.
 NUMBER: 1
 DESCRIPTION: Simple when without bound value and with else branch not in the last position.
 */

fun test1(<!UNUSED_PARAMETER!>value<!>: Int): Int = when {
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 1
    <!UNREACHABLE_CODE!>value == 1 -> 2<!>
}

fun test2(<!UNUSED_PARAMETER!>value<!>: Int): Int = when {
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 1
    <!UNREACHABLE_CODE!>value == 1 -> 2<!>
    <!UNREACHABLE_CODE!>value == 2 -> 3<!>
}

fun test3(value: Int): Int = when {
    value == 1 -> 1
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 2
    <!UNREACHABLE_CODE!>value == 2 -> 3<!>
}

fun test4(): Int {
    when {
        <!ELSE_MISPLACED_IN_WHEN!>else<!> -> return 1
        <!UNREACHABLE_CODE!>else -> return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
