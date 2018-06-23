/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 10
 DESCRIPTION: When with bound value and range expressions in when entry.
 */

fun test1(value: Any?, value1: Int, value2: Long): Int = when (value) {
    -100..-1000 -> 1
    0..0-> 2
    -100..9 -> 3
    10..100 -> 4
    101..value1 -> 5
    value1..value2 -> 6
    value2..102301239123L -> 7
    null -> 8
    else -> 9
}

fun test2(value: Any?, value1: Int, value2: Long): Int {
    when (value) {
        -100..-1000 -> return 1
        0..0-> return 2
        -100..9 -> return 3
        10..100 -> return 4
        101..value1 -> return 5
        value1..value2 -> return 6
        value2..102301239123L -> return 7
        null -> return 8
    }

    return -1
}
