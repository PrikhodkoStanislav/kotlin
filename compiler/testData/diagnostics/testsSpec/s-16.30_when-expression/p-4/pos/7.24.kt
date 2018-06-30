/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 24
 DESCRIPTION: 'When' with bound value and break expression in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and only one break expression.
fun case_1(value: Any?, value1: Stack<Int>): String {
    loop@ while (!value1.empty()) {
        when (value) {
            break@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
        }
        value1.pop()
    }

    return ""
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and only one break expression.
fun case_2(value: Any?, value1: Stack<Int>): String? {
    var whenValue: String? = null

    loop@ while (!value1.empty()) {
        <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
            break@loop <!UNREACHABLE_CODE!>-> ""
            else -> ""
        }<!>
        value1.pop()
    }

    return whenValue
}

// CASE DESCRIPTION: 'When' with 'else' branch (as expression) and several break expressions.
fun case_3(value: Any?, value1: Stack<Int>, value2: Stack<Int>, value3: Stack<Int>, value4: Stack<Int>): String {
    loop1@ while (!value1.empty()) {
        loop2@ while (!value2.empty()) {
            loop3@ while (!value3.empty()) {
                loop4@ while (!value4.empty()) {
                    when (value) {
                        break@loop1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
                        <!UNREACHABLE_CODE!>break@loop2 -> return ""<!>
                        <!UNREACHABLE_CODE!>break@loop3 -> return ""<!>
                        <!UNREACHABLE_CODE!>break@loop4 -> return ""<!>
                    }
                    value4.pop()
                }
                value3.pop()
            }
            value2.pop()
        }
        value1.pop()
    }

    return ""
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement) and several break expressions.
fun case_4(value: Any?, value1: Stack<Int>, value2: Stack<Int>, value3: Stack<Int>, value4: Stack<Int>): String? {
    var whenValue: String? = null

    loop1@ while (!value1.empty()) {
        loop2@ while (!value2.empty()) {
            loop3@ while (!value3.empty()) {
                loop4@ while (!value4.empty()) {
                    <!UNREACHABLE_CODE!>whenValue = when (<!>value<!UNREACHABLE_CODE!>) {<!>
                        break@loop1 <!UNREACHABLE_CODE!>-> ""
                        break@loop2 -> ""
                        break@loop3 -> ""
                        break@loop4 -> ""
                    }<!>
                    value4.pop()
                }
                value3.pop()
            }
            value2.pop()
        }
        value1.pop()
    }

    return whenValue
}