/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 */

fun withSimpleTypeCheckOperator(value: Any): Int = when (value) {
    is Int -> 1
    is Float -> 2
    is Double -> 3
    is String -> 4
    is Char -> 5
    is Boolean -> 6
    !is Unit -> 7
    else -> 8
}

fun withNotTypeCheckOperator(value: Any?): Int = when (value) {
    !is Int -> 1
    null -> 2
    else -> 3
}

fun withOppositeTypeCheckOperators(value: Any?): Int {
    when (value) {
        is Int -> return 1
        !is Int -> return 2
    }

    return -1
}

fun withUselessOppositeTypeCheckOperators(value: Int): Int {
    when (value) {
        <!USELESS_IS_CHECK!>is Int<!> -> return 1
        <!USELESS_IS_CHECK!>!is Int<!> -> return 2
    }

    return -1
}

fun withSingleBranchTypeCheckAndElse(value: Int): Int = when (value) {
    <!USELESS_IS_CHECK!>is Int<!> -> 1
    else -> 2
}

fun withSingleBranch(value: Int): Int {
    when (value) {
        <!USELESS_IS_CHECK!>is Int<!> -> return 1
    }

    return -1
}
