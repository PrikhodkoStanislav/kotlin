// !WITH_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [1] Type test condition: type checking operator followed by type.
 NUMBER: 2
 DESCRIPTION: 'When' with bound value_1 and type test condition on the non-type operand of the type checking operator.
 */

fun case_1(value_1: Any, <!UNUSED_PARAMETER!>value_1<!>: Int): String {
    when (value_1) {
        is <!UNRESOLVED_REFERENCE!>value_1<!> -> return ""
    }

    return ""
}
