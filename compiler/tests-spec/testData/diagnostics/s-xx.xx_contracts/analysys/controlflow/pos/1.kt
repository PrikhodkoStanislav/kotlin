/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (POSITIVE)

 SECTION XX.XX: Contracts
 CATEGORY: analysys, controlflow
 NUMBER: 1
 DESCRIPTION: Empty 'when' with bound value.
 */

fun case_1(value: Int) {
    when (<!UNUSED_EXPRESSION!>value<!>) {}
}
