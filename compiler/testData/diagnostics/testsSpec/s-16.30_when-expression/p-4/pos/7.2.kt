/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 2
 DESCRIPTION: When with bound value and logical expressions in when entry.
 */

fun foo1(value: Boolean): Int = when (value) {
    true && false || !!!!true -> 1
    true && !!!true && (!!!false || true) -> 2
}

fun bar1(value: Boolean): Int = when (value) {
    true && false || !!false -> 1
    true && !!!!true && (!!!false || true) -> 2
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> 3
}

fun foo2(value: Boolean, value1: Boolean, value2: Boolean, value3: Boolean): Int {
    when (value) {
        value1 && value2 || !!!value3 -> return 1
        value2 && !!value1 && (!!!value3 || value1) -> return 2
    }

    return -1
}

fun bar2(value: Boolean, value1: Boolean, value2: Boolean, value3: Boolean): Int = when (value) {
    value1 && value2 || !!!value3 -> 1
    value2 && !!value1 && (!!!value3 || value1) -> 2
    else -> 3
}
