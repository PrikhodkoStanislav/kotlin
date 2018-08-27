// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [3] Contains test condition: containment operator followed by an expression.
 NUMBER: 1
 DESCRIPTION: 'When' with bound value_1 and 'when condition' with range expression, but without containment checking operator.
 */

fun case_1(value_1: Int, value_1: _BasicTypesProvider): String {
    when (value_1) {
        <!INCOMPATIBLE_TYPES!>-1000L..100<!> -> return ""
        <!INCOMPATIBLE_TYPES!>value_1.getInt()..getLong()<!> -> return ""
    }

    return ""
}
