/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 17
 DESCRIPTION: When with fun literal in the control structure bodies.
 */

fun foo(value: Int) {
    val fun1 = fun(): Int {
        return 0
    }

    when {
        value == 1 -> <!UNUSED_EXPRESSION!>fun() {}<!>
        value == 2 -> <!UNUSED_EXPRESSION!>fun(): Boolean {
            return when {
                else -> true
            }
        }<!>
        value == 3 -> <!UNUSED_EXPRESSION!>fun1<!>
        value == 4 -> {
            <!UNUSED_EXPRESSION!>fun() {<!UNUSED_EXPRESSION!>fun() {<!UNUSED_EXPRESSION!>fun() {}<!>}<!>}<!>
        }
    }
}