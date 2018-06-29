/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value and containment operator on the classes with contains operator defined.
 */

class A {
    operator fun contains(a: Int): Boolean {
        return a > 30
    }

    fun getIntArray(value: Int): IntArray {
        return intArrayOf(1, 2, 3, value, 91923, 14, 123124)
    }
}

// CASE DESCRIPTION: 'When' with contains operator on the classes with contains operator defined.
fun case_1(value: Int, value1: List<IntArray>, value2: A, value3: IntRange): Int {
    when (value) {
        in value1[0] -> return 1
        in value1[10] -> return 2
        in listOf(3, 5, 6, 7, 8) -> return 3
        in value2 -> return 4
        in value3 -> return 5
        in value2.getIntArray(90000) -> return 6
    }

    return -1
}

// CASE DESCRIPTION: 'When' with contains operator on the classes with contains operator defined, and 'else' branch.
fun case_2(value: Int, value1: List<IntArray>, value2: A): Int = when (value) {
    in value1[0] -> 1
    in value1[10] -> 2
    in listOf(3, 5, 6, 7, 8) -> 3
    in value2 -> 4
    in value2.getIntArray(90000) -> 5
    else -> 6
}

// CASE DESCRIPTION: 'When' with one contains operator on the class with contains operator defined.
fun case_3(value: Int, value1: A): Int {
    when (value) {
        in value1.getIntArray(90000) -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with one contains operator on the class with contains operator defined, and 'else' branch.
fun case_4(value: Int, value1: A): Int = when (value) {
    in value1.getIntArray(90000) -> 1
    else -> 2
}