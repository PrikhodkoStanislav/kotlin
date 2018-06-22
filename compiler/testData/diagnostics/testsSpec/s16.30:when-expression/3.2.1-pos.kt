/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: Simple when without bound value and different variants of the boolean conditions (numbers).
 */

fun getNumber(char: Char): Int {
    return char.toInt()
}

fun isInt(value: Any): Boolean {
    return value is Int
}

class A {
    fun method1(number: Float): Int {
        return number.toInt()
    }
    fun method2(value: Any): Boolean {
        return value is Int
    }
}

fun foo(value1: Int, value2: Float, value3: Double, value4: Short, value5: Byte, value6: Long, value7: A): Int {
    when {
        value1 == 21 -> return 1
        value2 > -.000000001 && value2 < 0.000000001 -> return 2
        value3 > 2.0 && value3 <= 1000.90 -> return 3
        value4 == 0.toShort() -> return 4
        value5 > -128 || value5 < 127 -> return 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> return 7
        getNumber('a') > 100 || getNumber('+') < 10 -> return 8
        value7.method1(-.00000001f) <= 100 || value7.method1(4412.11F) >= 10 -> return 9
        isInt(0) || isInt(0L) -> return 10
        value7.method2(0) && value7.method2(0L) -> return 11
        11 == 11 || 13123123123123L == 0L || 0f == 0f && !!!(-.0000000001 == -.0000000001) || -10.toByte() == 90.toByte() || 91.toChar() == 127.toChar() -> return 12
    }

    return -1
}

fun bar(value1: Int, value2: Float, value3: Double, value4: Short, value5: Byte, value6: Long, value7: A): Int {
    return when {
        value1 == 21 -> 1
        value2 > -.000000001 && value2 < 0.000000001 -> 2
        value3 > 2.0 && value3 <= 1000.90 -> 3
        value4 == 0.toShort() -> 4
        value5 > -128 || value5 < 127 -> 6
        value6 > 213412341234L && value6 <= 1100000000000L || value6 == 0L -> 7
        getNumber('a') > 100 || getNumber('+') < 10 -> return 8
        value7.method1(-.00000001f) <= 100 || value7.method1(4412.11F) >= 10 -> return 9
        isInt(0) || isInt(0L) -> return 10
        value7.method2(0) && value7.method2(0L) -> return 11
        11 == 11 || 13123123123123L == 0L || 0f == 0f && !!!(-.0000000001 == -.0000000001) || -10.toByte() == 90.toByte() || 91.toChar() == 127.toChar() -> return 12
        else -> 13
    }
}