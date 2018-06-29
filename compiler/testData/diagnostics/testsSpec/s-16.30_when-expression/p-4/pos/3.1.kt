/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and type test condition.
 */

// CASE DESCRIPTION: 'When' with type test condition on the various basic types.
fun case_1(value: Any): Int {
    when (value) {
        is Int -> return 1
        is Float -> return 2
        is Double -> return 3
        is String -> return 4
        is Char -> return 5
        is Boolean -> return 6
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various basic types.
fun case_2(value: Any): Int = when (value) {
    is Int -> 1
    is Float -> 2
    is Double -> 3
    is String -> 4
    is Char -> 5
    is Boolean -> 6
    else -> 7
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the one basic types (Int).
fun case_3(value: Any): Int = when (value) {
    is Int -> 1
    else -> 7
}

// CASE DESCRIPTION: 'When' with type test condition on the one basic types (Int).
fun case_4(value: Any): Int {
    when (value) {
        is Int -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on Any.
fun case_5(value: Any): Int = when (value) {
    <!USELESS_IS_CHECK!>is Any<!> -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on Nothing.
fun case_6(value: Any): Int = when (value) {
    is Nothing -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on Unit.
fun case_7(value: Any): Int = when (value) {
    is Unit -> 1
    else -> 2
}