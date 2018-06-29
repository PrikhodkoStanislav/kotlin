/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 19
 DESCRIPTION: 'When' with bound value and object literals in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression).
fun case_1(value: Any?): Int {
    val object1 = object {
        val prop1 = 1
    }

    return when (value) {
        object {} -> 1
        object {
            val o1 = object {
                val o2 = object {}
            }
        } -> 2
        object {
            var lambda1 = {
                when {
                    else -> true
                }
            }
            val prop1 = 1
        } -> 3
        object1 -> 4
        else -> 5
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement).
fun case_2(value: Any?): Int {
    val object1 = object {
        val prop1 = 1
    }

    when (value) {
        object {} -> return 1
        object {
            val o1 = object {
                val o2 = object {}
            }
        } -> return 2
        object {
            var lambda1 = {
                when {
                    else -> true
                }
            }
            val prop1 = 1
        } -> <!UNUSED_EXPRESSION!>3<!>
        object1 -> return 4
    }

    return -1
}
