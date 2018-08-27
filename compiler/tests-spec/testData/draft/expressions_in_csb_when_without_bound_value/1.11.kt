/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 11
 DESCRIPTION: 'When' with cast expression in the control structure body.
 */

fun case_1(value_1: Int, value_1: Collection<Int>, value_2: Collection<Int>?) {
    when {
        value_1 == 1 -> value_1 as MutableList<Int>
        value_1 == 2 -> value_2 <!UNCHECKED_CAST!>as? MutableMap<Int, Int><!>
        value_1 == 3 -> (value_1 <!UNCHECKED_CAST!>as? Map<Int, Int><!>) as MutableMap<Int, Int>
        value_1 == 4 -> {
            (value_1 as List<Int>) as MutableList<Int>
        }
    }
}