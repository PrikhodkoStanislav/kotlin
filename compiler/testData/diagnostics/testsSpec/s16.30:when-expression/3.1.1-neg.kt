/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 1
 DESCRIPTION: When with different variants of the invalid arithmetic expressions in the control structure bodies.
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

fun getString(number: Int): String {
    return "${(number + 11).toChar()}..."
}

fun getNothing(): Nothing = throw Exception()

fun getUnit(): Unit {}

fun getAny(): Any {
    return Any()
}

fun getList(): List<Int> {
    return mutableListOf()
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

    fun getString(number: Int): String {
        return "${(number + 11).toChar()}..."
    }

    fun getNothing(): Nothing = throw Exception()

    fun getUnit(): Unit {

    }

    fun getAny(): Any {
        return Any()
    }

    fun getList(): List<Int> {
        return mutableListOf()
    }
}

// arithmetic expressions with invalid syntax on integers
fun test1(value: Int, value1: A) {
    when { value == 1 -> -100 <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getInt(10) }
    when { value == 1 -> 10 <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - 9900 }
    when { value == 1 -> getInt(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -<!UNSUPPORTED!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>13<!><!SYNTAX!><!> }
    when { value == 1 -> -100 <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getInt(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> <!UNSUPPORTED, UNUSED_EXPRESSION!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>9900<!><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -34 }
    when { value == 1 -> -0 shr<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>0<!><!SYNTAX!><!> }
    when { value == 1 -> 0 ushr<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>234<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000 or<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>34<!><!SYNTAX!><!> }
    when { value == 1 -> value1.getInt(10) and<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>and<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>99999999<!><!SYNTAX!><!> }
}

// arithmetic expressions with invalid syntax on chars
fun test2(value: Int, value1: A) {
    when { value == 1 -> 100.toChar() +<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>+<!> value1.getChar(10) }
    when { value == 1 -> <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>-<!>10.toChar() <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>-<!><!SYNTAX!><!> }
    when { value == 1 -> (-10).toChar() <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>-<!> 99.toChar() }
    when { value == 1 -> getChar(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> 13.toChar() }
    when { value == 1 -> <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>-<!>1.toChar() <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>/<!><!SYNTAX!><!> }
    when { value == 1 -> (-1).toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getChar(10) }
    when { value == 1 -> 0.toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -34 }
    when { value == 1 -> 94.toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>0<!><!SYNTAX!><!> }
    when { value == 1 -> 255.toChar() <!UNRESOLVED_REFERENCE!>ushr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!TYPE_MISMATCH!>234.toChar()<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>-<!>128.toChar() <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>or<!><!SYNTAX!><!> }
    when { value == 1 -> (-128).toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!TYPE_MISMATCH!>34.toChar()<!><!SYNTAX!><!> }
    when { value == 1 -> value1.getChar(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>and<!> <!TYPE_MISMATCH!>9.toChar()<!><!SYNTAX!><!> }
}

fun int(value: Int, value1: Int, value2: A) {
    val value3 = 912
    val value4 = 124901924904

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>2<!>
        value == 2 -> 2 + 2
        value == 3 -> 2 * -2
        value == 4 -> getInt(-9) / 3
        value == 5 -> -8 % 3
        value == 6 -> -4 - 2
        value == 7 -> value2.getInt(111) + 2 * getInt(2) / -2 % 2 - 2
        value == 8 -> 32 shl 2
        value == 9 -> value1 shr -value3
        value == 10 -> -64 ushr value3
        value == 11 -> value3 and 4
        value == 12 -> 16 or -5
        value == 13 -> value1 xor 55
        value == 14 -> -55.inv()
        value == 15 -> value1 * -value3
        value == 16 -> {
            value1 * 2 / 10 - 5 + value2.getInt(-500) / 2 % -4 % value4
        }
    }
}

fun long(value: Int, value1: Long, value2: A) {
    val value3 = 9L
    val value4 = 124909249042341234L

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>2L<!>
        value == 2 -> 1249011249042341234L + 412L
        value == 3 -> -2L * getLong(1000)
        value == 4 -> 3241019249042341234L / -2L
        value == 5 -> 324901924942341234L % -2L
        value == 6 -> 4L - value2.getLong(0)
        value == 7 -> 2L + -9L * -10000000000000L / 2L % value2.getLong(-99999999) - 2L
        value == 8 -> value1 * value3
        value == 9 -> {
            value1 * 2L / -10L - 92490149042341234L + 1324019249042341234L / 2L % 234124312423452L % value4
        }
    }
}

fun float(value: Int, value1: Float, value2: A) {
    val value3 = 912.2134F
    val value4 = -124901924904.991242f

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>2.1F<!>
        value == 2 -> 2.1f + value2.getFloat(-1)
        value == 3 -> getFloat(-10) * 2f
        value == 4 -> 8.5f / -2.3f
        value == 5 -> value2.getFloat(1111111) % 2f
        value == 6 -> 4.0F - 2.1f
        value == 7 -> 2f + .9f * -.0000000001F / 2F % 2.91f - -2.09F
        value == 8 -> value1 * -value3
        value == 9 -> {
            value1 * 2F / -10.12414141f - .13104141040f + 0.5F / -2F % 4.0f % value4
        }
    }
}

fun double(value: Int, value1: Double, value2: A) {
    val value3 = 912.2134
    val value4 = -124901924904.99124212

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>2.1<!>
        value == 2 -> 2.1 + 2.5
        value == 3 -> getDouble(-20) * 2.0
        value == 4 -> -8.5 / 2.3
        value == 5 -> 8.0 % value2.getDouble(100000000)
        value == 6 -> 4.0 - -2.1
        value == 7 -> 2.4 + -.9 * -.0000000001 / value2.getDouble(-10) % 2.91 - 2.09
        value == 8 -> -value1 * value3
        value == 9 -> {
            value1 * -2.0 / 10.12414141 - .13104141040 + 0.5 / 2.0 % 4.0 % value4
        }
    }
}

fun byte(value: Int, value1: Byte, value2: A) {
    val value3: Byte = -11
    val value4: Byte = 3
    val value5: Byte = 127
    val value6: Byte = 5
    val value7: Byte = -128

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>value1<!>
        value == 2 -> -11.toByte() - value3
        value == 3 -> 90.toByte() * -value5
        value == 4 -> 2.toByte() / 100.toByte()
        value == 5 -> value4 % value5
        value == 6 -> 0.toByte() - 0.toByte()
        value == 7 -> value3 + -128.toByte() * 127.toByte() / getByte(-9999) % value5 - value2.getByte(9999999)
        value == 8 -> {
            value2.getByte(-100) * value5 / -10.toByte() - value6 + value4 / 9.toByte() % value5 % value1 + value7
        }
    }
}

fun char(value: Int, value1: Char, value2: A) {
    val value3: Char = 11.toChar()

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>value1<!>
        value == 2 -> 11.toChar() - 10
        value == 3 -> 0.toChar() + 92
        value == 3 -> value3 + 11
        value == 3 -> getChar(9) - 11
        value == 4 -> {
            value2.getChar(99) - 11 + 90
        }
    }
}