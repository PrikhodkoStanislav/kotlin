/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 2
 DESCRIPTION: When with different variants of the logical expressions in the control structure bodies.
 */

fun getInt(number: Int): Int {
    return number + 11
}

fun getDouble(number: Int): Double {
    return (number + 11).toDouble()
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

    fun getDouble(number: Int): Double {
        return (number + 11).toDouble()
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

// logical expressions with invalid syntax on Conjunction
fun test1(value: Int, value1: Boolean, value2: Boolean) {
    val value3 = true
    val value4 = false

    when {value == 1 -> !value1 &&<!SYNTAX!><!>}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>&&<!> !value2}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>&&<!> <!UNUSED_EXPRESSION!>true<!>}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>&&<!><!SYNTAX!><!>}
    when {
        value == 10 -> {
            value4 && value1 && value3 && value4 && !!!false &&<!SYNTAX!><!>
        }
    }
}

// logical expressions with invalid syntax on Disjunction
fun test2(value: Int, value1: Boolean, value2: Boolean) {
    val value3 = true
    val value4 = false

    when {value == 1 -> !value1 ||<!SYNTAX!><!>}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>||<!>!value2}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>||<!> <!UNUSED_EXPRESSION!>true<!>}
    when {value == 2 -><!SYNTAX!><!> <!SYNTAX!><!SYNTAX!><!>||<!><!SYNTAX!><!>}
    when {
        value == 10 -> {
            value4 || value1 || value3 || value4 || !!!false &&<!SYNTAX!><!>
        }
    }
}

// logical expressions with invalid syntax on not operator
fun test3(value: Int, value1: Boolean, value2: Boolean) {
    val value3 = true
    val value4 = false

    when {value == 1 -> ! !(!(!value1))}
    when {value == 2 -> ! !value2 }
    when {value == 2 -> !! !true }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!DEBUG_INFO_MISSING_UNRESOLVED!>!<!><!SYNTAX!><!>}
    when {
        value == 10 -> {
            !value4 || !! !value1 || ! ! value3 || ! value4 || ! ! !false
        }
    }
}

// logical expressions with type errors on Conjunction
fun test4(value: Int, value1: Boolean, value2: Boolean, value5: A) {
    val value3 = true
    val value4 = false

    when {value == 1 -> !value1 && <!TYPE_MISMATCH!>getAny()<!>}
    when {value == 2 -> getNothing()<!UNREACHABLE_CODE!>&&!value2<!>}
    when {value == 2 -> true&&<!TYPE_MISMATCH!>getList()<!>}
    when {value == 2 -> false&&<!TYPE_MISMATCH!>value5.getInt(11)<!>}
    when {value == 2 -> value2 && true && <!TYPE_MISMATCH!>getUnit()<!>}
    when {value == 2 -> value2&&!!!true&&<!TYPE_MISMATCH!>value5.getDouble(55)<!>}
    when {
        value == 10 -> {
            value1 && value3 && value4 && true && !!!false && <!TYPE_MISMATCH!>value5.getUnit()<!>
        }
    }
}

// logical expressions with type errors on Disjunction
fun test5(value: Int, value1: Boolean, value2: Boolean, value5: A) {
    val value3 = true
    val value4 = false

    when {value == 1 -> !value1 || <!TYPE_MISMATCH!>getAny()<!>}
    when {value == 2 -> getNothing()<!UNREACHABLE_CODE!>||!value2<!>}
    when {value == 2 -> true||<!TYPE_MISMATCH!>getList()<!>}
    when {value == 2 -> <!UNUSED_EXPRESSION!>true||<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!><!>}
    when {value == 2 -> false||<!TYPE_MISMATCH!>value5.getInt(11)<!>}
    when {value == 2 -> value2 || true || <!TYPE_MISMATCH!>getUnit()<!>}
    when {value == 2 -> value2||!!!true||<!TYPE_MISMATCH!>""<!>}
    when {value == 2 -> value2||!!!true||<!CONSTANT_EXPECTED_TYPE_MISMATCH!>''<!>}
    when {value == 2 -> value2||!!!true||<!NULL_FOR_NONNULL_TYPE!>null<!>}
    when {value == 2 -> <!UNUSED_EXPRESSION!>value2||<!TYPE_MISMATCH!>{}<!><!>}
    when {
        value == 10 -> {
            value1 || value3 || value4 || true || !!!false || <!TYPE_MISMATCH!>value5.getUnit()<!>
        }
    }
}

// logical expressions with type errors on not operator
fun test6(value: Int, value1: Boolean, value2: Boolean, value5: A) {
    val value3 = true
    val value4 = false

    when {value == 1 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>getDouble(31)}
    when {value == 2 -> <!UNRESOLVED_REFERENCE!>!<!>value5.getUnit() }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>value5.getAny() }
    when {value == 2 -> <!UNREACHABLE_CODE!><!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!><!>value5.getNothing() }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>value5.getString(<!NO_VALUE_FOR_PARAMETER!>)<!> }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>0 }
    when {value == 2 -> <!UNRESOLVED_REFERENCE!>!<!>-0 }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>null }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>"" }
    when {value == 2 -> <!UNRESOLVED_REFERENCE!>!<!><!EMPTY_CHARACTER_LITERAL!>''<!> }
    when {value == 2 -> <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>!<!><!UNRESOLVED_REFERENCE!>!<!>{} }
    when {
        value == 10 -> {
            !value1 || !!value2 || !value3 || !value4 || <!UNRESOLVED_REFERENCE!>!<!>{}
        }
    }
}
