/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: When invalid else condition
 */

fun test1() {
    when {
        else -><!SYNTAX!><!>
    }
}

fun test2() {
    when {
        else -><!SYNTAX!><!>
        else -><!SYNTAX!><!>
    }
}

fun test3(value: Int) {
    when {
        value == 1 -> println("1")
        value == 2 -> println("2")
        else -><!SYNTAX!><!>
    }
}

fun test4(value: Int) {
    when {
        value == 1 -> println("!")
        else -><!SYNTAX!><!>
    }
}