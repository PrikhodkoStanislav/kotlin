// !DIAGNOSTICS: -UNUSED_PARAMETER

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 */

fun withSimpleContainmentOperator(value: Int): Int = when (value) {
    in 1..10 -> 1
    in 11..100 -> 2
    in 101..1000 -> 3
    else -> 4
}

fun withFloatArrayContainmentOperator(value: Float, arrays: List<FloatArray>): Int = when (value) {
    in arrays[0] -> 1
    in arrays[0] -> 2
    in arrays[0] -> 3
    else -> 4
}

class A {
    operator fun contains(a: Float): Boolean {
        return true
    }
}

fun withCustomContainmentOperator(value: Float, arrays: List<A>): Int = when (value) {
    in arrays[0] -> 1
    in arrays[0] -> 2
    in arrays[0] -> 3
    else -> 4
}

fun withListOfContainmentOperator(value: Int): Int {
    when (value) {
        in listOf(1, 9) -> return 1
        in listOf(2, 3) -> return 2
        in listOf(3, 5, 6, 7, 8) -> return 3
        else -> return 4
    }
}

fun withSingleContainmentOperator(value: Int): Int {
    when (value) {
        in Int.MIN_VALUE..Int.MAX_VALUE -> return 1
    }

    return -1
}
