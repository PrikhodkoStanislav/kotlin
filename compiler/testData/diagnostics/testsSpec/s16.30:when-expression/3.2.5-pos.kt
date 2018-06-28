/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 5
 DESCRIPTION: Simple when without bound value and only one else branch.
 */

fun test1(): Int = when {
    else -> 1
}

fun test2(): Int {
    when {
        else -> {return 1}
    }
}