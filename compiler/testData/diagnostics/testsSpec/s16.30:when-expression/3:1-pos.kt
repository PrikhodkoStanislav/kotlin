/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 */

fun int(value: Int?): Int {
    when {
        value == null -> return 0
        value == 1 -> return 1
        value == 2 -> return 2
        <!DEBUG_INFO_SMARTCAST!>value<!> > 2 && <!DEBUG_INFO_SMARTCAST!>value<!> <= 10 -> return 3
        value == 11 -> return 4
        <!DEBUG_INFO_SMARTCAST!>value<!> > 11 -> return 5
        <!DEBUG_INFO_SMARTCAST!>value<!> > -4 || <!DEBUG_INFO_SMARTCAST!>value<!> < -100 && <!DEBUG_INFO_SMARTCAST!>value<!> > -1000 || value == 11 -> return 7
        value != -3 && value != -4 && value != -5 -> return 8
        <!DEBUG_INFO_SMARTCAST!>value<!> > -3 -> return 9
        else -> return 10
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun bool(value: Boolean): Int {
    when {
        value -> return 1
        !value -> return 2
    }

    return -1
}

fun nullableBool(value: Boolean?): Int {
    when {
        value == true -> return 1
        value == false -> return 2
        else -> return 3
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun nullableBoolSmartCast(value: Boolean?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!> -> return 1
        !<!DEBUG_INFO_SMARTCAST!>value<!> -> return 2
    }

    return -1
}

fun string(value: String?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!>.isEmpty() -> return 1
        value == "a" || value == "b" -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun float(value: Float?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!> < 10.5F -> return 1
        <!DEBUG_INFO_SMARTCAST!>value<!> > 10.6F -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun double(value: Double?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!> < 10.51235 -> return 1
        <!DEBUG_INFO_SMARTCAST!>value<!> > 10.69931 -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun long(value: Long?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!> < 8128491894234244849L -> return 1
        <!DEBUG_INFO_SMARTCAST!>value<!> > 8128491894234244865L -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun typeCheck(value: Any?): Int {
    when {
        value is String -> return 0
        value is Int -> return 1
        value is Double -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun rangeTest(value: Int?): Int {
    when {
        value in 0..10 -> return 0
        value in 11..100 -> return 1
        value in 101..1000 -> return 2
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun boolConsts(): Int {
    when {
        1 == 2 -> { return 0 }
        1 == 1 && 1 >= 5 -> { return 1 }
        true && false -> { return 2 }
        false || !true -> { return 3 }
        false && false && true -> { return 4 }
        ((true)) && (false) || ((false)) -> { return 5 }
        true == true -> { return 6 }
    }

    return -1
}
