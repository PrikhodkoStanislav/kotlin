// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !CHECK_TYPE
// !WITH_SEALED_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 11
 SENTENCE 6: The bound expression is of a sealed class type and all its possible subtypes are covered using type test conditions of this expression;
 NUMBER: 1
 DESCRIPTION: Checking for not exhaustive when when not covered by all possible subtypes.
 */

// CASE DESCRIPTION: Checking for not exhaustive 'when' on the sealed class (type checking and equality with object).
fun case_1(value: _SealedClassMixed): String = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is _SealedMixedChild1 -> ""
    is _SealedMixedChild2 -> ""
    _SealedMixedChildObject1 -> ""
}

// CASE DESCRIPTION: Checking for not exhaustive 'when' on the sealed class (type checking).
fun case_2(value: _SealedClassMixed): String = <!NO_ELSE_IN_WHEN!>when<!>(value) {
    is _SealedMixedChild1 -> ""
    is _SealedMixedChild2 -> ""
    is _SealedMixedChild3 -> ""
}

// CASE DESCRIPTION: Checking for not exhaustive 'when' on the sealed class with several subtypes (no branches).
fun case_3(value: _SealedClassMixed): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) { }

// CASE DESCRIPTION: Checking for not exhaustive 'when' on the sealed class with one subtype (no branches).
fun case_4(value: _SealedClassSingleWithObject): Int = <!NO_ELSE_IN_WHEN!>when<!>(value) { }

// CASE DESCRIPTION: Checking for not exhaustive 'when' on the empty sealed class (without subtypes).
fun case_5(value: _SealedClassEmpty): String = <!NO_ELSE_IN_WHEN!>when<!> (value) { }

// CASE DESCRIPTION: 'When' with 'else' branch and type test condition on the various nullable basic types (two nullable type check).
fun case_6(value: Number): String = when (value) {
    is Byte -> ""
    is Double -> ""
    is Float -> ""
    is Int -> ""
    is Long -> ""
    is Short -> ""
    else -> ""
}

/*
 CASE DESCRIPTION: 'When' with 'else' branch and type test condition on Any.
 DISCUSSION: maybe make exhaustive without else?
 */
fun case_7(value: Any?): String = <!NO_ELSE_IN_WHEN!>when<!> (value) {
    is Any -> ""
    null -> ""
}

/*
 CASE DESCRIPTION: 'When' with 'else' branch and type test condition on Any.
 DISCUSSION: maybe make exhaustive without else?
 */
fun case_8(value: Any): String = when (value) {
    <!USELESS_IS_CHECK!>is Any<!> -> ""
    else -> ""
}

fun case_6(value: _SealedClass?): String = <!NO_ELSE_IN_WHEN!>when<!> (value) {
    null -> ""
}

/*
 CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
 UNEXPECTED BEHAVIOUR
 */
fun case_9(value: _SealedClass?): String = when (value) {
    is _SealedChild1, !is _SealedChild3?, <!USELESS_IS_CHECK!>is _SealedChild3?<!> -> ""
    else -> ""
}

/*
 CASE DESCRIPTION: 'When' with two subtypes of the sealed class covered and all subtypes other than specified covered via invert type checking operator.
 UNEXPECTED BEHAVIOUR
 */
fun case_10(value: _SealedClass?): String = when (value) {
    is _SealedChild1, !is _SealedChild3 -> ""
    <!USELESS_IS_CHECK!>is _SealedChild3?<!> -> ""
    else -> ""
}