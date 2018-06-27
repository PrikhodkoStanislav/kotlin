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

    fun getUnit(): Unit {}

    fun getAny(): Any {
        return Any()
    }

    fun getList(): List<Int> {
        return mutableListOf()
    }
}

// arithmetic expressions with invalid syntax on Integer
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
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!SYNTAX!>99999999<!>
    } }
}

// arithmetic expressions with invalid syntax on Char
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
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!SYNTAX!>9.toChar()<!>
    } }
}

// arithmetic expressions with invalid syntax on Byte
fun test3(value: Int, value1: A) {
    when { value == 1 -> -100.toByte() <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getByte(10) }
    when { value == 1 -> 10.toByte() <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - 9900.toByte() }
    when { value == 1 -> getByte(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -<!UNSUPPORTED!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>13<!><!SYNTAX!><!SYNTAX!><!>.<!><!UNRESOLVED_REFERENCE!>toByte<!>() }
    when { value == 1 -> -100 <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getByte(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> <!UNSUPPORTED, UNUSED_EXPRESSION!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>9900<!><!SYNTAX!><!SYNTAX!><!>.<!><!UNRESOLVED_REFERENCE!>toByte<!>() <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -34.toByte() }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> (-34).toByte() }
    when { value == 1 -> -0.toByte() shr<!SYNTAX!><!> }
    when { value == 1 -> (-0).toByte() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!TYPE_MISMATCH!>0.toByte()<!><!SYNTAX!><!> }
    when { value == 1 -> 0.toByte() <!UNRESOLVED_REFERENCE!>ushr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!TYPE_MISMATCH!>234.toByte()<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000.toByte() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!TYPE_MISMATCH!>34.toByte()<!><!SYNTAX!><!> }
    when { value == 1 -> value1.getByte(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!><!SYNTAX!><!> }
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!SYNTAX!>99999999.toByte()<!>
    } }
}

// arithmetic expressions with invalid syntax on Short
fun test4(value: Int, value1: A) {
    when { value == 1 -> -100.toShort() <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getShort(10) }
    when { value == 1 -> 10.toShort() <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - 9900.toShort() }
    when { value == 1 -> getShort(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -<!UNSUPPORTED!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>13<!><!SYNTAX!><!SYNTAX!><!>.<!><!UNRESOLVED_REFERENCE!>toShort<!>() }
    when { value == 1 -> -100 <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getShort(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> <!UNSUPPORTED, UNUSED_EXPRESSION!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>9900<!><!SYNTAX!><!SYNTAX!><!>.<!><!UNRESOLVED_REFERENCE!>toShort<!>() <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -34.toShort() }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> (-34).toShort() }
    when { value == 1 -> -0.toShort() shr<!SYNTAX!><!> }
    when { value == 1 -> (-0).toShort() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!TYPE_MISMATCH!>0.toShort()<!><!SYNTAX!><!> }
    when { value == 1 -> 0.toShort() <!UNRESOLVED_REFERENCE!>ushr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!TYPE_MISMATCH!>234.toShort()<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000.toShort() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!TYPE_MISMATCH!>34.toShort()<!><!SYNTAX!><!> }
    when { value == 1 -> value1.getShort(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!><!SYNTAX!><!> }
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!SYNTAX!>99999999.toShort()<!>
    } }
}

// arithmetic expressions with invalid syntax on Long
fun test5(value: Int, value1: A) {
    when { value == 1 -> -100L <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getLong(10) }
    when { value == 1 -> 10L <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - 9900L }
    when { value == 1 -> getLong(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -<!UNSUPPORTED!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>13L<!><!SYNTAX!><!> }
    when { value == 1 -> -100L <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getLong(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> <!UNSUPPORTED, UNUSED_EXPRESSION!>0<!><!CONSTANT_EXPECTED_TYPE_MISMATCH, UNSUPPORTED!>0<!><!UNSUPPORTED, SYNTAX!>9900L<!><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -34L }
    when { value == 1 -> -0L shr<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>0L<!><!SYNTAX!><!> }
    when { value == 1 -> 0L ushr<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>234L<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000L or<!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>34L<!><!SYNTAX!><!> }
    when { value == 1 -> value1.getLong(10) and<!SYNTAX!><!> }
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!SYNTAX!>99999999L<!>
    } }
}

// arithmetic expressions with invalid syntax on Float
fun test6(value: Int, value1: A) {
    when { value == 1 -> -100f <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getFloat(10) }
    when { value == 1 -> 0.10F <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - .0000001F }
    when { value == 1 -> getFloat(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -0013f }
    when { value == 1 -> -.001020123f <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getFloat(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> 009900F <!OVERLOAD_RESOLUTION_AMBIGUITY!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -0.034F }
    when { value == 1 -> -0f <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>20.0000f<!><!SYNTAX!><!> }
    when { value == 1 -> 0F <!UNRESOLVED_REFERENCE!>ushr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>99.999F<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000f <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>-<!>0.34f }
    when { value == 1 -> value1.getFloat(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!><!SYNTAX!><!> }
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>-<!>10.01F
    } }
}

// arithmetic expressions with invalid syntax on Double
fun test7(value: Int, value1: A) {
    when { value == 1 -> -100.0 <!OVERLOAD_RESOLUTION_AMBIGUITY!>+<!><!SYNTAX!><!> }
    when { value == 1 -> + value1.getDouble(10) }
    when { value == 1 -> 0.10 <!OVERLOAD_RESOLUTION_AMBIGUITY!>-<!><!SYNTAX!><!> }
    when { value == 1 -> - .0000001 }
    when { value == 1 -> getDouble(10) <!OVERLOAD_RESOLUTION_AMBIGUITY!>*<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>*<!> -0013.0 }
    when { value == 1 -> -.001020123 <!OVERLOAD_RESOLUTION_AMBIGUITY!>/<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>/<!> getDouble(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when { value == 1 -> 009900.0 <!OVERLOAD_RESOLUTION_AMBIGUITY!>%<!><!SYNTAX!><!> }
    when { value == 1 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>%<!> -0.034 }
    when { value == 1 -> -0.0 <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>shr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>20.0000<!><!SYNTAX!><!> }
    when { value == 1 -> 0.0 <!UNRESOLVED_REFERENCE!>ushr<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>ushr<!> <!CONSTANT_EXPECTED_TYPE_MISMATCH!>99.999<!><!SYNTAX!><!> }
    when { value == 1 -> 100000000.0 <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!><!SYNTAX!><!> }
    when { value == 1 -> <!UNRESOLVED_REFERENCE!>or<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>-<!>0.34 }
    when { value == 1 -> value1.getDouble(10) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!><!SYNTAX!><!> }
    when { value == 1 -> {
        <!UNRESOLVED_REFERENCE!>and<!> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>-<!>10.01
    } }
}

// arithmetic expressions with type errors on Integer
fun test8(value: Int, value1: A) {
    when { value == 1 -> -100 <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> 999 <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> -39213 <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> getInt(42) <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0 or <!TYPE_MISMATCH!>value1.getUnit()<!> }
    when { value == 1 -> -0 <!UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> value1.getInt(-44234) shr <!TYPE_MISMATCH!>value1.getString(11)<!> }
    when { value == 1 -> -39213 ushr <!TYPE_MISMATCH!>getList()<!> }
    when { value == 1 -> {
        -39213 ushr getInt(11) - value1.getByte(11) or <!TYPE_MISMATCH!>value1.getList()<!>
    } }
}

// arithmetic expressions with type errors on Char
fun test9(value: Int, value1: A) {
    when { value == 1 -> value1.getChar(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>*<!> 524 }
    when { value == 1 -> (-0).toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>/<!> getInt(13) }
    when { value == 1 -> 13.toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>%<!> value1.getInt(34) }
    when { value == 1 -> getChar(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!> getInt(34) }
    when { value == 1 -> value1.getChar(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>and<!> -0 }
    when { value == 1 -> 90.toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!> 0 }
    when { value == 1 -> value1.getChar(13) <!UNRESOLVED_REFERENCE!>ushr<!> 756525 }
    when { value == 1 -> getChar(13) <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> (-9999999).toChar() + <!TYPE_MISMATCH!>value1.getList()<!> }
    when { value == 1 -> value1.getChar(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>/<!> value1.getAny() }
    when { value == 1 -> 0.toChar() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>%<!> getUnit() }
    when { value == 1 -> {
        value1.getChar(13) + 11 - 20 * getInt(44) / value1.getByte(11) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!> value1.getList()
    } }
}

// arithmetic expressions with type errors on Byte
fun test10(value: Int, value1: A) {
    when { value == 1 -> (-100).toByte() <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> value1.getByte(-123) <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> (-39213).toByte() <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> 13.toByte() <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0.toByte() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!> value1.getUnit() }
    when { value == 1 -> (-0).toByte() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> getByte(11) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!> value1.getString(11) }
    when { value == 1 -> (-39213).toByte() <!UNRESOLVED_REFERENCE!>ushr<!> getList() }
    when { value == 1 -> {
        (-39213).toByte() <!UNRESOLVED_REFERENCE!>ushr<!> getInt(11) - value1.getByte(11) <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>or<!> value1.getList()
    } }
}

// arithmetic expressions with type errors on Short
fun test11(value: Int, value1: A) {
    when { value == 1 -> (-100).toShort() <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> 999.toShort() <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> (-39213).toShort() <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> getShort(43) <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0.toShort() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!> value1.getUnit() }
    when { value == 1 -> (-0).toShort() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> value1.getShort(-53) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!> value1.getString(11) }
    when { value == 1 -> (-39213).toShort() <!UNRESOLVED_REFERENCE!>ushr<!> getList() }
    when { value == 1 -> {
        (-39213).toShort() <!UNRESOLVED_REFERENCE!>ushr<!> getShort(11) - value1.getShort(11) <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>or<!> value1.getList()
    } }
}

// arithmetic expressions with type errors on Long
fun test12(value: Int, value1: A) {
    when { value == 1 -> -100L <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> 999L <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> -39213L <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> getLong(123) <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0L or <!TYPE_MISMATCH!>value1.getUnit()<!> }
    when { value == 1 -> -0L <!UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> value1.getLong(13) shr <!TYPE_MISMATCH!>value1.getString(11)<!> }
    when { value == 1 -> -39213L ushr <!TYPE_MISMATCH!>getList()<!> }
    when { value == 1 -> {
        -39213L ushr <!TYPE_MISMATCH!>getLong(11) - value1.getByte(11)<!> or <!TYPE_MISMATCH!>value1.getList()<!>
    } }
}

// arithmetic expressions with type errors on Float
fun test13(value: Int, value1: A) {
    when { value == 1 -> 100.0f <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> 0.0012f <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> -39213F <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> getFloat(123) <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0F <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>or<!> value1.getUnit() }
    when { value == 1 -> -0f <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> value1.getFloat(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!> value1.getString(11) }
    when { value == 1 -> -.123123f <!UNRESOLVED_REFERENCE!>ushr<!> getList() }
    when { value == 1 -> {
        -23424.11f <!UNRESOLVED_REFERENCE!>ushr<!> getLong(11) - value1.getByte(11) <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>or<!> value1.getList()
    } }
}

// arithmetic expressions with type errors on Double
fun test14(value: Int, value1: A) {
    when { value == 1 -> 100.0 <!NONE_APPLICABLE!>+<!> value1.getChar(13) }
    when { value == 1 -> 0.0012 <!NONE_APPLICABLE!>%<!> value1.getAny() }
    when { value == 1 -> -39213 <!OVERLOAD_RESOLUTION_AMBIGUITY, UNREACHABLE_CODE!>-<!> getNothing() }
    when { value == 1 -> getDouble(123) <!NONE_APPLICABLE!>*<!> getString(11) }
    when { value == 1 -> 0 or <!TYPE_MISMATCH!>value1.getUnit()<!> }
    when { value == 1 -> -0 <!UNREACHABLE_CODE!>and<!> value1.getNothing() }
    when { value == 1 -> value1.getDouble(13) <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>shr<!> value1.getString(11) }
    when { value == 1 -> -.123123 <!UNRESOLVED_REFERENCE!>ushr<!> getList() }
    when { value == 1 -> {
        -23424.11 <!UNRESOLVED_REFERENCE!>ushr<!> getLong(11) - value1.getByte(11) <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>or<!> value1.getList()
    } }
}