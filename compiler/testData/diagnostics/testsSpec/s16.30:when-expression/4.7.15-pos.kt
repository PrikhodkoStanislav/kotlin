/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 15
 DESCRIPTION: When with bound value and call expressions in when entry.
 */

class A {
    fun mul(value: Int): Int {
        return value * 2
    }
    fun nestedMul(value1: Int): (Int) -> Int {
        return fun(value2: Int): Int {
            return value1 * value2 * 2
        }
    }
}

fun test1(value: Int?, value1: List<Int>, value2: A, value3: A?): Int {
    fun fun1(): Int {
        return value1[0] + value1[1]
    }

    return when (value) {
        fun1() -> 1
        value2.mul(value!!) -> 2
        value2.nestedMul(<!DEBUG_INFO_SMARTCAST!>value<!>)(<!DEBUG_INFO_SMARTCAST!>value<!>) -> 3
        <!UNSAFE_IMPLICIT_INVOKE_CALL!>value3?.nestedMul(<!DEBUG_INFO_SMARTCAST!>value<!>)<!>(<!DEBUG_INFO_SMARTCAST!>value<!>) -> 4
        value3?.mul(<!DEBUG_INFO_SMARTCAST!>value<!>) -> 5
        else -> 6
    }
}

fun test2(value: Int?, value1: List<Int>, value2: A, value3: A?): Int {
    fun fun1(): Int {
        return value1[0] + value1[1]
    }

    when (value) {
        fun1() -> return 1
        value2.mul(value!!) -> return 2
        value2.nestedMul(<!DEBUG_INFO_SMARTCAST!>value<!>)(<!DEBUG_INFO_SMARTCAST!>value<!>) -> <!UNUSED_EXPRESSION!>3<!>
        <!UNSAFE_IMPLICIT_INVOKE_CALL!>value3?.nestedMul(<!DEBUG_INFO_SMARTCAST!>value<!>)<!>(<!DEBUG_INFO_SMARTCAST!>value<!>) -> <!UNUSED_EXPRESSION!>4<!>
        value3?.mul(<!DEBUG_INFO_SMARTCAST!>value<!>) -> return 5
    }

    return -1
}