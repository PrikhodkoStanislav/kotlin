// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 1: The type of the resulting expression is the least upper bound of the types of all the entries.
 NUMBER: 1
 DESCRIPTION: When least upper bound of the types check (when exhaustive via else branch).
 */

open class A {}
open class B: A() {}
open class C: B() {}
open class D: C() {}
class E: D() {}

fun test1(value: Int): Int {
    val whenValue = when {
        value == 0 -> B()
        value > 0 && value <= 10 -> C()
        value > 10 && value <= 100 -> D()
        else -> E()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><E>() }
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<E>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test2(value: Int): Int {
    val whenValue = when(value) {
        0 -> B()
        1 -> C()
        2 -> D()
        else -> E()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><E>() }
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<E>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test3(value: Int): Int {
    val whenValue = when {
        value == 0 -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        value > 0 && value <= 10 -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        value > 10 && value <= 100 -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        else -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><() -> Unit>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<() -> Unit>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test4(value: Int): Int {
    val whenValue = when(value) {
        0 -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        1 -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        2 -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        else -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><() -> Unit>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<() -> Unit>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}
