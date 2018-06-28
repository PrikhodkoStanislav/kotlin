/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 9: The else condition, which works the exact same way as it would in the form without bound expression.
 NUMBER: 1
 DESCRIPTION: When with invalid else condition
 */

fun test1(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        else -><!SYNTAX!><!>
    }
}

fun test2(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        else -><!SYNTAX!><!>
        else -><!SYNTAX!><!>
    }
}

fun test3(value: Int) {
    when (value) {
        1 -> println("1")
        2 -> println("2")
        else -><!SYNTAX!><!>
    }
}

fun test4(value: Int) {
    when (value) {
        1 -> println("!")
        else -><!SYNTAX!><!>
    }
}