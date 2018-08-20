import kotlin.internal.contracts.*

inline fun <T> funWithExacltyOnceCallsInPlace(block: () -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

inline fun funWithExacltyOnceCallsInPlace(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
}

inline fun <T> funWithAtLeastOnceCallsInPlace(block: () -> Unit): T {
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
