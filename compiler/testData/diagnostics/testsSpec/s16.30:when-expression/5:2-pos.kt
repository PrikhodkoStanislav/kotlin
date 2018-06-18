/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 5
 SENTENCE 2: It has an else entry;
 */

fun simpleWithElse(value: Int): Int = when {
    value == 0 -> 1
    value > 0 && value <= 10 -> 2
    value > 10 && value <= 100 -> 3
    value > 100 -> 4
    else -> 5
}

fun withOnlyElse(): Int = when {
    else -> 5
}

fun withElseAndTrue(): Int = when {
    true -> 4
    else -> 5
}

fun simpleWithElseAndBoundValue(value: Int): Int = when(value) {
    0 -> 1
    1 -> 2
    2 -> 3
    3 -> 4
    else -> 5
}

fun withOnlyElseAndBoundValue(value: Int): Int = when(<!UNUSED_EXPRESSION!>value<!>) {
    else -> 5
}

fun withElseAndTrueAndBoundValue(): Int = when(true) {
    true -> 4
    else -> 5
}
