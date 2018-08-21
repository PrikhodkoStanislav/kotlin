// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 4
 DESCRIPTION: with name shadowing
 */

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
fun case_1() {
    val <!UNUSED_VARIABLE!>value<!>: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!> = 10
        value.inc()
    }
}

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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

// CASE DESCRIPTION: lambdas with non-null assertions and 'exactly once' CallsInPlace effect.
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


