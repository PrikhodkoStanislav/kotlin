/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 8
 DESCRIPTION: When with bound value and try expressions in when entry.
 */

fun test1(value: Int?): Int = when (value) {
    try {
        4
    } catch (e: Exception) {
        5
    } -> 1
    try {
        throw Exception()
    } catch (e: Exception) {
        6
    } finally {
        <!UNUSED_EXPRESSION!>7<!>
    } -> 2
    else -> 4
}

fun test2(value: Int?): Int {
    when (value) {
        try {
            4
        } catch (e: Exception) {
            5
        } -> return 1
        try {
            throw Exception()
        } catch (e: Exception) {
            6
        } finally {
            <!UNUSED_EXPRESSION!>7<!>
        } -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}