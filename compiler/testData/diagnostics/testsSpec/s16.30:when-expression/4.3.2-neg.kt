/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 3: Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: When entry with missed type.
 */

fun test1(value: Any): Int {
    when (value) {
        is<!SYNTAX!><!> -> return 1
    }

    return -1
}

fun test2(value: Any): Int {
    when (value) {
        is<!SYNTAX!><!> -> return 1
        is<!SYNTAX!><!> -> return 2
    }

    return -1
}
