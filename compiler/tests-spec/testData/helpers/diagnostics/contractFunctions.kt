import kotlin.internal.contracts.*

inline fun <T> funWithExactlyOnceCallsInPlace(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

inline fun funWithExactlyOnceCallsInPlace(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

inline fun <T> funWithAtLeastOnceCallsInPlace(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
    return block()
}

inline fun funWithAtLeastOnceCallsInPlace(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    block()
    block()
}

inline fun funWithAtMostOnceCallsInPlace(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
}

inline fun funWithUnknownCallsInPlace(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }
    block()
}

fun funWithReturns(cond: Boolean) {
    contract {
        returns() implies (cond)
    }
    if (!cond) throw Exception()
}

fun funWithReturnsAndInvertCondition(cond: Boolean) {
    contract {
        returns() implies (!cond)
    }
    if (cond) throw Exception()
}

fun funWithReturnsAndTypeCheck(value: Any?) {
    contract {
        returns() implies (value is String)
    }
    if (value !is String) throw Exception()
}

fun funWithReturnsAndNotNullCheck(value: Any?) {
    contract {
        returns() implies (value != null)
    }
    if (value == null) throw Exception()
}

fun funWithReturnsAndNullCheck(value: Any?) {
    contract {
        returns() implies (value == null)
    }
    if (value != null) throw Exception()
}

fun funWithReturnsTrue(cond: Boolean): Boolean {
    contract {
        returns(true) implies (cond)
    }
    return cond
}

fun funWithReturnsTrueAndInvertCondition(cond: Boolean): Boolean {
    contract {
        returns(true) implies (!cond)
    }
    return !cond
}

fun funWithReturnsTrueAndTypeCheck(value: Any?): Boolean {
    contract {
        returns(true) implies (value is String)
    }
    return value is String
}

fun funWithReturnsTrueAndInvertTypeCheck(value: Any?): Boolean {
    contract {
        returns(true) implies (value !is String)
    }
    return value !is String
}

fun funWithReturnsTrueAndNotNullCheck(value: Number?): Boolean {
    contract {
        returns(true) implies (value != null)
    }
    return value != null
}

fun funWithReturnsTrueAndNullCheck(value: Number?): Boolean {
    contract {
        returns(true) implies (value == null)
    }
    return value == null
}

fun funWithReturnsFalse(cond: Boolean): Boolean {
    contract {
        returns(false) implies (cond)
    }
    return cond
}

fun funWithReturnsFalseAndInvertCondition(cond: Boolean): Boolean {
    contract {
        returns(false) implies (!cond)
    }
    return !cond
}

fun funWithReturnsFalseAndTypeCheck(value: Any?): Boolean {
    contract {
        returns(false) implies (value is String)
    }
    return value is String
}

fun funWithReturnsFalseAndInvertTypeCheck(value: Any?): Boolean {
    contract {
        returns(false) implies (value !is String)
    }
    return value !is String
}

fun funWithReturnsFalseAndNotNullCheck(value: Number?): Boolean {
    contract {
        returns(false) implies (value != null)
    }
    return value != null
}

fun funWithReturnsFalseAndNullCheck(value: Number?): Boolean {
    contract {
        returns(false) implies (value == null)
    }
    return value == null
}
