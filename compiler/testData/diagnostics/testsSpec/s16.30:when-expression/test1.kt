/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: When entry with type, but wuthout type checking operator.
 */

fun test1(value: Any): Int {
    when (value) {
        Int -> return 1
        Float -> return 2
        Double -> return 3
        String -> return 4
        Char -> return 5
        Boolean -> return 6
    }

    return -1
}

fun test2(value: Any): Int {
    when (value) {
        Any -> return 1
    }

    return -1
}
