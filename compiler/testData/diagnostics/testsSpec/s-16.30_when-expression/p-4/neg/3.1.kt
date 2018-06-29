/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and type test condition (without companion object in class), but without type checking operator.
 */

class A {}

// CASE DESCRIPTION: 'When' with custom class type test condition.
fun case_1(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>A<!> -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with Any type test condition.
fun case_2(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>Any<!> -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with Nothing type test condition.
fun case_3(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>Nothing<!> -> return 1
    }

    return -1
}
