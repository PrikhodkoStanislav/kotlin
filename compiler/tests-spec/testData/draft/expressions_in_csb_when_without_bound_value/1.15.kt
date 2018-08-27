// !WITH_CLASSES
// !WITH_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 15
 DESCRIPTION: 'When' with call expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: _Class, value_2: _Class?, value_3: List<Int>, value_4: List<Int>?) {
    fun __fun_2(): () -> Unit {
        return fun() {
            value_4!![0] + value_3[1]
        }
    }

    when {
        value_1 == 1 -> _fun(value_3, value_4!!)
        value_1 == 2 -> __fun_2()()
        value_1 == 3 -> value_1.fun_2(value_3[0])
        value_1 == 4 -> value_2?.fun_2(value_3[0])
        value_1 == 5 -> {
            value_2!!.fun_1()(value_4!![0])
        }
    }
}