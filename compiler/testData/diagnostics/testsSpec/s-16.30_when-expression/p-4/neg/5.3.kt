/*
 KOTLIN SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 5: Contains test condition: containment operator followed by an expression.
 NUMBER: 3
 DESCRIPTION: When entry with contains operator and object without contains operator.
 */

class A {}

fun test1(value: Int, value1: A): Int {
    when (<!UNUSED_EXPRESSION!>value<!>) {
        <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, TYPE_MISMATCH_IN_RANGE!>in<!> value1  -> return 1
    }

    return -1
}

fun test2(
    value: Int,
    value1: Int,
    value2: Any,
    value3: Nothing,
    <!UNUSED_PARAMETER!>value4<!>: Unit,
    <!UNUSED_PARAMETER!>value5<!>: Map<Int, Int>
): Int {
    when (value) {
        <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, TYPE_MISMATCH_IN_RANGE!>in<!> value1 -> {}
        <!UNRESOLVED_REFERENCE_WRONG_RECEIVER, TYPE_MISMATCH_IN_RANGE!>in<!> value2 -> {}
        <!OVERLOAD_RESOLUTION_AMBIGUITY, TYPE_MISMATCH_IN_RANGE, UNREACHABLE_CODE!>in<!> value3 -> <!UNREACHABLE_CODE!>{}<!>
        <!UNREACHABLE_CODE!><!UNRESOLVED_REFERENCE_WRONG_RECEIVER, TYPE_MISMATCH_IN_RANGE!>in<!> value4 -> {}<!>
        <!UNREACHABLE_CODE!>in value5 -> {}<!>
    }

    return -1
}
