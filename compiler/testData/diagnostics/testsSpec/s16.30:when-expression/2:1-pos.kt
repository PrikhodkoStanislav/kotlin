/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 2
 SENTENCE 1: When expression is alike a conditional expression in the sense that it allows several different control structure bodies (cases) to be evaluated depending on boolean conditions.
 */

/*
 The same as the following conditional expression:

 if (value == 0) {
    return 1
 } else if (value > 0 && value <= 10) {
    return 2
 } else if (value > 10 && value <= 100) {
    return 3
 } else if (value > 100) {
    return 4
 }
 */
fun whenBoolConditionsTest(value: Int): Int {
    when {
        value == 0 -> return 1
        value > 0 && value <= 10 -> return 2
        value > 10 && value <= 100 -> return 3
        value > 100 -> return 4
    }

    return -1
}

/*
 The same as the following conditional expression:

 if (value == 0) {
    return 1
 } else if (value > 0 && value <= 10) {
    return 2
 } else if (value > 10 && value <= 100) {
    return 3
 } else {
    return 4
 }
 */
fun whenBoolConditionsWithElseTest(value: Int): Int {
    when {
        value == 0 -> return 1
        value > 0 && value <= 10 -> return 2
        value > 10 && value <= 100 -> return 3
        else -> return 4
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 The same as the following conditional expression:

 if (value) {
    return 1
 } else {
    return 2
 }
 */
fun whenBoolConditionsOnBoolVarTest(value: Boolean): Int {
    when {
        value -> return 1
        else -> return 2
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

/*
 The same as the following conditional expression:

 if (!value) {
    return 1
 }
 */
fun whenSingleBoolConditionOnBoolVarTest(value: Boolean): Int {
    when {
        !value -> return 1
    }

    return -1
}
