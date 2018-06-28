/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 1
 DESCRIPTION: When with bound value and arithmetic expressions in when entry.
 */

fun getShort(number: Int): Short {
    return (number + 11).toShort()
}

fun getInt(number: Int): Int {
    return number + 11
}

fun getLong(number: Int): Long {
    return (number + 11).toLong()
}

fun getFloat(number: Int): Float {
    return (number + 11).toFloat()
}

fun getDouble(number: Int): Double {
    return (number + 11).toDouble()
}

fun getByte(number: Int): Byte {
    return (number + 11).toByte()
}

fun getChar(number: Int): Char {
    return (number + 11).toChar()
}

class A {
    fun getShort(number: Int): Short {
        return (number + 11).toShort()
    }

    fun getInt(number: Int): Int {
        return number + 11
    }

    fun getLong(number: Int): Long {
        return (number + 11).toLong()
    }

    fun getFloat(number: Int): Float {
        return (number + 11).toFloat()
    }

    fun getDouble(number: Int): Double {
        return (number + 11).toDouble()
    }

    fun getByte(number: Int): Byte {
        return (number + 11).toByte()
    }

    fun getChar(number: Int): Char {
        return (number + 11).toChar()
    }
}

fun int1(value: Int, value1: Int, value2: A): Int {
    val value3 = 912

    when (value) {
        2 -> return 1
        2 + 2 -> return 2
        2 * 3 -> return 3
        8 / 1 -> return 4
        8 % 5 -> return 5
        4 - 3 -> return 6
        2 + value3 * 2 / 2 % 2 - 2 -> return 7
        32 shl value3 -> return 8
        value1 shr value2.getInt(1000) -> return 9
        64 ushr getInt(1000) -> return 10
        value2.getInt(1000) and 4 -> return 11
        16 or 5 -> return 12
        value1 xor 55 -> return 13
        55.inv() -> return 14
        Int.MIN_VALUE.inv() -> return 15
        Int.MAX_VALUE.hashCode().inv() -> return 16
        value1 * value3 -> return 17
        value1 * 2 / 10 or 5 + 14 / getInt(1000) % 4 ushr value2.getInt(1000) -> return 18
    }

    return -1
}

fun int2(value: Int, value1: Int, value2: A): Int {
    val value3 = 912

    return when (value) {
        2 -> 1
        2 + 2 -> 2
        2 * 3 -> 3
        8 / 1 -> 4
        8 % 5 -> 5
        4 - 3 -> 6
        2 + value3 * 2 / 2 % 2 - 2 -> 7
        32 shl value3 -> 8
        value1 shr value2.getInt(1000) -> 9
        64 ushr getInt(1000) -> 10
        value2.getInt(1000) and 4 -> 11
        16 or 5 -> 12
        value1 xor 55 -> 13
        55.inv() -> 14
        Int.MIN_VALUE.inv() -> 15
        Int.MAX_VALUE.hashCode().inv() -> 16
        value1 * value3 -> 17
        value1 * 2 / 10 or 5 + 14 / getInt(1000) % 4 ushr value2.getInt(1000) -> 18
        else -> 19
    }
}

fun float1(value: Float, value1: Float, value2: A): Int {
    val value3 = 912.113f

    when (value) {
        2f -> return 1
        2.1f + 2.9F -> return 2
        94.1243235235f * .9193f -> return 3
        -.000001f / 3F -> return 4
        8F % 3.1f -> return 5
        4.0F - 0.2f -> return 6
        2.111111f + value3 * 11f / 0.113F % 0.1F - 2.0f -> return 7
        value1 * getFloat(-100) -> return 8
        value1 * 2 / 14 / getFloat(1000) % 4 - value2.getFloat(1000) -> return 9
    }
 
    return -1
}

fun float2(value: Float, value1: Float, value2: A): Int {
    val value3 = 912.113f

    return when (value) {
        2f -> 1
        2.1f + 2.9F -> 2
        94.1243235235f * .9193f -> 3
        -.000001f / 3F -> 4
        8F % 3.1f -> 5
        4.0F - 0.2f -> 6
        2.111111f + value3 * 11f / 0.113F % 0.1F - 2.0f -> 7
        value1 * getFloat(-100) -> 8
        value1 * 2 / 14 / getFloat(1000) % 4 - value2.getFloat(1000) -> 9
        else -> 10
    }
}

fun double1(value: Double, value1: Double, value2: A): Int {
    val value3 = 912.113

    when (value) {
        2.4 -> return 1
        2.1 + 2.9 -> return 2
        94.1243235235 * .9193 -> return 3
        -.000001 / 3.0 -> return 4
        8.0 % 3.0 -> return 5
        4.0 - 0.2 -> return 6
        2.111111 + value3 * 11.1 / 0.113 % 0.1 - 2.0 -> return 7
        value1 * getDouble(-100) -> return 8
        value1 * 2.4 / 14.0 / getDouble(1000) % 4.0 * value2.getDouble(1000) -> return 9
    }

    return -1
}

fun double2(value: Double, value1: Double, value2: A): Int {
    val value3 = 912.113

    return when (value) {
        2.4 -> 1
        2.1 + 2.9 -> 2
        94.1243235235 * .9193 -> 3
        -.000001 / 3.0 -> 4
        8.0 % 3.0 -> 5
        4.0 - 0.2 -> 6
        2.111111 + value3 * 11.1 / 0.113 % 0.1 - 2.0 -> 7
        value1 * getDouble(-100) -> 8
        value1 * 2.4 / 14.0 / getDouble(1000) % 4.0 * value2.getDouble(1000) -> 9
        else -> 10
    }
}

fun short1(value: Short, value1: Short, value2: A): Int {
    val value3 = 912.toShort()

    when (value) {
        2.toShort() -> return 1
        (2.toShort() + 2.toShort()).toShort() -> return 2
        (2.toShort() * 6.toShort()).toShort() -> return 3
        (8.toShort() / 5.toShort()).toShort() -> return 4
        (8.toShort() % 5.toShort()).toShort() -> return 5
        (9.toShort() - 1.toShort()).toShort() -> return 6
        (2.toShort() + value3 * 2 / 2 % 2 - 2).toShort() -> return 7
        Int.MIN_VALUE.inv().toShort() -> return 15
        Int.MAX_VALUE.hashCode().inv().toShort() -> return 16
        (value1 * value3).toShort() -> return 17
        (value1 * 2.toShort() / 10.toShort() + 5.toShort() + 14.toShort() / getShort(1000) % 4.toShort() * value2.getShort(1000)).toShort() -> return 18
    }

    return -1
}

fun short2(value: Short, value1: Short, value2: A): Int {
    val value3 = 912.toShort()

    return when (value) {
        2.toShort() -> 1
        (2.toShort() + 2.toShort()).toShort() -> 2
        (2.toShort() * 6.toShort()).toShort() -> 3
        (8.toShort() / 5.toShort()).toShort() -> 4
        (8.toShort() % 5.toShort()).toShort() -> 5
        (9.toShort() - 1.toShort()).toShort() -> 6
        (2.toShort() + value3 * 2 / 2 % 2 - 2).toShort() -> 7
        Int.MIN_VALUE.inv().toShort() -> 15
        Int.MAX_VALUE.hashCode().inv().toShort() -> 16
        (value1 * value3).toShort() -> 17
        (value1 * 2.toShort() / 10.toShort() + 5.toShort() + 14.toShort() / getShort(1000) % 4.toShort() * value2.getShort(1000)).toShort() -> 18
        else -> 19
    }
}

fun byte1(value: Byte, value1: Byte, value2: A): Int {
    val value3 = 912.toByte()

    when (value) {
        2.toByte() -> return 1
        (2.toByte() + 2.toByte()).toByte() -> return 2
        (2.toByte() * 6.toByte()).toByte() -> return 3
        (8.toByte() / 5.toByte()).toByte() -> return 4
        (8.toByte() % 5.toByte()).toByte() -> return 5
        (9.toByte() - 1.toByte()).toByte() -> return 6
        (2.toByte() + value3 * 2 / 2 % 2 - 2).toByte() -> return 7
        Int.MIN_VALUE.inv().toByte() -> return 15
        Int.MAX_VALUE.hashCode().inv().toByte() -> return 16
        (value1 * value3).toByte() -> return 17
        (value1 * 2.toByte() / 10.toByte() - 5.toByte() + 14.toByte() / getByte(1000) % 4.toByte() * value2.getByte(1000)).toByte() -> return 18
    }

    return -1
}

fun byte2(value: Byte, value1: Byte, value2: A): Int {
    val value3 = 912.toByte()

    return when (value) {
        2.toByte() -> 1
        (2.toByte() + 2.toByte()).toByte() -> 2
        (2.toByte() * 6.toByte()).toByte() -> 3
        (8.toByte() / 5.toByte()).toByte() -> 4
        (8.toByte() % 5.toByte()).toByte() -> 5
        (9.toByte() - 1.toByte()).toByte() -> 6
        (2.toByte() + value3 * 2 / 2 % 2 - 2).toByte() -> 7
        Int.MIN_VALUE.inv().toByte() -> 15
        Int.MAX_VALUE.hashCode().inv().toByte() -> 16
        (value1 * value3).toByte() -> 17
        (value1 * 2.toByte() / 10.toByte() - 5.toByte() + 14.toByte() / getByte(1000) % 4.toByte() * value2.getByte(1000)).toByte() -> 18
        else -> 19
    }
}

fun char1(value: Char): Int {
    when (value) {
        2.toChar() -> return 1
        2.toChar() + 2 -> return 2
        8.toChar() - 2 -> return 6
        Int.MIN_VALUE.toChar() -> return 15
        Int.MAX_VALUE.hashCode().toChar() -> return 16
    }

    return -1
}

fun char2(value: Char): Int = when (value) {
    2.toChar() -> 1
    2.toChar() + 23 -> 2
    8.toChar() - 12 -> 6
    Int.MIN_VALUE.toChar() -> 15
    Int.MAX_VALUE.hashCode().toChar() -> 16
    else -> 19
}

fun long1(value: Long, value1: Long, value2: A): Int {
    val value3: Long = 34939942345L

    when (value) {
        11345342345L -> return 1
        2L + 2L -> return 2
        212452342345L * 2L -> return 3
        8L / 3L -> return 4
        8L % 5L -> return 5
        5323452342345L - 2L -> return 6
        159345342345L + value3 * 2L / 65939942345L % 2L - 85939942345L -> return 7
        32L shl value3.toInt() -> return 8
        value1 shr value2.getLong(1000).toInt() -> return 9
        64L ushr getLong(1000).toInt() -> return 10
        value2.getLong(1000) and 4 -> return 11
        33244523442345L or 5L -> return 12
        value1 xor 932452342345L -> return 13
        Int.MIN_VALUE.toLong() -> return 15
        Int.MAX_VALUE.hashCode().toLong() -> return 16
        value1 * value3 -> return 17
        value1 * 2L / 10L or 85939942345L + 14L / getLong(1000) % 4L ushr value2.getLong(1000).toInt() -> return 18
    }

    return -1
}

fun long2(value: Long, value1: Long, value2: A): Int {
    val value3: Long = 34939942345L

    return when (value) {
        11345342345L -> 1
        2L + 2L -> 2
        212452342345L * 2L -> 3
        8L / 3L -> 4
        8L % 5L -> 5
        5323452342345L - 2L -> 6
        159345342345L + value3 * 2L / 65939942345L % 2L - 85939942345L -> 7
        32L shl value3.toInt() -> 8
        value1 shr value2.getLong(1000).toInt() -> 9
        64L ushr getLong(1000).toInt() -> 10
        value2.getLong(1000) and 4 -> 11
        33244523442345L or 5L -> 12
        value1 xor 932452342345L -> 13
        Int.MIN_VALUE.toLong() -> 15
        Int.MAX_VALUE.hashCode().toLong() -> 16
        value1 * value3 -> 17
        value1 * 2L / 10L or 85939942345L + 14L / getLong(1000) % 4L ushr value2.getLong(1000).toInt() -> 18
        else -> 19
    }
}