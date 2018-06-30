/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 23
 DESCRIPTION: 'When' with bound value and continue expression in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and only one continue expression.
fun case_1(value: Any?): String {
    loop@ while (true) {
        when (value) {
            continue@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
        }
    }

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and only one continue expression.
fun case_2(value: Any?): String? {
    var <!UNUSED_VARIABLE!>whenValue<!>: String? = null

    loop@ while (true) {
        <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
            continue@loop <!UNREACHABLE_CODE!>-> ""
            else -> ""
        }<!>
    }

    <!UNREACHABLE_CODE!>return whenValue<!>
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and several continue expressions.
fun case_3(value: Any?): String {
    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    when (value) {
                        continue@loop1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
                        <!UNREACHABLE_CODE!>continue@loop2 -> return ""<!>
                        <!UNREACHABLE_CODE!>continue@loop3 -> return ""<!>
                        <!UNREACHABLE_CODE!>continue@loop4 -> return ""<!>
                    }
                }
            }
        }
    }

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and several continue expressions.
fun case_4(value: Any?): String? {
    var <!UNUSED_VARIABLE!>whenValue<!>: String? = null

    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
                        continue@loop1 <!UNREACHABLE_CODE!>-> ""
                        continue@loop2 -> ""
                        continue@loop3 -> ""
                        continue@loop4 -> ""
                    }<!>
                }
            }
        }
    }

    <!UNREACHABLE_CODE!>return whenValue<!>
}