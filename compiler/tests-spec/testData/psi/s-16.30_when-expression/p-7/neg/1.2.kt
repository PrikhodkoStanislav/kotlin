/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [1] Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value_1 and 'when condition' with type checking operator and non-type value_1.
 */

// CASE DESCRIPTION: 'When' with variables and return value_1 as type checking operator value_1.
fun case_2(value_1: Any, value_1: String, value_2: Any?): String {
    when (value_1) {
        is value_1 -> return ""
        is value_2 -> return ""
        is value_1.isEmpty() -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with literals as type checking operator value_1.
fun case_3(value_1: Any): String {
    when (value_1) {
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
