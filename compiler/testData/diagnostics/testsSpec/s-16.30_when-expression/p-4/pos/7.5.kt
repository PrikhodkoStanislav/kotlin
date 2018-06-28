/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 5
 DESCRIPTION: When with bound value and concat expressions in when entry.
 */

fun foo(value: String): Int = when (value) {
    "" -> 1
    " " + "1" -> 2
    " $value " + "2" -> 3
    else -> 4
}

fun bar(value: String): Int {
    when (value) {
        "" -> return 1
        " " + "1" -> return 2
        " $value " + "2" -> return 3
    }

    return -1
}