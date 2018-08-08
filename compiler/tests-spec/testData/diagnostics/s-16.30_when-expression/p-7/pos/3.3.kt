// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 7
 SENTENCE 3: Contains test condition: containment operator followed by an expression.
 NUMBER: 3
 DESCRIPTION: 'When' with bound value and invert containment operator with range operator.
 */

// CASE DESCRIPTION: 'When' with various integer ranges (not exhaustive).
fun case_1(value: Int, value1: Int, value2: Int, value3: Long, value4: _BasicTypesProvider): String {
    when (value) {
        in Int.MIN_VALUE..-1000000000000L, in 1..10.toShort() -> return ""
        in -1000000000000L..0L, !in 11.toByte()..value1, in 11.toByte()..value1 -> return ""
        !in value1..value2, !in value2..1000, !in value2..getInt(value2) -> return ""
        in getInt(value2)..value4.getLong(value2), !in value4.getLong(value2)..value3 -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with various integer ranges and 'else' branch (exhaustive).
fun case_2(value: Int, value1: Int, value2: Int, value3: Long, value4: _BasicTypesProvider): String = when (value) {
    in Int.MIN_VALUE..-1000000000000L, in -1000000000000L..0L, in 1..10.toShort(), in value2..1000, in 11.toByte()..value1, in getInt(value2)..value4.getLong(value2), in value4.getLong(value2)..value3 -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with various integer ranges and 'else' branch (exhaustive).
fun case_3(value: Int, value1: Int, value2: Int, value3: Long, value4: _BasicTypesProvider): String = when (value) {
    !in Int.MIN_VALUE..-1000000000000L, !in -1000000000000L..0L, !in 1..10.toShort(), !in value2..1000, !in 11.toByte()..value1, !in getInt(value2)..value4.getLong(value2), !in value4.getLong(value2)..value3 -> ""
    else -> ""
}
