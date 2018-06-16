// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 4: The condition generated is a type check expression with the same operator and the same type, but an implicit left hand side, which has the same value as the bound expression.
 */

fun withSimpleTypeCheckOperator(value: Any): Boolean {
    val whenWithBoundValue = when (value) {
        is Int -> 1
        is Float -> 2
        is Double -> 3
        else -> 4
    }

    val whenWithoutBoundValue = when { // the same as when with bound value
        value is Int -> 1
        value is Float -> 2
        value is Double -> 3
        else -> 4
    }

    whenWithBoundValue checkType { _<Int>() }
    whenWithoutBoundValue checkType { _<Int>() }

    return whenWithBoundValue == whenWithoutBoundValue // must be true
}