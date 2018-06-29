/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 4
 DESCRIPTION: 'When' with bound value and type test condition with type aliases.
 */

typealias AnyCustom = Any
typealias UnitCustom = Unit
typealias NothingCustom = Nothing
typealias IntCustom = Int

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_1(value: Any): Int {
    when (value) {
        is IntCustom -> return 2
        <!USELESS_IS_CHECK!>is AnyCustom<!> -> return 3
    }

    return -1
}

// CASE DESCRIPTION: 'When' with type checking operator on the one typealias and 'else' branch.
fun case_2(value: Any): Int = when (value) {
    is IntCustom -> 2
    else -> 4
}

// CASE DESCRIPTION: 'When' with type checking operator on the one typealias which is equal to the source type, and 'else' branch.
fun case_3(value: Any): Int = when (value) {
    <!USELESS_IS_CHECK!>is AnyCustom<!> -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with type checking operator on the one typealias which is not equal to the source type, and 'else' branch.
fun case_4(value: Any): Int = when (value) {
    is UnitCustom -> 1
    else -> 2
}

// CASE DESCRIPTION: 'When' with type checking operator on the Nothing typealias, and 'else' branch.
fun case_5(value: Any): Int = when (value) {
    is NothingCustom -> 1
    else -> 2
}
