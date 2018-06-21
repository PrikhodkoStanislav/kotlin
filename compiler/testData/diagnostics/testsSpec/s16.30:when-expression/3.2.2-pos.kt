/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: Simple when without bound value and different variants of the boolean conditions (string and char).
 */

fun foo(value1: String, value2: Char): Int {
    when {
        value1.isEmpty() -> return 1
        value1 == "..." || value1 == ":::" -> return 2
        value1.equals("-") -> return 3
        value2 == '_' -> return 4
        value2 > 10.toChar() -> return 5
        value2.equals('+') -> return 6
    }

    return -1
}

fun bar(value1: String, value2: Char): Int {
    return when {
        value1.isEmpty() -> 1
        value1 == "..." || value1 == ":::" -> 2
        value1.equals("-") -> 3
        value2 == '_' -> 4
        value2 > 10.toChar() -> 5
        value2.equals('+') -> 6
        else -> 7
    }
}