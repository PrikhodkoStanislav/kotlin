/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 1
 DESCRIPTION: Simple when with bound value and containment operator with range operator.
 */

fun getInt(number: Int): Int {
    return number + 11
}

class A {
    fun method1(number: Int): Long {
        return (number * 100000).toLong()
    }
}

fun test1(value: Int, value1: Int, value2: Int, value3: Long, value4: A): Int {
    when (value) {
        in Int.MIN_VALUE..-1000000000000L -> return 1
        in -1000000000000L..0L -> return 2
        in 1..10.toShort() -> return 3
        in 11.toByte()..value1 -> return 4
        in value1..value2 -> return 5
        in value2..1000 -> return 6
        in value2..getInt(value2) -> return 7
        in getInt(value2)..value4.method1(value2) -> return 8
        in value4.method1(value2)..value3 -> return 9
    }

    return -1
}

fun test2(value: Int, value1: Int, value2: Int, value3: Long, value4: A): Int = when (value) {
    in Int.MIN_VALUE..-1000000000000L -> 1
    in -1000000000000L..0L -> 2
    in 1..10.toShort() -> 3
    in 11.toByte()..value1 -> 4
    in value1..value2 -> 5
    in value2..1000 -> 6
    in value2..getInt(value2) -> 7
    in getInt(value2)..value4.method1(value2) -> 8
    in value4.method1(value2)..value3 -> 9
    else -> 10
}

fun test3(value: Int): Int {
    when (value) {
        in Int.MIN_VALUE..Int.MAX_VALUE -> return 1
    }

    return -1
}

fun test4(value: Int): Int = when (value) {
    in Int.MIN_VALUE..Int.MAX_VALUE -> 1
    else -> 2
}

fun test5(value: Any?): Int = when (value) {
    in Int.MIN_VALUE..Int.MAX_VALUE -> 1
    else -> 2
}