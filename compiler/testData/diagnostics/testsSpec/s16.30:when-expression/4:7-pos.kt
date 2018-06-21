// !DIAGNOSTICS: -UNUSED_CHANGED_VALUE

/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 4
 SENTENCE 7: Any other expression.
 */

fun withIntArithmeticExpression(value: Int): Int = when (value) {
    1 + 2 * 90 % 100 / 11 -> 1
    Int.MAX_VALUE -> 2
    5.hashCode() -> 3
    0b10000111 -> 4
    0x0 -> 6
    0x11 -> 7
    else -> 8
}

fun withFloatArithmeticExpression(value: Float?): Int = when (value) {
    1.0F / 5F * 102.12F / Int.MIN_VALUE -> 1
    2.4F - 1.0F -> 2
    1.1231244512F / 24.1F -> 3
    null -> 4
    else -> 5
}

fun withLogicalExpression(value: Boolean): Int = when (value) {
    true && false || !!!true -> 1
    true -> 2
}

fun withEqualityExpression(value: Boolean, flag1: Boolean, flag2: Boolean, obj1: List<String>, obj2: List<String>): Int = when (value) {
    flag1 == flag2 -> 1
    obj1 === obj2 -> 2
    else -> 3
}

fun withComparisonExpression(value: Boolean, additionalValue1: Int, additionalValue2: Int): Int = when (value) {
    additionalValue1 > 10 && additionalValue2 <= 100 -> 1
    additionalValue1 >= 500 && additionalValue2 > 100 -> 2
    else -> 3
}

fun withConcatExpression(value: String): Int = when (value) {
    "" -> 1
    " " + "1" -> 2
    " $value " + "2" -> 3
    else -> 4
}

fun withWhenExpression(value: String?, additionalValue: Int, flag: Boolean): Int = when (value) {
    when {
        additionalValue > 10 -> "1"
        else -> "2"
    } -> 1
    null -> 2
    when (flag) {
        true -> "10"
        false -> "100"
    } -> 3
    else -> 4
}

fun withIfExpression(value: Int?, additionalValue: Int, flag: Boolean): Int = when (value) {
    if (additionalValue > 10) {
        1
    } else if (additionalValue < -10) {
        2
    } else {
        3
    } - Int.MAX_VALUE -> 1
    null -> 2
    if (flag) 10 else 100 -> 3
    else -> 4
}

fun withTryExpression(value: Int?): Int = when (value) {
    try {
        4
    } catch (e: Exception) {
        5
    } -> 1
    try {
        throw Exception()
    } catch (e: Exception) {
        6
    } finally {
        <!UNUSED_EXPRESSION!>7<!>
    } -> 2
    else -> 4
}

fun withElvisOperatorExpression(value: Boolean, additionalValue: Int?): Int = when (value) {
    additionalValue ?: true -> 1
    additionalValue!! > 100 -> 2
    else -> 3
}

fun withRangeExpression(value: Any?): Int = when (value) {
    1..9 -> 1
    10..100 -> 2
    null -> 3
    else -> 4
}

fun withCastExpression(value: Collection<Int>?, additionalValue1: Collection<Int>, additionalValue2: Collection<Int>?): Int = when (value) {
    additionalValue1 as MutableList<Int> -> 1
    additionalValue2 <!UNCHECKED_CAST!>as? MutableMap<Int, Int><!> -> 2
    null -> 3
    else -> 4
}

fun withPrefixOperatorsExpression(value1: Int, value2: Boolean, additionalValue1: Int, additionalValue2: Int, flag: Boolean): Int {
    var additionalValue1Mutable = additionalValue1
    var additionalValue2Mutable = additionalValue2

    val whenValue1 = when (value1) {
        ++additionalValue1Mutable -> 1
        --additionalValue2Mutable -> 2
        else -> 4
    }

    val whenValue2 = when (value2) {
        !flag -> 3
        else -> 4
    }

    return whenValue1 + whenValue2
}

fun withPostfixOperatorsExpression(value1: Int, value2: Boolean, additionalValue1: Int, additionalValue2: Int, flag: Boolean?): Int {
    var additionalValue1Mutable = additionalValue1
    var additionalValue2Mutable = additionalValue2

    val whenValue1 = when (value1) {
        additionalValue1Mutable++ -> 1
        additionalValue2Mutable-- -> 2
        else -> 4
    }

    val whenValue2 = when (value2) {
        !flag!! -> 3
        else -> 4
    }

    return whenValue1 + whenValue2
}

fun withIndexingExpression(value: Int?, additionalValue: List<Int>): Int = when (value) {
    additionalValue[0] -> 1
    additionalValue[1] -> 2
    null -> 3
    else -> 4
}

class A {
    val prop1 = 1
    val prop2 = 2
    val prop3 = 3

    fun mul(value: Int): Int {
        return value * 2
    }
}

fun withCallExpression(value: Int?, additionalValue1: List<Int>, additionalValue2: A, additionalValue3: A?): Int {
    fun fun1(): Int {
        return additionalValue1[0] + additionalValue1[1]
    }

    return when (value) {
        fun1() -> 1
        additionalValue2.mul(value!!) -> 2
        additionalValue3?.mul(<!DEBUG_INFO_SMARTCAST!>value<!>) -> 3
        else -> 4
    }
}

fun withPropertyAccessExpression(value: Int?, additionalValue2: A, additionalValue3: A?): Int = when (value) {
    additionalValue2.prop1 -> 1
    additionalValue3?.prop2 -> 2
    additionalValue2::prop3.get() -> 3
    else -> 4
}

fun withFunLiteral(value: Any?): Int {
    val fun1 = fun(): Int {
        return 0
    }

    return when (value) {
        fun() {} -> 1
        fun(): Boolean {
            return when {
                else -> true
            }
        } -> 2
        fun1 -> 3
        else -> 4
    }
}

fun withLambdaLiteral(value: Any?): Int {
    val lambda1 = { 0 }

    return when (value) {
        {} -> 1
        {
            when {
                else -> true
            }
        } -> 2
        lambda1 -> 3
        else -> 4
    }
}

fun withObjectLiteral(value: Any?): Int {
    val object1 = object {
        val prop1 = 1
    }

    return when (value) {
        object {} -> 1
        object {
            var lambda1 = {
                when {
                    else -> true
                }
            }
            val prop1 = 1
        } -> 2
        object1 -> 3
        else -> 4
    }
}

open class B {
    val prop1 = 1

    fun withThisExpression(value: Any?): Int = when (value) {
        this -> 1
        this.prop1 -> 2
        else -> 3
    }
}

fun withThrowExpression(value: Any?): Int = when (value) {
    throw Exception("Ex")<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
    <!UNREACHABLE_CODE!>else -> 2<!>
}

fun withReturnExpression(value: Any?): Int {
    when (value) {
        return 1<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
        <!UNREACHABLE_CODE!>else -> 2<!>
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun withContinueExpression(value: Any?): Int {
    loop@ while (true) {
        when (value) {
            continue@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
            <!UNREACHABLE_CODE!>else -> 2<!>
        }
    }

    <!UNREACHABLE_CODE!>return -1<!>
}

fun withBreakExpression(value: Any?): Int {
    loop@ while (true) {
        when (value) {
            break@loop<!UNREACHABLE_CODE!><!> -> <!UNREACHABLE_CODE!>1<!>
            <!UNREACHABLE_CODE!>else -> 2<!>
        }
    }

    return -1
}
