/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 2
 DESCRIPTION: When entry with range, but wuthout contains operator.
 */

fun test1(value: Int): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return 1
    }

    return -1
}

fun test2(value: Int): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        in<!SYNTAX!><!> -> return 1
        in<!SYNTAX!><!> -> return 2
    }

    return -1
}
