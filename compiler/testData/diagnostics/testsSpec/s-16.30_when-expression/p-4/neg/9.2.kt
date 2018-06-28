/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 9: The else condition, which works the exact same way as it would in the form without bound expression.
 NUMBER: 2
 DESCRIPTION: Simple when with bound value and with else branch not in the last position.
 */

fun test1(value: Int): Int = when (value) {
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 1
    <!UNREACHABLE_CODE!>1 -> 2<!>
}

fun test2(value: Int): Int = when(value) {
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 1
    <!UNREACHABLE_CODE!>1 -> 2<!>
    <!UNREACHABLE_CODE!>2 -> 3<!>
}

fun test3(value: Int): Int = when (value) {
    1 -> 1
    <!ELSE_MISPLACED_IN_WHEN!>else<!> -> 2
    <!UNREACHABLE_CODE!>2 -> 3<!>
}

fun test4(value: Int): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        <!ELSE_MISPLACED_IN_WHEN!>else<!> -> return 1
        <!UNREACHABLE_CODE!>else -> return 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
