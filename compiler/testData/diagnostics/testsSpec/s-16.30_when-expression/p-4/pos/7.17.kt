/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 17
 DESCRIPTION: 'When' with bound value and fun literals in 'when condition'.
 */

// CASE DESCRIPTION: 'When' with 'else' branch (as expression).
fun case_1(value: Any?): Int {
    val fun1 = fun(): Int {
        return 0
    }

    return when (value) {
        fun() {} -> 1
        fun(): () -> () -> Unit {return fun(): () -> Unit {return fun() {}}} -> 2
        fun(): Boolean {
            return when {
                else -> true
            }
        } -> 3
        fun1 -> 4
        else -> 5
    }
}

// CASE DESCRIPTION: 'When' without 'else' branch (as statement).
fun case_2(value: Any?): Int {
    val fun1 = fun(): Int {
        return 0
    }

    when (value) {
        fun() {} -> return 1
        fun(): () -> () -> Unit {return fun(): () -> Unit {return fun() {}}} -> return 2
        fun(): Boolean {
            return when {
                else -> true
            }
        } -> return 3
        fun1 -> return 4
    }

    return -1
}