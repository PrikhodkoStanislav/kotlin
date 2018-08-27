// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 1
 DESCRIPTION: 'When' with different variants of the arithmetic expressions (additive expression and multiplicative expression) in the control structure body.
 */

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Short.
fun case_1(value_1: Int, value_1: Short, value_2: _BasicTypesProvider) {
    val value_3: Short = 32767
    val value_4: Short = -32768

    when {
        value_1 == 1 -> 900.toShort()
        value_1 == 2 -> value_2.getShort(value_1.toInt()) - 9234.toShort()
        value_1 == 3 -> 9234.toShort() * 0.toShort()
        value_1 == 4 -> -6.toShort() / getShort(-9000)
        value_1 == 5 -> 6.toShort() % 112.toShort()
        value_1 == 5 -> -9313.toShort() % 10.toShort()
        value_1 == 6 -> 6.toShort() - value_4
        value_1 == 7 -> 50.toShort() + value_1 * -90.toShort() / value_3 % 112.toShort() - value_1
        value_1 == 8 -> {
            value_1 * -112.toShort() / 9234.toShort() - -1.toShort() + value_2.getShort(111) / 0.toShort() % -99.toShort() % 9234.toShort() + value_4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Int.
fun case_2(value_1: Int, value_1: Int, value_2: _BasicTypesProvider) {
    val value_3 = 912
    val value_4 = 124901924904

    when {
        value_1 == 1 -> 2
        value_1 == 2 -> 2 + 2
        value_1 == 3 -> 2 * -2
        value_1 == 4 -> getInt(-9) / 3
        value_1 == 5 -> -8 % 3
        value_1 == 6 -> -4 - 2
        value_1 == 7 -> value_2.getInt(111) + 2 * getInt(2) / -2 % 2 - 2
        value_1 == 8 -> 32 shl 2
        value_1 == 9 -> value_1 shr -value_3
        value_1 == 10 -> -64 ushr value_3
        value_1 == 11 -> value_3 and 4
        value_1 == 12 -> 16 or -5
        value_1 == 13 -> value_1 xor 55
        value_1 == 14 -> -55.inv()
        value_1 == 15 -> value_1 * -value_3
        value_1 == 16 -> {
            value_1 * 2 / 10 - 5 + value_2.getInt(-500) / 2 % -4 % value_4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Long.
fun case_3(value_1: Int, value_1: Long, value_2: _BasicTypesProvider) {
    val value_3 = 9L
    val value_4 = 124909249042341234L

    when {
        value_1 == 1 -> 2L
        value_1 == 2 -> 1249011249042341234L + 412L
        value_1 == 3 -> -2L * getLong(1000)
        value_1 == 4 -> 3241019249042341234L / -2L
        value_1 == 5 -> 324901924942341234L % -2L
        value_1 == 6 -> 4L - value_2.getLong(0)
        value_1 == 7 -> 2L + -9L * -10000000000000L / 2L % value_2.getLong(-99999999) - 2L
        value_1 == 8 -> value_1 * value_3
        value_1 == 9 -> {
            value_1 * 2L / -10L - 92490149042341234L + 1324019249042341234L / 2L % 234124312423452L % value_4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Float.
fun case_4(value_1: Int, value_1: Float, value_2: _BasicTypesProvider) {
    val value_3 = 912.2134F
    val value_4 = -124901924904.991242f

    when {
        value_1 == 1 -> 2.1F
        value_1 == 2 -> 2.1f + value_2.getFloat(-1)
        value_1 == 3 -> getFloat(-10) * 2f
        value_1 == 4 -> 8.5f / -2.3f
        value_1 == 5 -> value_2.getFloat(1111111) % 2f
        value_1 == 6 -> 4.0F - 2.1f
        value_1 == 7 -> 2f + .9f * -.0000000001F / 2F % 2.91f - -2.09F
        value_1 == 8 -> value_1 * -value_3
        value_1 == 9 -> {
            value_1 * 2F / -10.12414141f - .13104141040f + 0.5F / -2F % 4.0f % value_4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Double.
fun case_5(value_1: Int, value_1: Double, value_2: _BasicTypesProvider) {
    val value_3 = 912.2134
    val value_4 = -124901924904.99124212

    when {
        value_1 == 1 -> 2.1
        value_1 == 2 -> 2.1 + 2.5
        value_1 == 3 -> getDouble(-20) * 2.0
        value_1 == 4 -> -8.5 / 2.3
        value_1 == 5 -> 8.0 % value_2.getDouble(100000000)
        value_1 == 6 -> 4.0 - -2.1
        value_1 == 7 -> 2.4 + -.9 * -.0000000001 / value_2.getDouble(-10) % 2.91 - 2.09
        value_1 == 8 -> -value_1 * value_3
        value_1 == 9 -> {
            value_1 * -2.0 / 10.12414141 - .13104141040 + 0.5 / 2.0 % 4.0 % value_4
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression with Byte.
fun case_6(value_1: Int, value_1: Byte, value_2: _BasicTypesProvider) {
    val value_3: Byte = -11
    val value_4: Byte = 3
    val value_5: Byte = 127
    val value_6: Byte = 5
    val value_7: Byte = -128

    when {
        value_1 == 1 -> value_1
        value_1 == 2 -> -11.toByte() - value_3
        value_1 == 3 -> 90.toByte() * -value_5
        value_1 == 4 -> 2.toByte() / 100.toByte()
        value_1 == 5 -> value_4 % value_5
        value_1 == 6 -> 0.toByte() - 0.toByte()
        value_1 == 7 -> value_3 + -128.toByte() * 127.toByte() / getByte(-9999) % value_5 - value_2.getByte(9999999)
        value_1 == 8 -> {
            value_2.getByte(-100) * value_5 / -10.toByte() - value_6 + value_4 / 9.toByte() % value_5 % value_1 + value_7
        }
    }
}

// CASE DESCRIPTION: 'When' with control structure body as arithmetic expression (minus and plus of Integer) with Char.
fun case_7(value_1: Int, value_1: Char, value_2: _BasicTypesProvider) {
    val value_3: Char = 11.toChar()

    when {
        value_1 == 1 -> value_1
        value_1 == 2 -> 11.toChar() - 10
        value_1 == 3 -> 0.toChar() + 92
        value_1 == 3 -> value_3 + 11
        value_1 == 3 -> getChar(9) - 11
        value_1 == 4 -> {
            value_2.getChar(99) - 11 + 90
        }
    }
}