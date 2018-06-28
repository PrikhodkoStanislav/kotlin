/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 4
 DESCRIPTION: Simple when without bound value and different variants of the boolean conditions (boolean literals and values).
 */

fun isShortString(str: String): Boolean {
    return str.toString().length < 3
}

fun isString(value: Any): Boolean {
    return value is String
}

class A {
    fun method1(str: String): Boolean {
        return str.toString().length >= 4
    }
    fun method2(value: Any): Boolean {
        return value is String
    }
}

fun foo(value1: A): Int {
    when {
        false || false && true || ((((true)))) -> return 1
        ((value1.method1("some string"))) || true -> return 2
        value1.method2("") && ((((false)))) -> return 3
        value1.method2("") || !!!!!!isShortString("++++") && isString("") -> return 4
        value1.method2("") -> return 5
        true && true && true && !!!true && true -> return 6
        false || false || false || !!!false || false -> return 7
    }

    return -1
}

fun bar(value1: A): Int {
    return when {
        false || false && true || ((((true)))) -> return 1
        ((value1.method1("some string"))) || true -> return 2
        value1.method2("") && ((((false)))) -> return 3
        value1.method2("") || !!!!!!isShortString("++++") && isString("") -> return 4
        value1.method2("") -> return 5
        true && true && true && !!!true && true -> return 6
        false || false || false || !!!false || false -> return 7
        else -> 8
    }
}