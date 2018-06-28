/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 3
 DESCRIPTION: When with bound value and equality expressions in when entry.
 */

fun test1(value: Boolean, flag1: Boolean, flag2: Boolean, obj1: List<String>, obj2: List<String>): Int = when (value) {
    flag1 == flag2 -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>flag1 === flag2<!> -> 2
    obj1 === obj2 -> 3
    else -> 4
}

fun test2(value: Boolean, flag1: Boolean, flag2: Boolean, obj1: List<String>, obj2: List<String>): Int {
    when (value) {
        flag1 == flag2 -> return 1
        <!DEPRECATED_IDENTITY_EQUALS!>flag1 === flag2<!> -> return 2
        obj1 === obj2 -> return 3
    }

    return -1
}

fun test3(value: Boolean, flag1: Boolean, flag2: Boolean, obj1: List<String>, obj2: List<String>): Int = when (value) {
    flag1 != flag2 -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>flag1 !== flag2<!> -> 2
    obj1 !== obj2 -> 3
    else -> 4
}

fun test4(value: Boolean, value1: Char, value2: String): Int = when (value) {
    value1 == '.' -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value1 !== '-'<!> -> 2
    value2 == "..." -> 3
    value2 != "" -> 4
    "zzz" !== "" -> 5
    '=' != 'z' -> 6
    <!DEPRECATED_IDENTITY_EQUALS!>'-' === '-'<!> -> 7
    <!DEPRECATED_IDENTITY_EQUALS!>'=' !== 'z'<!> -> 8
    else -> 9
}

fun test5(value: Boolean, value1: Int, value2: Float, value3: Double, value4: Byte, value5: Char, value6: Short, value7: Long): Int = when (value) {
    value1 == 9921 -> 1
    value1 != 212 -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value1 !== -1111111<!> -> 1
    900 == -10 -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>900 === -10<!> -> 1

    <!DEPRECATED_IDENTITY_EQUALS!>value2 !== 11.4f<!> -> 2
    value2 == -.4f -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>value2 === 100000F<!> -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>0.133f !== .0132F<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>0.900F !== -10f<!> -> 1

    value3 == 3.11 -> 2
    value3 != 0.01 -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>value3 !== 1.01<!> -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>0.133 === .0132<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>0.900 !== -10.0<!> -> 1

    value4 == 100.toByte() -> 1
    value4 != 10.toByte() -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value4 !== 100L.toByte()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>90L.toByte() === 100.toByte()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>0L.toByte() === 88.toByte()<!> -> 1

    value5 == 100.toChar() -> 1
    value5 != 10.toChar() -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value5 === 100L.toChar()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>90L.toChar() !== 100.toChar()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>0L.toChar() === 88.toChar()<!> -> 1

    value6 == 100.toShort() -> 1
    value6 != 10.toShort() -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value6 === 100L.toShort()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>90L.toShort() !== 100.toShort()<!> -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>0L.toShort() === 88.toShort()<!> -> 1

    value7 == 100L -> 1
    value7 != -10L -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>value7 === -100L<!> -> 1
    1902901293L != -9902901293L -> 1
    <!DEPRECATED_IDENTITY_EQUALS!>902901293L !== 3902901293L<!> -> 1
}

fun test6(value: Boolean, flag1: Boolean, flag2: Boolean, obj1: List<String>, obj2: List<String>): Int {
    when (value) {
        flag1 != flag2 -> return 1
        <!DEPRECATED_IDENTITY_EQUALS!>flag1 !== flag2<!> -> return 2
        obj1 !== obj2 -> return 3
    }

    return -1
}

fun test7(value: Boolean): Int = when (value) {
    true || false == false || !false && true -> 1
    false || false != false || !!!!false && true -> 2
}

fun test8(value: Boolean): Int = when (value) {
    true || false == false || !false && true -> 1
    false || false != false || !!!!false && true -> 2
}

fun test9(value: Boolean): Int = when (value) {
    true || false == false || !false && true -> 1
    false || false != false || !!!!false && true -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun test10(value: Boolean, value1: Boolean, value2: Boolean): Int = when (value) {
    value1 == false || !false && true -> 1
    value2 != false || !!!!false && true -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>value2 !== !false<!> || !!!false && true -> 2
    <!DEPRECATED_IDENTITY_EQUALS!>value1 === !!false<!> || !(false && true) -> 2
    else -> 3
}

fun test11(value: Boolean): Int {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value) {
        true || false == false || !false && true -> return 1
        false || false != false || !!!!false && true -> return 2
    }<!>

    <!UNREACHABLE_CODE!>return -1<!>
}