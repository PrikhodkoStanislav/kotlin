// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_SEALED_CLASSES
// !WITH_ENUM_CLASSES

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 6
 DESCRIPTION: 'When' with exhaustive when expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: Int, value_2: Boolean?, value_3: _EnumClass?, value_4: _SealedClass?) {
    when {
        value_1 == 1 -> when {
            value_1 > 1000 -> "1"
            value_1 > 100 -> "2"
            value_1 > 10 || value_1 < -10 -> "3"
            else -> "4"
        }
        value_1 == 2 -> when(value_2!!) {
            true -> "1"
            false -> "2"
        }
        value_1 == 3 -> when(value_2) {
            true -> "1"
            false -> "2"
            null -> "3"
        }
        value_1 == 4 -> when(value_3!!) {
            _EnumClass.WEST -> "1"
            _EnumClass.SOUTH -> "2"
            _EnumClass.NORTH -> "3"
            _EnumClass.EAST -> "4"
        }
        value_1 == 5 -> when(value_3) {
            _EnumClass.WEST -> "1"
            _EnumClass.SOUTH -> "2"
            _EnumClass.NORTH -> "3"
            _EnumClass.EAST -> "4"
            null -> "5"
        }
        value_1 == 6 -> when(value_4!!) {
            is _SealedChild1 -> "1"
            is _SealedChild2 -> "2"
            is _SealedChild3 -> "3"
        }
        value_1 == 7 -> {
            when(value_4) {
                is _SealedChild1 -> "1"
                is _SealedChild2 -> "2"
                is _SealedChild3 -> "3"
                null -> "4"
            }
        }
    }
}