// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 5
 SENTENCE 1: The type of the resulting expression is the least upper bound of the types of all the entries.
 NUMBER: 4
 DESCRIPTION: When least upper bound of the types check (when exhaustive via sealed class).
 */

sealed class Expr
data class Const(val number: Int) : Expr()
data class Sum(val e1: Int, val e2: Int) : Expr()
data class Mul(val m1: Int, val m2: Int) : Expr()

sealed class Expr2
object ConstO : Expr2()
object SumO : Expr2()
object MulO : Expr2()

open class A {}
open class B: A() {}
open class C: B() {}
open class D: C() {}
class E: D() {}

fun test1(value: Expr): Int {
    val whenValue = when(value) {
        is Const -> B()
        is Sum -> C()
        is Mul -> D()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test2(value: Expr?): Int {
    val whenValue = when(value) {
        is Const -> B()
        is Sum -> C()
        is Mul -> D()
        null -> E()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><E>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    checkSubtype<E>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test3(value: Expr): Int {
    val whenValue = when(value) {
        is Const -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        is Sum -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        is Mul -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test4(value: Expr?): Int {
    val whenValue = when(value) {
        is Const -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        is Sum -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        is Mul -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
        null -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><() -> Unit>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<() -> Unit>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test5(value: Expr2): Int {
    val whenValue = when(value) {
        ConstO -> B()
        SumO -> C()
        MulO -> D()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test6(value: Expr2?): Int {
    val whenValue = when(value) {
        ConstO -> B()
        SumO -> C()
        MulO -> D()
        null -> E()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><E>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    checkSubtype<E>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test7(value: Expr2): Int {
    val whenValue = when(value) {
        ConstO -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        SumO -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        MulO -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test8(value: Expr2?): Int {
    val whenValue = when(value) {
        ConstO -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        SumO -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        MulO -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
        null -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><Int>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><String>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><() -> Unit>() }
    checkSubtype<Int>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<String>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<() -> Unit>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}

fun test10(value: Expr2?): Int {
    val whenValue = when(value) {
        is Expr2 -> B()
        else -> C()
    }

    whenValue checkType { <!TYPE_MISMATCH!>_<!><E>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><D>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><C>() }
    whenValue checkType { <!TYPE_MISMATCH!>_<!><A>() }
    checkSubtype<E>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<D>(<!TYPE_MISMATCH!>whenValue<!>)
    checkSubtype<C>(<!TYPE_MISMATCH!>whenValue<!>)

    return -1
}