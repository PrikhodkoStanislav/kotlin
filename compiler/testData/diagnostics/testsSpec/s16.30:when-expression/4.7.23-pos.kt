/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 23
 DESCRIPTION: When with bound value and continue expression in when entry.
 */

fun test1(value: Any?): Int {
    loop@ while (true) {
        when (value) {
            continue@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
        }
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun test2(value: Any?): Int? {
    var <!UNUSED_VARIABLE!>whenValue<!>: Int? = null

    loop@ while (true) {
        <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
            continue@loop <!UNREACHABLE_CODE!>-> 1
            else -> 2
        }<!>
    }

    <!UNREACHABLE_CODE!>return whenValue<!>
}

fun test3(value: Any?): Int {
    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    when (value) {
                        continue@loop1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
                        <!UNREACHABLE_CODE!>continue@loop2 -> return 2<!>
                        <!UNREACHABLE_CODE!>continue@loop3 -> return 3<!>
                        <!UNREACHABLE_CODE!>continue@loop4 -> return 4<!>
                    }
                }
            }
        }
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun test4(value: Any?): Int? {
    var <!UNUSED_VARIABLE!>whenValue<!>: Int? = null

    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
                        continue@loop1 <!UNREACHABLE_CODE!>-> 1
                        continue@loop2 -> 2
                        continue@loop3 -> 3
                        continue@loop4 -> 4
                    }<!>
                }
            }
        }
    }

    <!UNREACHABLE_CODE!>return whenValue<!>
}