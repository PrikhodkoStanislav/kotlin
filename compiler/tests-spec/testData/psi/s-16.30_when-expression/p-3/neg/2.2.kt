/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [2] Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value and with invalid list of the boolean conditions in 'when entry'.
 */

// CASE DESCRIPTION: 'When' with list of expressions and an extra comma at the end.
fun case_1(value1: Int, value2: Any, value3: IntRange): String {
    when {
        value1 == 21, -> return ""
        value2 is Int, value2 is String, -> return ""
        value1 in -100..100, value in value3, -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with list of expressions and an double comma.
fun case_2(value1: Int, value2: Any, value3: IntRange): String {
    when {
        value1 == 21, , -> return ""
        value2 is Int, ,value2 is String -> return ""
        value1 in -100..100, ,value in value3 -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with empty list of expressions and with double comma.
fun case_3(): String {
    when {
        , , -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with list of expressions and a comma at the beginning.
fun case_4(value1: Int, value2: Any, value3: IntRange): String {
    when {
        , value1 == 21 -> return ""
        , value2 is Int, value2 is String -> return ""
        , value1 in -100..100, value in value3 -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with list of expressions and missed comma.
fun case_5(value1: Int, value2: Any): String {
    when {
        value2 is Int value2 is String -> return ""
        value1 in -100..100 value in value3 -> return ""
    }

    return ""
}
