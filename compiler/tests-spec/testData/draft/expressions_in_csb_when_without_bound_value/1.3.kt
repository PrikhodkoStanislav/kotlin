/*
 KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)

 SECTION: 16.30 When expression
 PARAGRAPH: 3
 SENTENCE: [1] When expression without bound value_1 (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 NUMBER: 3
 DESCRIPTION: 'When' with different variants of the equality expression in the control structure body.
 */

fun case_1(
    value_1: Int,
    value_1: Boolean,
    value_2: Byte,
    value_3: Short,
    value_4: Int,
    value_5: Long,
    value_6: Float,
    value_7: Double,
    value_8: String,
    value_9: Char,
    obj1: List<String>,
    obj2: List<String>,
    obj3: Nothing,
    obj4: Any
) {
    when {
        value_1 == 1 -> value_1 == true
        value_1 == 2 -> value_2 == 127.toByte()
        value_1 == 3 -> value_3 != 11.toShort()
        value_1 == 4 -> value_4 != 13142
        value_1 == 5 -> value_5 == 1243124124431443L
        value_1 == 6 -> value_6 != .0000000012f
        value_1 == 7 -> value_7 == 13223.12391293
        value_1 == 8 -> value_8 == ""
        value_1 == 9 -> value_9 != 'a'
        value_1 == 10 -> {
            obj2 === obj1 && obj4 != obj2 || !(value_6 == .000023412f) && value_9 == '0' || value_1 == false
        }
        value_1 == 11 -> obj3 <!UNREACHABLE_CODE!>=== obj4<!>
        value_1 == 12 -> obj4 <!UNREACHABLE_CODE!>!==<!> obj3
        value_1 == 13 -> obj3 <!UNREACHABLE_CODE!>!= obj3<!>
        value_1 == 14 -> obj1 === obj2
        value_1 == 15 -> obj1 == obj2
    }
}