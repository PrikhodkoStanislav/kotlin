// !WITH_SEALED_CLASSES
// !WITH_CLASSES
// !WITH_OBJECTS
// !WITH_TYPEALIASES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 7
 SENTENCE: [1] Type test condition: type checking operator followed by type.
 NUMBER: 5
 DESCRIPTION: 'When' with bound value_1 and type test condition (invert type checking operator).
 */

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_1(value_1: _SealedClass): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3<!> -> ""
}

// CASE DESCRIPTION: 'When' with three invert type checking operators on the all sybtypes of the sealed class.
fun case_2(value_1: _SealedClass): String {
    <!DEBUG_INFO_IMPLICIT_EXHAUSTIVE!>when (value_1) {
        !is _SealedChild1 -> return ""
        !is _SealedChild2 -> return ""
        !is _SealedChild3 -> return ""
    }<!>

    <!UNREACHABLE_CODE!>return ""<!>
}

// CASE DESCRIPTION: 'When' with direct and invert type checking operators on the same subtype of thee sealed class.
fun case_3(value_1: _SealedClass): String = when (value_1) {
    is _SealedChild2 -> ""
    !is _SealedChild2 -> ""
}

// CASE DESCRIPTION: 'When' with direct (in the second position) and invert (in the first position) type checking operators on the same subtype of the sealed class, and 'else' branch (redundant).
fun case_6(value_1: _SealedClass): String = when (value_1) {
    !is _SealedChild1 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild1<!> -> ""
    <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> ""
}


// CASE DESCRIPTION: 'When' with one invert type checking operator on the some subtype of the sealed class, and 'else' branch.
fun case_9(value_1: _SealedClass): String = when (value_1) {
    !is _SealedChild1 -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with one direct type checking operator on the some subtype of the sealed class, and 'else' branch.
fun case_10(value_1: _SealedClass): String = when (value_1) {
    is _SealedChild1 -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with three basic types (Int, Boolean, String) covered and all types other than specified covered via invert type checking operator, and 'else' branch.
fun case_11(value_1: Any): String = when (value_1) {
    is Int -> ""
    is Boolean -> ""
    !is String -> ""
    <!USELESS_IS_CHECK!>is String<!> -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with direct and invert type checking operators on the basic type (String).
fun case_12(value_1: Any): String = when (value_1) {
    is String -> ""
    !is String -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_13(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1? -> ""
    !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3<!> -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_14(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3? -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3?<!> -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_15(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3?<!> -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_16(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3<!> -> ""
    null -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_17(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3? -> ""
    is _SealedChild3 -> ""
    null -> ""
}

// CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
fun case_18(value_1: _SealedClass?): String = when (value_1) {
    is _SealedChild1 -> ""
    !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3?<!> -> ""
    null -> ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_19(value_1: Number): String = when (value_1) {
    is Byte -> ""
    is Float -> ""
    !is Double -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_20(value_1: Number?): String = when (value_1) {
    is Byte -> ""
    is Float -> ""
    !is Double -> ""
    <!USELESS_IS_CHECK!>is Double<!> -> ""
    is Long -> "" // redundant Float type check
    else -> ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_21(value_1: Number?): String = when (value_1) {
    is Byte -> ""
    is Float -> ""
    !is Double -> ""
    <!USELESS_IS_CHECK!>is Double<!> -> ""
    null -> "" // redundant null check
    else -> ""
}

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_22(value_1: Number?): String = when (value_1) {
    is Byte -> ""
    is Float -> ""
    !is Double? -> ""
    is Double -> ""
    null -> "" // not redundant null check
    else -> ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the one typealias which is not equal to the source type, and 'else' branch.
fun case_23(value_1: Any): String = when (value_1) {
    !is _TypeAliasUnit -> ""
    else -> ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_24(value_1: Any): String {
    when (value_1) {
        !is _TypeAliasInt -> return ""
        <!USELESS_IS_CHECK!>!is _TypeAliasAny<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_25(value_1: Any): String {
    when (value_1) {
        is _TypeAliasInt -> return ""
        <!USELESS_IS_CHECK!>!is _TypeAliasAny<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_26(value_1: Any): String {
    when (value_1) {
        !is _TypeAliasInt -> return ""
        <!USELESS_IS_CHECK!>is _TypeAliasAny<!> -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_27(value_1: Any): String {
    when (value_1) {
        !is _EmptyObject -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_28(value_1: Any): String {
    when (value_1) {
        !is _EmptyObject -> return ""
        !is _ClassWithCompanionObject.Companion -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_29(value_1: Any): String {
    when (value_1) {
        !is _EmptyObject -> return ""
        is _ClassWithCompanionObject.Companion -> return ""
    }

    return ""
}

// CASE DESCRIPTION: 'When' with type checking operator on the two typealiases (one of which is equal to the source type).
fun case_30(value_1: Any): String {
    when (value_1) {
        is _EmptyObject -> return ""
        !is _ClassWithCompanionObject.Companion -> return ""
    }

    return ""
}
