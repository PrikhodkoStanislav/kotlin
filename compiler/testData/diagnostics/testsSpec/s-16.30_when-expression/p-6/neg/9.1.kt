// !CHECK_TYPE

/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 6
 SENTENCE 9: The bound expression is of a nullable type and one of the cases above is met for its non-nullable counterpart and, in addition, there is a condition containing literal null.
 NUMBER: 1
 DESCRIPTION: Checking for not exhaustive when when contains by all Boolean values, bot no null check (or with no null check, but not contains by all Boolean values).
 */

fun test1(value: Boolean?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    true -> 1
    false -> 2
}

fun test2(value: Boolean?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    true -> 1
    null -> 2
}

fun test3(value: Boolean?): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) { }
