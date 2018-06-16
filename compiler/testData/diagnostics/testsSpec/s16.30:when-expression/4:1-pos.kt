/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 */

fun withIntBoundValue(value: Int?): Int {
    when (value) {
        0 -> return 0
        1 -> return 1
        2 -> return 2
        null -> return 3
    }

    return -1
}

fun withBoolBoundValue(value: Boolean?): Int = when (value) {
    true -> 0
    false -> 1
    null -> 2
}

fun withStringBoundValue(value: String?): Int {
    when (value) {
        "" -> return 0
        "1" -> return 1
        null -> return 2
    }

    return -1
}

fun withCharBoundValue(value: Char?): Int {
    when (value) {
        '0' -> return 0
        '1' -> return 1
        null -> return 2
    }

    return -1
}

fun withFloatBoundValue(value: Float?): Int {
    when (value) {
        10.2F -> return 0
        50.0F -> return 1
        44F -> return 2
        null -> return 3
    }

    return -1
}

fun withDoubleBoundValue(value: Double?): Int {
    when (value) {
        10.2 -> return 0
        50.0 -> return 1
        44.1 -> return 2
        null -> return 3
    }

    return -1
}