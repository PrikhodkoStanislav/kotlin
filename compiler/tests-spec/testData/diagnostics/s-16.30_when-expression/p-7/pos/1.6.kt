// !WITH_CLASSES
// !WITH_SEALED_CLASSES
// !WITH_OBJECTS
// !WITH_TYPEALIASES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 7
 SENTENCE 1: Type test condition: type checking operator followed by type.
 NUMBER: 6
 DESCRIPTION: 'When' with bound value and enumaration of type test conditions.
 */

// CASE DESCRIPTION: 'When' with type test condition on the various basic types.
fun case_1(value: Any): String {
    when (value) {
        is Int -> return ""
        is Float, is Char, is Boolean -> return ""
        is String -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types.
fun case_2(value: Any?): String = when (value) {
    is Float, is Char, is Int? -> "" // if value is null then this branch will be executed
    is Double, is Boolean, is String -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_3(value: Any?): String = when (value) {
    is Float, is Char, is Int? -> "" // if value is null then this branch will be executed
    is Double, is Boolean?, is String -> "" // contain redundant (second) nullable type check
    else -> ""
}

// CASE DESCRIPTION: 'When' with type test condition on the all possible subtypes of the sealed class.
fun case_4(value: _SealedClass): String = when (value) {
    is _SealedChild1 -> ""
    is _SealedChild2, is _SealedChild3 -> ""
}

// CASE DESCRIPTION: 'When' with type test condition on the all possible subtypes of the sealed class.
fun case_5(value: _SealedClass?): String = when (value) {
    is _SealedChild1 -> ""
    is _SealedChild2?, is _SealedChild3 -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with type test condition on the all possible subtypes of the sealed class (two nullable type check).
fun case_6(value: _SealedClass?): String = when (value) {
    is _SealedChild1? -> "" // if value is null then this branch will be executed
    is _SealedChild2, is _SealedChild3? -> "" // contain redundant (second) nullable type check
    else -> ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_7(value: Any): String {
    when (value) {
        is _TypeAliasInt, <!USELESS_IS_CHECK!>is _TypeAliasAny<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_8(value: Any?): String {
    when (value) {
        null, is _TypeAliasInt, <!USELESS_IS_CHECK!>is _TypeAliasAny<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_9(value: Any): String {
    when (value) {
        is _ClassWithCompanionObject.Companion, is _EmptyObject -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_10(value: Any?): String {
    when (value) {
        is _ClassWithCompanionObject.Companion, null, is _EmptyObject -> return ""
    }

    return ""
}