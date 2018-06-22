/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 3
 DESCRIPTION: Simple when without bound value and different variants of the boolean conditions (objects).
 */

open class A {}
open class B: A() {}
open class C: B() {}
object D {}

fun foo(value1: Any, value2: Any, value3: A, value4: B, value5: C, value6: D, value7: Any, value8: Any, value9: Any): Int {
    when {
        value1 === value2 -> return 1
        value3 == value2 -> return 2
        value1 is D -> return 3
        value1 is C -> return 4
        <!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value1 === (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value2 === (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value7 === (value8 === value9)<!>)<!>)<!> -> return 5
        value1 == (value2 == (value7 == (value8 == value9))) -> return 6
        value1 == (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value2 === (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value7 === (value8 == value9)<!>)<!>) -> return 7
        <!USELESS_IS_CHECK!>value4 is A<!> && <!USELESS_IS_CHECK!>value5 is C<!> || <!USELESS_IS_CHECK!>value6 is Any<!> -> return 8
    }

    return -1
}

fun bar(value1: Any, value2: Any, value3: A, value4: B, value5: C, value6: D, value7: Any, value8: Any, value9: Any): Int {
    return when {
        value1 === value2 -> return 1
        value3 == value2 -> return 2
        value1 is D -> return 3
        value1 is C -> return 4
        <!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value1 === (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value2 === (value7 === value8)<!>)<!> -> return 5
        value1 == (value2 == (value7 == value8)) -> return 6
        value1 == (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value2 === (<!IMPLICIT_BOXING_IN_IDENTITY_EQUALS!>value7 === (value8 == value9)<!>)<!>) -> return 7
        <!USELESS_IS_CHECK!>value4 is A<!> && <!USELESS_IS_CHECK!>value5 is C<!> || <!USELESS_IS_CHECK!>value6 is Any<!> -> return 8
        else -> 9
    }
}