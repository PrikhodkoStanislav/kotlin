/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value and containment operator with range operator.
 */

fun getInt(number: Int): Int {
    return number + 11
}

class A {
    fun method1(number: Int): Long {
        return (number * 100000).toLong()
    }
}

// CASE DESCRIPTION: 'When' with various integer ranges (not exhaustive).
fun case_1(value: Int, value1: Int, value2: Int, value3: Long, value4: A): Int {
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

// CASE DESCRIPTION: 'When' with various integer ranges and 'else' branch (exhaustive).
fun case_2(value: Int, value1: Int, value2: Int, value3: Long, value4: A): Int = when (value) {
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

// CASE DESCRIPTION: 'When' with one integer range (not exhaustive).
fun case_3(value: Int): Int {
    when (value) {
        in Int.MIN_VALUE..Int.MAX_VALUE -> return 1
    }

    return -1
}

// CASE DESCRIPTION: 'When' with one integer range and 'else' branch (exhaustive).
fun case_4(value: Int): Int = when (value) {
    in Int.MIN_VALUE..Int.MAX_VALUE -> 1
    else -> 2
}