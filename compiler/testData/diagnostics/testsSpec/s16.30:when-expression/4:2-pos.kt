/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 2: In fact, it supports three different condition forms:
 */

fun differentConditions(value: Any?): Int {
    when (value) {
        is String -> return 1
        in 1..8 -> return 2
        6 - 15 * 6 % 10 -> return 3
    }

    return -1
}
