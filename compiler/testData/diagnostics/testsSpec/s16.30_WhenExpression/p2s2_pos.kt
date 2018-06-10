/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 2
 SENTENCE 2: The key difference, however, is that when expressions may include several different conditions.
 */

fun whenIntTest(value: Int?): Int {
    when (value) {
        null -> return 0
        1 -> return 1
        2 -> return 2
        3 -> return 3
    }

    return -1
}
