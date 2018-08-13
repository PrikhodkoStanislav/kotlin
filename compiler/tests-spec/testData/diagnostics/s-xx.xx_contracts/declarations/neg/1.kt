/*
 KOTLIN DIAGNOSTICS FUTURE SPEC TEST (NEGATIVE)

 SECTION: XX.XX Contracts
 CATEGORY: declarations
 NUMBER: 1
 DESCRIPTION: Empty 'when' with bound value.
 */

import kotlin.internal.contracts.*

fun isString(x: Any?): Boolean {
    contract {
        returns(true) implies (x is String)
    }
    return x is String
}

fun test(x: Any?) {
    if (isString(x)) {
        <!DEBUG_INFO_SMARTCAST!>x<!>.length
    }
}
