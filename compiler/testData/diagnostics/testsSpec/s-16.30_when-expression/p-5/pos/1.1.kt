// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 1: The type of the resulting expression is the least upper bound of the types of all the entries.
 NUMBER: 1
 DESCRIPTION: 'When' least upper bound of the types check (when exhaustive via else branch).
 */

open class A {}
open class B: A() {}
open class C: B() {}
open class D: C() {}
class E: D() {}

// CASE DESCRIPTION: Checking correctness type (custom types) in 'when' without bound value.
fun case_1(value: Int): String {
    val whenValue = when {
        value == 0 -> B()
        value > 0 && value <= 10 -> C()
        value > 10 && value <= 100 -> D()
        else -> E()
    }

    whenValue checkType { _<B>() }
    checkSubtype<A>(whenValue)

    return ""
}

// CASE DESCRIPTION: Checking correctness type (custom types) in 'when' with bound value.
fun case_2(value: Int): String {
    val whenValue = when(value) {
        0 -> B()
        1 -> C()
        2 -> D()
        else -> E()
    }

    whenValue checkType { _<B>() }
    checkSubtype<A>(whenValue)

    return ""
}

// CASE DESCRIPTION: Checking Any type (implicit cast to any) in 'when' without bound value.
fun case_3(value: Int): String {
    val whenValue = when {
        value == 0 -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        value > 0 && value <= 10 -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        value > 10 && value <= 100 -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        else -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { _<Any>() }
    checkSubtype<Any>(whenValue)

    return ""
}

// CASE DESCRIPTION: Checking Any type (implicit cast to any) in 'when' with bound value.
fun case_4(value: Int): String {
    val whenValue = when(value) {
        0 -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        1 -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        2 -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        else -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { _<Any>() }
    checkSubtype<Any>(whenValue)

    return ""
}
