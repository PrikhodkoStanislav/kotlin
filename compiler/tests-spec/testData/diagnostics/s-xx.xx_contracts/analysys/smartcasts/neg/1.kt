/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION XX.XX: Contracts
 CATEGORY: analysys, smartcasts
 NUMBER: 1
 DESCRIPTION: Empty 'when' with bound value.
 */

fun case_1(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>) {}
}
