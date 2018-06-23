/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 12
 DESCRIPTION: When with bound value and prefix expressions in when entry.
 */

fun test1(value: Int, value1: Int, value2: Int): Int {
    var value1Mutable = value1
    var value2Mutable = value2

    return when (value) {
        ++value1Mutable -> 1
        --value2Mutable -> 2
        else -> 5
    }
}

fun test2(value: Int, value1: Int, value2: Int): Int {
    var value1Mutable = value1
    var value2Mutable = value2

    when (value) {
        ++value1Mutable -> return 1
        --value2Mutable -> return 2
    }

    return -1
}

fun test3(value: Boolean, value1: Boolean): Int {
    return when (value) {
        !value1 -> 1
        !!!value1 -> 2
        else -> 3
    }
}

fun test4(value: Boolean, value1: Boolean): Int {
    when (value) {
        !value1 -> return 1
        !!!value1 -> return 2
    }

    return -1
}
