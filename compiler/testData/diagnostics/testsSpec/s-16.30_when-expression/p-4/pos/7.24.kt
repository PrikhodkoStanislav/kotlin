/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 24
 DESCRIPTION: 'When' with bound value and break expression in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and only one break expression.
fun case_1(value: Any?): String {
    loop@ while (true) {
        when (value) {
            break@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
        }
    }

    return ""
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and only one break expression.
fun case_2(value: Any?): String? {
    var whenValue: String? = null

    loop@ while (true) {
        <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
            break@loop <!UNREACHABLE_CODE!>-> ""
            else -> ""
        }<!>
    }

    return whenValue
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and several break expressions.
fun case_3(value: Any?): String {
    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    when (value) {
                        break@loop1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
                        <!UNREACHABLE_CODE!>break@loop2 -> return ""<!>
                        <!UNREACHABLE_CODE!>break@loop3 -> return ""<!>
                        <!UNREACHABLE_CODE!>break@loop4 -> return ""<!>
                    }
                }
            }
        }
    }

    return ""
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and several break expressions.
fun case_4(value: Any?): String? {
    var whenValue: String? = null

    loop1@ while (true) {
        loop2@ while (true) {
            loop3@ while (true) {
                loop4@ while (true) {
                    <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
                        break@loop1 <!UNREACHABLE_CODE!>-> ""
                        break@loop2 -> ""
                        break@loop3 -> ""
                        break@loop4 -> ""
                    }<!>
                }
            }
        }
    }

    return whenValue
}