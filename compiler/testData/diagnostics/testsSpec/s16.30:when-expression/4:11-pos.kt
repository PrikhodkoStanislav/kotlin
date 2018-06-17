// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 11: The type of the resulting expression is the [least upper bound][Least upper bound] of the types of all the entries (TODO(): not that simple).
 */

open class A {}
open class B: A() {}
open class C: B() {}
open class D: C() {}
class E: D() {}

fun whenLeastUpperBoundTypeInference(value: Int): Int {
    val whenValue = when {
        value == 0 -> B()
        value > 0 && value <= 10 -> C()
        value > 10 && value <= 100 -> D()
        else -> E()
    }

    whenValue checkType { _<B>() }
    checkSubtype<A>(whenValue)

    return -1
}

fun whenAnyTypeInference(value: Int): Int {
    val whenValue = when {
        value == 0 -> <!IMPLICIT_CAST_TO_ANY!>55<!>
        value > 0 -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        else -> <!IMPLICIT_CAST_TO_ANY!>55F<!>
    }

    whenValue checkType { _<Any>() }
    checkSubtype<Any>(whenValue)

    return -1
}