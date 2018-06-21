/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: Simple when without bound value and different variants of the boolean conditions (numbers).
 */

fun foo(value1: Int, value2: Float, value3: Double, value4: Short, value5: Byte, value6: Long): Int {
    when {
        value1 == 21 -> return 1
        value2 > -.000000001 && value2 < 0.000000001 -> return 2
        value3 > 2.0 && value3 <= 1000.90 -> return 3
        value4 == 0.toShort() -> return 4
        value5 > -128 || value5 < 127 -> return 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> return 7
    }

    return -1
}

fun bar(value1: Int, value2: Float, value3: Double, value4: Short, value5: Byte, value6: Long): Int {
    return when {
        value1 == 21 -> 1
        value2 > -.000000001 && value2 < 0.000000001 -> 2
        value3 > 2.0 && value3 <= 1000.90 -> 3
        value4 == 0.toShort() -> 4
        value5 > -128 || value5 < 127 -> 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> 7
        else -> 8
    }
}