fun whenWithIntBoundValueTest(value: Int?): Int {
    when (value) {
        0 -> return 0
        1 -> return 1
        2 -> return 2
        null -> return 3
    }

    return -1
}

fun whenWithBoolBoundValueTest(value: Boolean?): Int {
    when (value) {
        true -> return 0
        false -> return 1
        null -> return 2
    }

    return -1
}

fun whenWithStringBoundValueTest(value: String?): Int {
    when (value) {
        "" -> return 0
        "1" -> return 1
        null -> return 2
    }

    return -1
}

fun whenWithCharBoundValueTest(value: Char?): Int {
    when (value) {
        '0' -> return 0
        '1' -> return 1
        null -> return 2
    }

    return -1
}

fun whenWithFloatBoundValueTest(value: Float?): Int {
    when (value) {
        10.2F -> return 0
        50.0F -> return 1
        44 -> return 2
        null -> return 3
    }

    return -1
}

fun whenWithDoubleBoundValueTest(value: Float?): Int {
    when (value) {
        10.2F -> return 0
        50.0F -> return 1
        44 -> return 2
        null -> return 3
    }

    return -1
}