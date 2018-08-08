// !WITH_BASIC_TYPES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 2: Each entry consists of a boolean condition (or a special else condition), each of which is checked and evaluated in order of appearance.
 NUMBER: 2
 DESCRIPTION: 'When' without bound value and not allowed (deprecated) comma in when entry.
 */

// CASE DESCRIPTION: 'When' with enumeration of the boolean expressions (exactly Boolean).
fun case_1(value1: _BasicTypesProvider): String {
    when {
        !!!!!!getBoolean("4") && getBoolean("5")<!COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT!>,<!> value1.getBoolean("6")  -> return ""
        value1.getBoolean("6")<!COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT!>,<!> getList() === listOf(1, 2, 3) -> return ""
        getList() == arrayListOf(1, 2, 3)<!COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT!>,<!> getLong("1") == 1000L -> return ""
        ((((false))))<!COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT!>,<!> !!!true && true -> return ""
        true && true && true && !!!true && true<!COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT!>,<!> getBoolean("5") -> return ""
    }

    return ""
}
