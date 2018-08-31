/*
 KOTLIN PSI SPEC TEST (NEGATIVE)

 SECTION: when-expression
 PARAGRAPH: 3
 SENTENCE: [2] Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value_1 and with invalid list of the boolean conditions in 'when entry'.
 */

// CASE DESCRIPTION: 'When' with list of expressions and an extra comma at the end.
fun case_1(value_1: Int, value_2: Any, value_3: IntRange): String {
    when {
        value_1 == 21, -> return ""
        value_2 is Int, value_2 is String, -> return ""
        value_1 in -100..100, value_1 in value_3, -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with list of expressions and an double comma.
fun case_2(value_1: Int, value_2: Any, value_3: IntRange): String {
    when {
        value_1 == 21, , -> return ""
        value_2 is Int, ,value_2 is String -> return ""
        value_1 in -100..100, ,value_1 in value_3 -> return ""
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
fun case_4(value_1: Int, value_2: Any, value_3: IntRange): String {
    when {
        , value_1 == 21 -> return ""
        , value_2 is Int, value_2 is String -> return ""
        , value_1 in -100..100, value_1 in value_3 -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with list of expressions and missed comma.
fun case_5(value_1: Int, value_2: Any): String {
    when {
        value_2 is Int value_2 is String -> return ""
        value_1 in -100..100 value_1 in value_3 -> return ""
    }

    return ""
}
