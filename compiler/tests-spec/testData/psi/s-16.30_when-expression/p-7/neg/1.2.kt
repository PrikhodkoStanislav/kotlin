/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [1] Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and 'when condition' with type checking operator and non-type value.
 */

// CASE DESCRIPTION: 'When' with variables and return value as type checking operator value.
fun case_2(value: Any, value1: String, value2: Any?): String {
    when (value) {
        is value1 -> return ""
        is value2 -> return ""
        is value1.isEmpty() -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with literals as type checking operator value.
fun case_3(value: Any): String {
    when (value) {
        is {} -> return ""
        is fun() {} -> return ""
        is 90 -> return ""
        is -.032 -> return ""
        is "..." -> return ""
        is '.' -> return ""
        is return 1 -> return ""
        is throw Exception() -> return ""
    }

    return ""
}
