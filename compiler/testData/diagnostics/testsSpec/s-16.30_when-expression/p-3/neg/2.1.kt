/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: When with not boolean condition in when entry
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

    fun getUnit(): Unit {}

    fun getAny(): Any {
        return Any()
    }

    fun getList(): List<Int> {
        return mutableListOf()
    }
}

// numbers in when entry
fun test1(
    value1: Int,
    value2: Int,
    value3: Short,
    value4: Byte,
    value5: Char,
    value6: Long,
    value7: Float,
    value8: Double,
    value9: A
): Int {
    when {
        <!TYPE_MISMATCH!>-9 + 11<!> -> return 1
        <!TYPE_MISMATCH!>9 / 11<!> -> return 2
        <!TYPE_MISMATCH!>9 * value2<!> -> return 3
        <!TYPE_MISMATCH!>-9 % 11<!> -> return 4
        <!TYPE_MISMATCH!>9 or 11<!> -> return 5
        <!TYPE_MISMATCH!>-9 and 11<!> -> return 6
        <!TYPE_MISMATCH!>value1 xor 11<!> -> return 7
        <!TYPE_MISMATCH!>-0 shr 50<!> -> return 8
        <!TYPE_MISMATCH!>value1 ushr value2<!> -> return 9
        <!TYPE_MISMATCH!>9.inv()<!> -> return 10
        <!TYPE_MISMATCH!>9 % 11 + 123 - value1 or 11 and value2<!> -> return 11
        <!TYPE_MISMATCH!>value3 * 143.toShort()<!> -> return 12
        <!TYPE_MISMATCH!>getShort(13) - 143.toShort()<!> -> return 13
        <!TYPE_MISMATCH!>value4 * (-143).toByte()<!> -> return 14
        <!TYPE_MISMATCH!>value9.getByte(22) % 143.toByte()<!> -> return 15
        <!TYPE_MISMATCH!>value5 +10<!> -> return 16
        <!TYPE_MISMATCH!>143.toChar() - 11<!> -> return 17
        <!TYPE_MISMATCH!>value6 * 432L<!> -> return 18
        <!TYPE_MISMATCH!>-0L * 20L<!> -> return 19
        <!TYPE_MISMATCH!>value9.getLong(123) % 1234123513543L<!> -> return 20
        <!TYPE_MISMATCH!>-.012f / value7<!> -> return 21
        <!TYPE_MISMATCH!>value9.getLong(321) - 10000f<!> -> return 22
        <!TYPE_MISMATCH!>-.012 + value8<!> -> return 23
        <!TYPE_MISMATCH!>value9.getDouble(321) - 10000.0<!> -> return 24
        <!TYPE_MISMATCH!>9 % 11 + 123 - value1 or 11 and <!TYPE_MISMATCH!>value2 / value9.getDouble(321) - -.1223F<!><!> -> return 25
    }

    return -1
}

// strings and chars in when entry
fun test2(value1: String, value2: Char, value3: A): Int {
    when {
        <!TYPE_MISMATCH!>""<!> -> return 1
        <!CONSTANT_EXPECTED_TYPE_MISMATCH!>'-'<!> -> return 2
        <!TYPE_MISMATCH!>"$value1$value2"<!> -> return 3
        <!TYPE_MISMATCH!>value1<!> -> return 4
        <!TYPE_MISMATCH!>"$value1${getString(43)}"<!> -> return 5
        <!TYPE_MISMATCH!>"${value3.getString(33)}"<!> -> return 6
        <!TYPE_MISMATCH!>"${value3.getString(33)}"<!> -> return 7
        <!TYPE_MISMATCH!>getChar(32)<!> -> return 8
        <!TYPE_MISMATCH!>value3.getChar(32) - 20<!> -> return 9
        <!TYPE_MISMATCH!>value1 + "..." + value3.getString(43)<!> -> return 10
    }

    return -1
}

// Nothing in when entry
fun test3(value1: Nothing, <!UNUSED_PARAMETER!>value2<!>: A): Int {
    when {
        value1 -> <!UNREACHABLE_CODE!>return 1<!>
        <!UNREACHABLE_CODE!>value2.getNothing() -> return 2<!>
        <!UNREACHABLE_CODE!>getNothing() -> return 3<!>
        <!UNREACHABLE_CODE!>throw Exception() -> return 4<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

// Unit in when entry
fun test4(value1: Unit, value2: A): Int {
    when {
        <!TYPE_MISMATCH!>value1<!> -> return 1
        <!TYPE_MISMATCH!>value2.getUnit()<!> -> return 2
        <!TYPE_MISMATCH!>getUnit()<!> -> return 3
        <!TYPE_MISMATCH!>{}<!> -> return 4
        <!TYPE_MISMATCH!>fun() {}<!> -> return 5
    }

    return -1
}

// Any in when entry
fun test5(value1: Any, value2: A): Int {
    when {
        <!TYPE_MISMATCH!>value1<!> -> return 1
        <!TYPE_MISMATCH!>value2.getAny()<!> -> return 2
        <!TYPE_MISMATCH!>getAny()<!> -> return 3
    }

    return -1
}

// Collection in when entry
fun test5(value1: List<Int>, value2: A): Int {
    when {
        <!TYPE_MISMATCH!>value1<!> -> return 1
        <!TYPE_MISMATCH!>value2.getList()<!> -> return 2
        <!TYPE_MISMATCH!>getList()<!> -> return 3
    }

    return -1
}