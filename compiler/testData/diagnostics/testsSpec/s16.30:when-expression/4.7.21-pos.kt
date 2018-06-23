/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 21
 DESCRIPTION: When with bound value and throw expression in when entry.
 */

fun test1(value: Any?): Int = when (value) {
    throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
    <!UNREACHABLE_CODE!>else -> 2<!>
}

fun test2(value: Any?): Int {
    when (value) {
        throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun test3(value: Any?): Int = when (value) {
    throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
    <!UNREACHABLE_CODE!>throw Exception("Ex") -> 2<!>
    <!UNREACHABLE_CODE!>else -> 4<!>
}

fun test4(value: Any?): Int {
    when (value) {
        throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
        <!UNREACHABLE_CODE!>throw Exception("Ex") -> return 2<!>
        <!UNREACHABLE_CODE!>else -> 4<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}
