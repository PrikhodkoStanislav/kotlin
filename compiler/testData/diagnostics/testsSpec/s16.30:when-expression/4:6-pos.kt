// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 6: The condition generated is a containment check expression with the same operator and the same right hand side expression, but an implicit left hand side, which has the same value as the bound expression.
 */

fun withSimpleContainmentOperator(value: Any): Boolean {
    val whenWithBoundValue = when (value) {
        in 1..10 -> 1
        in 11..100 -> 2
        in 101..1000 -> 3
        else -> 4
    }

    val whenWithoutBoundValue = when { // the same as when with bound value
        value in 1..10 -> 1
        value in 11..100 -> 2
        value in 101..1000 -> 3
        else -> 4
    }

    whenWithBoundValue checkType { _<Int>() }
    whenWithoutBoundValue checkType { _<Int>() }

    return whenWithBoundValue == whenWithoutBoundValue // must be true
}