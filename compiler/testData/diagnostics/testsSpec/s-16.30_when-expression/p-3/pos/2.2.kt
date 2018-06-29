/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value and different variants of the boolean conditions (String and Char).
 */

fun getString(char: Char): String {
    return char.toString()
}

fun isString(value: Any): Boolean {
    return value is String
}

class A {
    fun method_1(char: Char): String {
        return char.toString()
    }
    fun method_2(value: Any): Boolean {
        return value is String
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch.
fun case_1(value1: String, value2: Char, value3: A): Int {
    when {
        value1.isEmpty() -> return 1
        value1 == "..." || value1 == ":::" -> return 2
        value1.equals("-") -> return 3
        value2 == '_' -> return 4
        value2 > 10.toChar() -> return 5
        value2.equals('+') -> return 6
        getString('a') == "A" || getString('+') == "+" -> return 7
        value3.method_1('-') == "_" || value3.method_1('/') == "\\" -> return 8
        isString("a") || isString('a') -> return 9
        value3.method_2("a") || value3.method_2('a') -> return 10
    }

    return -1
}

// CASE DESCRIPTION: 'When' with 'else' branch.
fun case_2(value1: String, value2: Char, value3: A): Int {
    return when {
        value1.isEmpty() -> 1
        value1 == "..." || value1 == ":::" -> 2
        value1.equals("-") -> 3
        value2 == '_' -> 4
        value2 > 10.toChar() -> 5
        value2.equals('+') -> 6
        getString('a') == "A" || getString('+') == "+" -> return 7
        value3.method_1('-') == "_" || value3.method_1('/') == "\\" -> return 8
        isString("a") || isString('a') -> return 9
        value3.method_2("a") || value3.method_2('a') -> return 10
        else -> 11
    }
}