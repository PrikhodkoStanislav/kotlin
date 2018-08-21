// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 4
 DESCRIPTION: Calls in place contract functions with name shadowing
 */

// CASE DESCRIPTION: usage val with an existing name (name shadowing) after initialization in calls in place contract functions with 'exactly once' invocation kind
fun case_1() {
    val <!UNUSED_VARIABLE!>value<!>: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!> = 10
        value.inc()
    }
}

// CASE DESCRIPTION: usage val with an existing name (name shadowing) after initialization (with two nesting levels) in calls in place contract functions with 'exactly once' invocation kind
fun case_2() {
    val <!UNUSED_VARIABLE!>value<!>: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithExactlyOnceCallsInPlace {
            value = 10
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }
}

// CASE DESCRIPTION: usage top-level val and val with same name (name shadowing) after initialization in correct contract functions ('at least once' + 'exactly once').
fun case_3() {
    val value: Int

    funWithAtLeastOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithExactlyOnceCallsInPlace {
            value = 10
        }
        funWithAtMostOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }
    funWithExactlyOnceCallsInPlace {
        value = 10
    }
    value.inc()
}

// CASE DESCRIPTION: usage top-level val and val with same name (name shadowing) after initialization in correct contract functions ('at most once' + 'exactly once').
fun case_4() {
    val value: Int

    funWithAtMostOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithExactlyOnceCallsInPlace {
            value = 10
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }
    funWithExactlyOnceCallsInPlace {
        value = 10
    }
    value.inc()
}

// CASE DESCRIPTION: usage top-level val and val with same name (name shadowing) after initialization in correct contract functions ('unknown' + 'exactly once').
fun case_5() {
    val value: Int

    funWithUnknownCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithExactlyOnceCallsInPlace {
            value = 10
        }
        funWithAtMostOnceCallsInPlace {
            value.inc()
        }
    }
    funWithExactlyOnceCallsInPlace {
        value = 10
    }
    value.inc()
}

// CASE DESCRIPTION: usage top-level var and val with same name (name shadowing) after initialization in correct contract functions ('at least once' + 'at least once').
fun case_6() {
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithExactlyOnceCallsInPlace {
            value = 10
        }
        funWithAtMostOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    funWithAtLeastOnceCallsInPlace {
        value = 1
    }

    value.dec()
}

// CASE DESCRIPTION: usage top-level val and var with same name (name shadowing) after initialization in correct contract functions ('at least once' + 'exactly once').
fun case_7() {
    val value: Int

    funWithAtLeastOnceCallsInPlace {
        var <!NAME_SHADOWING!>value<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value = 10
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    funWithExactlyOnceCallsInPlace {
        value = 1
    }

    value.dec()
}

// CASE DESCRIPTION: usage top-level var and var with same name (name shadowing) after initialization in correct contract functions ('at least once' + 'at least once').
fun case_8() {
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        var <!NAME_SHADOWING!>value<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value = 10
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        value++
    }

    funWithAtLeastOnceCallsInPlace {
        value = 1
    }

    value--
}


