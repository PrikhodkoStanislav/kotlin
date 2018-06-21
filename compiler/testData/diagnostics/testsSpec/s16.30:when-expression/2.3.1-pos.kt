/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 2
 SENTENCE 3: When expression has two different forms: with bound value and without it.
 NUMBER: 1
 DESCRIPTION: Simple (empty) when with bound value.
 */

fun foo(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>) {}
}
