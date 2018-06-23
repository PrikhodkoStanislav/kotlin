/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 22
 DESCRIPTION: When with bound value and this expression in when entry.
 */

fun test1(value: Any?): Int {
    when (value) {
        return 1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 2<!>
        <!UNREACHABLE_CODE!>else -> 2<!>
    }
}

fun test2(value: Any?): Int {
    when (value) {
        return 1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 2<!>
    }
}

fun test3(value: Any?): Int {
    when (value) {
        <!UNREACHABLE_CODE!>return return return return<!> return 1 -> <!UNREACHABLE_CODE!>return 2<!>
        <!UNREACHABLE_CODE!>return (return (return (return (return 2)))) -> return 2<!>
        <!UNREACHABLE_CODE!>return 3 -> return 2<!>
        <!UNREACHABLE_CODE!>return 4 -> return 2<!>
        <!UNREACHABLE_CODE!>else -> 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun test4(value: Any?): Int {
    when (value) {
        <!UNREACHABLE_CODE!>return return return return<!> return 1 -> <!UNREACHABLE_CODE!>return 2<!>
        <!UNREACHABLE_CODE!>return (return (return (return (return 2)))) -> return 2<!>
        <!UNREACHABLE_CODE!>return 3 -> return 2<!>
        <!UNREACHABLE_CODE!>return 4 -> return 2<!>
    }
}
