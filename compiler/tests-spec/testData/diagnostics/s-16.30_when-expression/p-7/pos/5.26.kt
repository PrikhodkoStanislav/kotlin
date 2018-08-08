// !WITH_BASIC_TYPES
// !WITH_CLASSES
// !WITH_SEALED_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 7
 SENTENCE 5: When expression with bound value (the form where the expression enclosed in parantheses is present) are very similar to the form without bound value, but use different syntax for conditions.
 NUMBER: 26
 DESCRIPTION: 'When' with bound value and a enumeration values of the subtype of the bound value in 'when entry'.
 */

// CASE DESCRIPTION: 'When' as statement with values of the subtype of Number.
fun case_1(value: Number, <!UNUSED_PARAMETER!>value7<!>: _BasicTypesProvider): String {
    when (value) {
        21, 0, -1, 100000, Int.MAX_VALUE -> return ""
        211, -0L, Float.MAX_VALUE -> return ""
        getByte("1"), Double.NEGATIVE_INFINITY, Byte.MIN_VALUE -> return ""
        Double.NaN, -.1212 -> return ""
        throw Exception()<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
    }

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' as expression with values of the subtype of nullable Number.
fun case_2(value: Number?, <!UNUSED_PARAMETER!>value7<!>: _BasicTypesProvider): String = when (value) {
    21, 0, -1, 100000, Int.MAX_VALUE -> ""
    211, -0L, Float.MAX_VALUE -> ""
    getByte("1"), Double.NEGATIVE_INFINITY, Byte.MIN_VALUE -> ""
    Double.NaN, -.1212 -> ""
    throw Exception()<!UNREACHABLE_CODE!><!>, <!UNREACHABLE_CODE!>null<!> -> <!UNREACHABLE_CODE!>""<!>
    <!UNREACHABLE_CODE!>else -> ""<!>
}

// CASE DESCRIPTION: 'When' as statement with values of the subtype of _ClassLevel1.
fun case_3(value: _ClassLevel1, value1: _ClassLevel3, value2: _ClassLevel4): String {
    when (value) {
        _ClassLevel2(), value1 -> return ""
        value2, _ClassLevel3(), _ClassLevel5() -> return ""
        return ""<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>return ""<!>
    }

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' as expression with values of the subtype of nullable _ClassLevel1.
fun case_4(value: _ClassLevel1?, value1: _ClassLevel3, value2: _ClassLevel4): String = when (value) {
    _ClassLevel2(), value1, null -> ""
    value2, _ClassLevel3(), _ClassLevel5() -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' as statement with values of the subtype of Any.
fun case_5(value: Any, value1: _ClassLevel3, value2: _ClassLevel4): String {
    when (value) {
        _ClassLevel2(), value1, "" -> return ""
        -0L, <!SENSELESS_NULL_IN_WHEN!>null<!>, '-', value2 -> return ""
        "...", "1", -.9, Any() -> return ""
        return ""<!UNREACHABLE_CODE!><!>, <!UNREACHABLE_CODE!>throw Exception()<!> -> <!UNREACHABLE_CODE!>return ""<!>
    }

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' as expression with values of the subtype of nullable Any.
fun case_6(value: Any?, value1: _ClassLevel3, value2: _ClassLevel4): String {
    <!UNREACHABLE_CODE!>val <!UNUSED_VARIABLE!>whenValue<!> = when (<!>value<!UNREACHABLE_CODE!>) {<!>
        _ClassLevel2(), value1, "" -> return ""
        -0L, null, '-', value2 -> return ""
        "...", "1", -.9, Any() -> return ""
        return "", <!UNREACHABLE_CODE!>throw Exception() -> return ""
        else -> return ""
    }<!>

    <!UNREACHABLE_CODE!>return whenValue<!>
}