/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: Simple when with bound value and type test condition.
 */

fun test1(value: Any): Int {
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

fun test2(value: Any): Int = when (value) {
    is Int -> 1
    is Float -> 2
    is Double -> 3
    is String -> 4
    is Char -> 5
    is Boolean -> 6
    else -> 7
}

fun test3(value: Any): Int = when (value) {
    is Int -> 1
    else -> 7
}

fun test4(value: Any): Int {
    when (value) {
        is Int -> return 1
    }

    return -1
}

fun test5(value: Any): Int = when (value) {
    <!USELESS_IS_CHECK!>is Any<!> -> 1
    else -> 2
}

fun test6(value: Any): Int = when (value) {
    is Nothing -> 1
    else -> 2
}

fun test7(value: Any): Int = when (value) {
    is Unit -> 1
    else -> 2
}