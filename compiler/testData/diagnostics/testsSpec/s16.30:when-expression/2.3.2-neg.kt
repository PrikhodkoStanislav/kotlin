/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 2
 SENTENCE 3: When expression has two different forms: with bound value and without it.
 NUMBER: 2
 DESCRIPTION: Simple (empty) when with missed when entries section.
 */

fun test1(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>)<!SYNTAX!><!>
}

fun test2() {
    when (<!SYNTAX!><!>)<!SYNTAX!><!>
}

fun test3() {
    when<!SYNTAX!><!>
}
