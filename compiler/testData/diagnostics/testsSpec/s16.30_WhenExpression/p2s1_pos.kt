/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 2
 SENTENCE 1: When expression is alike a conditional expression in the sense that it allows several different control structure bodies (cases) to be evaluated depending on boolean conditions.
 */

fun whenIntTest(value: Int?): Int {
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
        true -> return 10
    }

    return -1
}

fun whenBoolTest(value: Boolean): Int {
    when {
        value -> return 1
        !value -> return 2
    }

    return -1
}

fun whenNullableBoolTest(value: Boolean?): Int {
    when {
        value == true -> return 1
        value == false -> return 2
        true -> return 3
    }

    return -1
}

fun whenNullableBoolSmartCastTest(value: Boolean?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!> -> return 1
        !<!DEBUG_INFO_SMARTCAST!>value<!> -> return 2
    }

    return -1
}

fun whenStringTest(value: String?): Int {
    when {
        value == null -> return 0
        <!DEBUG_INFO_SMARTCAST!>value<!>.isEmpty() -> return 1
        value == "a" || value == "b" -> return 2
        false -> return 3
        true -> return 4
    }

    return -1
}

fun whenBoolConstsTest(): Int {
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
