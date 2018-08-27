// !WITH_BASIC_TYPES
// !WITH_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [3] Contains test condition: containment operator followed by an expression.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value_1 and enumeration of the containment operators.
 */

// CASE DESCRIPTION: 'When' with range operator.
fun case_1(value_1: Int, value_1: Int, value_2: Short): String {
    when (value_1) {
        in Long.MIN_VALUE..-100, in -99..0 -> return ""
        !in 100.toByte()..value_1, in value_1..value_2 -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' on types with contains method defined.
fun case_2(value_1: Int, value_1: List<IntArray>, value_2: _Class) = when (value_1) {
    !in value_1[0], !in listOf(0, 1, 2, 3, 4), !in value_2.getIntArray(90) -> ""
    else -> ""
}