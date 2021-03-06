// !DIAGNOSTICS: -UNUSED_EXPRESSION

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 1: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 1
 DESCRIPTION: 'When' with different variants of the arithmetic expressions (additive expression and multiplicative expression) in the control structure body.
 */

fun getInt(number: Int): Int {
    return number + 11
}

fun getShort(number: Int): Short {
    return (number + 11).toShort()
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
    fun getInt(number: Int): Int {
        return number + 11
    }

    fun getShort(number: Int): Short {
        return (number + 11).toShort()
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

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Short.
fun case_1(value: Int, value1: Short, value2: A) {
    val value3: Short = 32767
    val value4: Short = -32768

    when (value) {
        1 -> 900.toShort()
        2 -> value2.getShort(value1.toInt()) - 9234.toShort()
        3 -> 9234.toShort() * 0.toShort()
        4 -> -6.toShort() / getShort(-9000)
        5 -> 6.toShort() % 112.toShort()
        6 -> -9313.toShort() % 10.toShort()
        7 -> 6.toShort() - value4
        8 -> 50.toShort() + value1 * -90.toShort() / value3 % 112.toShort() - value1
        9 -> {
            value1 * -112.toShort() / 9234.toShort() - -1.toShort() + value2.getShort(111) / 0.toShort() % -99.toShort() % 9234.toShort() + value4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Int.
fun case_2(value: Int, value1: Int, value2: A) {
    val value3 = 912
    val value4 = 124901924904

    when (value) {
        1 -> 2
        2 -> 2 + 2
        3 -> 2 * -2
        4 -> getInt(-9) / 3
        5 -> -8 % 3
        6 -> -4 - 2
        7 -> value2.getInt(111) + 2 * getInt(2) / -2 % 2 - 2
        8 -> 32 shl 2
        9 -> value1 shr -value3
        10 -> -64 ushr value3
        11 -> value3 and 4
        12 -> 16 or -5
        13 -> value1 xor 55
        14 -> -55.inv()
        15 -> value1 * -value3
        16 -> {
            value1 * 2 / 10 - 5 + value2.getInt(-500) / 2 % -4 % value4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Long.
fun case_3(value: Int, value1: Long, value2: A) {
    val value3 = 9L
    val value4 = 124909249042341234L

    when (value) {
        1 -> 2L
        2 -> 1249011249042341234L + 412L
        3 -> -2L * getLong(1000)
        4 -> 3241019249042341234L / -2L
        5 -> 324901924942341234L % -2L
        6 -> 4L - value2.getLong(0)
        7 -> 2L + -9L * -10000000000000L / 2L % value2.getLong(-99999999) - 2L
        8 -> value1 * value3
        9 -> {
            value1 * 2L / -10L - 92490149042341234L + 1324019249042341234L / 2L % 234124312423452L % value4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Float.
fun case_4(value: Int, value1: Float, value2: A) {
    val value3 = 912.2134F
    val value4 = -124901924904.991242f

    when (value) {
        1 -> 2.1F
        2 -> 2.1f + value2.getFloat(-1)
        3 -> getFloat(-10) * 2f
        4 -> 8.5f / -2.3f
        5 -> value2.getFloat(1111111) % 2f
        6 -> 4.0F - 2.1f
        7 -> 2f + .9f * -.0000000001F / 2F % 2.91f - -2.09F
        8 -> value1 * -value3
        9 -> {
            value1 * 2F / -10.12414141f - .13104141040f + 0.5F / -2F % 4.0f % value4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Double.
fun case_5(value: Int, value1: Double, value2: A) {
    val value3 = 912.2134
    val value4 = -124901924904.99124212

    when (value) {
        1 -> 2.1
        2 -> 2.1 + 2.5
        3 -> getDouble(-20) * 2.0
        4 -> -8.5 / 2.3
        5 -> 8.0 % value2.getDouble(100000000)
        6 -> 4.0 - -2.1
        7 -> 2.4 + -.9 * -.0000000001 / value2.getDouble(-10) % 2.91 - 2.09
        8 -> -value1 * value3
        9 -> {
            value1 * -2.0 / 10.12414141 - .13104141040 + 0.5 / 2.0 % 4.0 % value4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Byte.
fun case_6(value: Int, value1: Byte, value2: A) {
    val value3: Byte = -11
    val value4: Byte = 3
    val value5: Byte = 127
    val value6: Byte = 5
    val value7: Byte = -128

    when (value) {
        1 -> value1
        2 -> -11.toByte() - value3
        3 -> 90.toByte() * -value5
        4 -> 2.toByte() / 100.toByte()
        5 -> value4 % value5
        6 -> 0.toByte() - 0.toByte()
        7 -> value3 + -128.toByte() * 127.toByte() / getByte(-9999) % value5 - value2.getByte(9999999)
        8 -> {
            value2.getByte(-100) * value5 / -10.toByte() - value6 + value4 / 9.toByte() % value5 % value1 + value7
        }
    }
}