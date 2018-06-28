/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 6
 DESCRIPTION: When with bound value and when expressions in when entry.
 */

fun test1(value: String?, value1: Int, value2: Boolean): Int = when (value) {
    when {
        value1 > 10 -> "1"
        else -> "2"
    } -> 1
    null -> 2
    when (value2) {
        true -> "10"
        false -> "100"
    } -> 3
    else -> 4
}

fun test2(value: String?, value1: Int, value2: Boolean): Int {
    when (value) {
        when {
            value1 > 10 -> "1"
            else -> "2"
        } -> return 1
        null -> return 2
        when (value2) {
            true -> "10"
            false -> "100"
        } -> return 3
    }

    return -1
}