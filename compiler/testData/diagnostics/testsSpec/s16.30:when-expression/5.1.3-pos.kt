// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 1: The type of the resulting expression is the least upper bound of the types of all the entries.
 NUMBER: 3
 DESCRIPTION: When least upper bound of the types check (when exhaustive via boolean bound value).
 */

open class A {}
open class B: A() {}
open class C: B() {}
class D: C() {}

fun test1(value: Boolean): Int {
    val whenValue = when(value) {
        true -> B()
        false -> C()
    }

    whenValue checkType { _<B>() }
    checkSubtype<A>(whenValue)

    return -1
}

fun test2(value: Boolean?): Int {
    val whenValue = when(value) {
        true -> B()
        false -> C()
        null -> D()
    }

    whenValue checkType { _<B>() }
    checkSubtype<A>(whenValue)

    return -1
}

fun test3(value: Boolean): Int {
    val whenValue = when(value) {
        true -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        false -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { _<Any>() }
    checkSubtype<Any>(whenValue)

    return -1
}

fun test4(value: Boolean?): Int {
    val whenValue = when(value) {
        true -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        false -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        null -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { _<Any>() }
    checkSubtype<Any>(whenValue)

    return -1
}


