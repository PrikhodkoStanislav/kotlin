/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH 3
 SENTENCE 1: When expression without bound value (the form where the expression enclosed in parantheses is absent) evaluates one of the many different expressions based on corresponding conditions present in the same when entry.
 */

fun int(value: Int): Any {
    when {
        value == 1 -> { <!UNUSED_EXPRESSION!>1<!> }
        value == 2 -> {
            println("2")
        }
        value == 3 -> {<!UNUSED_LAMBDA_EXPRESSION!>{ 2 }<!>}
        value == 4 -> {
            val square = fun(value: Int): Int {
                return value * value
            }

            return square(value)
        }
        value == 5 -> {
            return fun(value: Int): Int {
                return value * value
            }
        }
        value == 6 -> {<!UNUSED_LAMBDA_EXPRESSION!>{
            fun(value: Int): Int {
                return value * value
            }
        }<!>}
        value == 7 -> 7
    }

    return -1
}

fun bool(value: Boolean): Int {
    when {
        value -> return 1
        !value -> return 2
    }

    return -1
}

fun string(value: String): Int {
    when {
        value.isEmpty() -> return 1
        value == "a" || value == "b" -> return 2
    }

    return -1
}

fun float(value: Float): Int {
    when {
        value < 10.5F -> return 1
        value > 10.6F -> return 2
    }

    return -1
}

fun double(value: Double): Int {
    when {
        value < 10.51235 -> return 1
        value > 10.69931 -> return 2
    }

    return -1
}

fun long(value: Long): Int {
    when {
        value < 8128491894234244849L -> return 1
        value > 8128491894234244865L -> return 2
    }

    return -1
}

fun typeCheck(value: Any): Int {
    when {
        value is String -> return 0
        value is Int -> return 1
        value is Double -> return 2
    }

    return -1
}

fun rangeTest(value: Int): Int {
    when {
        value in 0..10 -> return 0
        value in 11..100 -> return 1
        value in 101..1000 -> return 2
    }

    return -1
}

fun boolConsts(): Int {
    when {
        1 == 2 -> { return 0 }
        1 == 1 && 1 >= 5 -> { return 1 }
        true && false -> { return 2 }
        false || !true -> { return 3 }
        false && false && true -> { return 4 }
        ((true)) && (false) || ((false)) -> { return 5 }
        true == true -> { return 6 }
    }

    return -1
}
