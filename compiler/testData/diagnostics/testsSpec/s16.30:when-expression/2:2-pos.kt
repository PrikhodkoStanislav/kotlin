/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 2
 SENTENCE 2: The key difference, however, is that when expressions may include several different conditions.
 */

fun calcSomeValue(value: Int): Int {
    return value * 123 % 2 / 10 - 11
}

fun allConditions(value: Any?): Int {
    when (value) {
        null -> return 0
        !is Int -> return 1
        2 -> return 2
        in 1..8 -> return 3
        "" -> return 4
        '0' -> return 5
        Double -> return 6
        6.2F -> return 7
        2342341444212312L -> return 8
        9 * 2 + 1 / 203 % 8 * 111 -> return 9
        true || false && true -> return 11
        calcSomeValue(11) -> return 12
    }

    return -1
}
