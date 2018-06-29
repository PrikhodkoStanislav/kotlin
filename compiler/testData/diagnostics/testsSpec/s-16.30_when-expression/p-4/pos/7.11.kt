/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 11
 DESCRIPTION: 'When' with bound value and cast expressions in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression).
fun case_1(value: Collection<Int>?, value1: Collection<Int>, value2: Collection<Int>?): Int = when (value) {
    value1 as MutableList<Int> -> 1
    value2 <!UNCHECKED_CAST!>as? MutableMap<Int, Int><!> -> 2
    (value2 <!UNCHECKED_CAST!>as? Map<Int, Int><!>) as MutableMap<Int, Int> -> 3
    null -> 4
    else -> 5
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement).
fun case_2(value: Collection<Int>?, value1: Collection<Int>, value2: Collection<Int>?): Int {
    when (value) {
        value1 as MutableList<Int> -> return 1
        value2 <!UNCHECKED_CAST!>as? MutableMap<Int, Int><!> -> return 2
        (value2 <!UNCHECKED_CAST!>as? Map<Int, Int><!>) as MutableMap<Int, Int> -> return 3
        null -> return 4
    }

    return -1
}
