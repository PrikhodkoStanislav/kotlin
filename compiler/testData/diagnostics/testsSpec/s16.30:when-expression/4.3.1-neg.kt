/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 1
 DESCRIPTION: When entry with type (without companion object), but wuthout type checking operator.
 */

class A {}

fun test1(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>A<!> -> return 1
    }

    return -1
}

fun test2(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>Any<!> -> return 1
    }

    return -1
}

fun test3(value: Any): Int {
    when (value) {
        <!NO_COMPANION_OBJECT!>Nothing<!> -> return 1
    }

    return -1
}
