// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [2] Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 1
 DESCRIPTION: 'When' without bound value_1 and with not boolean condition in 'when condition'.
 TODO: reuse in the parser tests
 */

// CASE DESCRIPTION: 'When' with numbers (Int, Short, Byte, Long, Float, Double) and Char (used as a number) in 'when condition'.
fun case_1(
    value_1: Int,
    value_2: Int,
    value_3: Short,
    value_4: Byte,
    value_5: Char,
    value_6: Long,
    value_7: Float,
    value_8: Double,
    value_9: _BasicTypesProvider
): String {
    when {
        <!TYPE_MISMATCH!>-9 + 11<!> -> return ""
        <!TYPE_MISMATCH!>9 / 11<!> -> return ""
        <!TYPE_MISMATCH!>9 * value_2<!> -> return ""
        <!TYPE_MISMATCH!>-9 % 11<!> -> return ""
        <!TYPE_MISMATCH!>9 or 11<!> -> return ""
        <!TYPE_MISMATCH!>-9 and 11<!> -> return ""
        <!TYPE_MISMATCH!>value_1 xor 11<!> -> return ""
        <!TYPE_MISMATCH!>-0 shr 50<!> -> return ""
        <!TYPE_MISMATCH!>value_1 ushr value_2<!> -> return ""
        <!TYPE_MISMATCH!>9.inv()<!> -> return ""
        <!TYPE_MISMATCH!>9 % 11 + 123 - value_1 or 11 and value_2<!> -> return ""
        <!TYPE_MISMATCH!>value_3 * 143.toShort()<!> -> return ""
        <!TYPE_MISMATCH!>getShort(13) - 143.toShort()<!> -> return ""
        <!TYPE_MISMATCH!>value_4 * (-143).toByte()<!> -> return ""
        <!TYPE_MISMATCH!>value_9.getByte(22) % 143.toByte()<!> -> return ""
        <!TYPE_MISMATCH!>value_5 +10<!> -> return ""
        <!TYPE_MISMATCH!>143.toChar() - 11<!> -> return ""
        <!TYPE_MISMATCH!>value_6 * 432L<!> -> return ""
        <!TYPE_MISMATCH!>-0L * 20L<!> -> return ""
        <!TYPE_MISMATCH!>value_9.getLong(123) % 1234123513543L<!> -> return ""
        <!TYPE_MISMATCH!>-.012f / value_7<!> -> return ""
        <!TYPE_MISMATCH!>value_9.getLong(321) - 10000f<!> -> return ""
        <!TYPE_MISMATCH!>-.012 + value_8<!> -> return ""
        <!TYPE_MISMATCH!>value_9.getDouble(321) - 10000.0<!> -> return ""
        <!TYPE_MISMATCH!>9 % 11 + 123 - value_1 or 11 and <!TYPE_MISMATCH!>value_2 / value_9.getDouble(321) - -.1223F<!><!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with String and Char in 'when condition'.
fun case_2(value_1: String, value_2: Char, value_3: _BasicTypesProvider): String {
    when {
        <!TYPE_MISMATCH!>""<!> -> return ""
        <!CONSTANT_EXPECTED_TYPE_MISMATCH!>'-'<!> -> return ""
        <!TYPE_MISMATCH!>"$value1$value2"<!> -> return ""
        <!TYPE_MISMATCH!>value_1<!> -> return ""
        <!TYPE_MISMATCH!>"$value1${getString(43)}"<!> -> return ""
        <!TYPE_MISMATCH!>"${value_3.getString(33)}"<!> -> return ""
        <!TYPE_MISMATCH!>"${value_3.getString(33)}"<!> -> return ""
        <!TYPE_MISMATCH!>getChar(32)<!> -> return ""
        <!TYPE_MISMATCH!>value_3.getChar(32) - 20<!> -> return ""
        <!TYPE_MISMATCH!>value_1 + "..." + value_3.getString(43)<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with Unit in 'when condition'.
fun case_3(value_1: Unit, value_2: _BasicTypesProvider): String {
    when {
        <!TYPE_MISMATCH!>value_1<!> -> return ""
        <!TYPE_MISMATCH!>value_2.getUnit()<!> -> return ""
        <!TYPE_MISMATCH!>getUnit()<!> -> return ""
        <!TYPE_MISMATCH!>{}<!> -> return ""
        <!TYPE_MISMATCH!>fun() {}<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with Any in 'when condition'.
fun case_4(value_1: Any, value_2: _BasicTypesProvider): String {
    when {
        <!TYPE_MISMATCH!>value_1<!> -> return ""
        <!TYPE_MISMATCH!>value_2.getAny()<!> -> return ""
        <!TYPE_MISMATCH!>getAny()<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with List (Collection example) in 'when condition'.
fun case_5(value_1: List<Int>, value_2: _BasicTypesProvider): String {
    when {
        <!TYPE_MISMATCH!>value_1<!> -> return ""
        <!TYPE_MISMATCH!>value_2.getList()<!> -> return ""
        <!TYPE_MISMATCH!>getList()<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with range expression (IntRange) in 'when condition'.
fun case_6(value_1: Int, value_2: Int): String {
    when {
        <!TYPE_MISMATCH!>-10..-1<!> -> return ""
        <!TYPE_MISMATCH!>-0..0<!> -> return ""
        <!TYPE_MISMATCH!>1..value_1<!> -> return ""
        <!TYPE_MISMATCH!>value_1 + 1..value_2<!> -> return ""
    }

    return ""
}