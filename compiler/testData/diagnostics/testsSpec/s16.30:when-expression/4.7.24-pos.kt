/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 24
 DESCRIPTION: When with bound value and break expression in when entry.
 */

fun test1(value: Any?): Int {
    loop@ while (true) {
        when (value) {
            break@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
        }
    }

    return -1
}

fun test2(value: Any?): Int? {
    var whenValue: Int? = null

    loop@ while (true) {
        <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
            break@loop <!UNREACHABLE_CODE!>-> 1
            else -> 2
        }<!>
    }

    return whenValue
}

fun test3(value: Any?): Int {
    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    when (value) {
                        break@loop1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return 1<!>
                        <!UNREACHABLE_CODE!>break@loop2 -> return 2<!>
                        <!UNREACHABLE_CODE!>break@loop3 -> return 3<!>
                        <!UNREACHABLE_CODE!>break@loop4 -> return 4<!>
                    }
                }
            }
        }
    }

    return -1
}

fun test4(value: Any?): Int? {
    var whenValue: Int? = null

    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
                        break@loop1 <!UNREACHABLE_CODE!>-> 1
                        break@loop2 -> 2
                        break@loop3 -> 3
                        break@loop4 -> 4
                    }<!>
                }
            }
        }
    }

    return whenValue
}