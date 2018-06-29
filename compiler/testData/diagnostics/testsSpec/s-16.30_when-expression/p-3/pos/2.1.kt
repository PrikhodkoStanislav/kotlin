/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: 'When' without bound value and different variants of the boolean conditions (numbers and Char).
 */

fun getNumber(char: Char): Int {
    return char.toInt()
}

fun isInt(value: Any): Boolean {
    return value is Int
}

class A {
    fun method_1(number: Float): Int {
        return number.toInt()
    }
    fun method_2(value: Any): Boolean {
        return value is Int
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch.
fun case_1(
    value1: Int,
    value2: Float,
    value3: Double,
    value4: Short,
    value5: Byte,
    value6: Long,
    value7: A,
    value8: Char
): Int {
    when {
        value1 == 21 -> return 1
        value2 > -.000000001 && value2 < 0.000000001 -> return 2
        value3 > 2.0 && value3 <= 1000.90 -> return 3
        value4 == 0.toShort() -> return 4
        value5 > -128 || value5 < 127 -> return 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> return 7
        getNumber('a') > 100 || getNumber('+') < 10 -> return 8
        value7.method_1(-.00000001f) <= 100 || value7.method_1(4412.11F) >= 10 -> return 9
        isInt(0) || isInt(0L) -> return 10
        value7.method_2(0) && value7.method_2(0L) -> return 11
        11 == 11 || 13123123123123L == 0L || 0f == 0f && !!!(-.0000000001 == -.0000000001) || ((-10).toByte() == 90.toByte()) || 91.toChar() == 127.toChar() -> return 12
        value8 == 127.toChar() -> return 13
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch.
fun case_2(
    value1: Int,
    value2: Float,
    value3: Double,
    value4: Short,
    value5: Byte,
    value6: Long,
    value7: A,
    value8: Char
): Int {
    return when {
        value1 == 21 -> 1
        value2 > -.000000001 && value2 < 0.000000001 -> 2
        value3 > 2.0 && value3 <= 1000.90 -> 3
        value4 == 0.toShort() -> 4
        value5 > -128 || value5 < 127 -> 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> 7
        getNumber('a') > 100 || getNumber('+') < 10 -> return 8
        value7.method_1(-.00000001f) <= 100 || value7.method_1(4412.11F) >= 10 -> return 9
        isInt(0) || isInt(0L) -> return 10
        value7.method_2(0) && value7.method_2(0L) -> return 11
        11 == 11 || 13123123123123L == 0L || 0f == 0f && !!!(-.0000000001 == -.0000000001) || ((-10).toByte() == 90.toByte()) || 91.toChar() == 127.toChar() -> return 12
        value8 == 127.toChar() -> return 13
        else -> 14
    }
}

// CASE DESCRIPTION: 'When' with only one 'else' branch.
fun case_3(): Int = when {
    else -> 1
}