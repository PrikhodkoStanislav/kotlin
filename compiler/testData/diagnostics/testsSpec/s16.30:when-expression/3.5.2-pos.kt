/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 5: The else branch is a special branch that evaluates if none of the branches above it evaluated to true.
 NUMBER: 2
 DESCRIPTION: Simple when without bound value and only one else branch.
 */

fun foo(): Int = when {
    else -> 1
}