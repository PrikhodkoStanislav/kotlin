// !WITH_CLASSES
// !WITH_OBJECTS

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 7
 SENTENCE 1: Type test condition: type checking operator followed by type.
 NUMBER: 4
 DESCRIPTION: 'When' with custom object and companion object of class as type checking operator value.
 */

fun case_1(value: Any): String {
    when (value) {
        is _EmptyObject -> return ""
        is _ClassWithCompanionObject.Companion -> return ""
    }

    return ""
}