// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 4
 DESCRIPTION: Calls in place contract functions with name shadowing and wrong invocation kind of calls in place effect
 */

// CASE DESCRIPTION: uninitialized variable after initialization of variable with same name (name shadowing) in calls in place contract functions with 'exactly once' invocation kind
fun case_1() {
    val value: Int

    funWithExactlyOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!> = 10
        value.inc()
    }

    <!UNINITIALIZED_VARIABLE!>value<!>.inc()
}

// CASE DESCRIPTION: uninitialized variable after initialization (with two nesting levels) of variable with same name (name shadowing) in calls in place contract functions with 'exactly once' invocation kind
fun case_2() {
    val value: Int

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
    <!UNINITIALIZED_VARIABLE!>value<!>.inc()
}

// CASE DESCRIPTION: uninitialized variable after initialization in 'at most once' and initialization (with two nesting levels) of variable with same name (name shadowing) in calls in place contract functions with 'exactly once' invocation kind
fun case_3() {
    val value: Int

    funWithAtLeastOnceCallsInPlace {
        val <!NAME_SHADOWING!>value<!>: Int
        funWithAtMostOnceCallsInPlace {
            value = 10
        }
        funWithAtMostOnceCallsInPlace {
            <!UNINITIALIZED_VARIABLE!>value<!>.inc()
        }
        value.inc()
    }
    funWithAtMostOnceCallsInPlace {
        value = 10
    }
    <!UNINITIALIZED_VARIABLE!>value<!>.inc()
}

// CASE DESCRIPTION: uninitialized variable (var) after initialization in 'at most once' and initialization (with two nesting levels) of variable (val) with same name (name shadowing) in calls in place contract functions with 'exactly once' invocation kind
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

    funWithAtMostOnceCallsInPlace {
        value = 1
    }

    <!UNINITIALIZED_VARIABLE!>value<!>.dec()
}

// CASE DESCRIPTION: uninitialized variable (val) after initialization in 'at most once' and initialization (with two nesting levels) of variable (var) with same name (name shadowing) in calls in place contract functions with 'exactly once' invocation kind
fun case_7() {
    val value: Int

    funWithUnknownCallsInPlace {
        var <!NAME_SHADOWING!>value<!>: Int
        funWithAtLeastOnceCallsInPlace {
            value = 10
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    funWithUnknownCallsInPlace {
        <!VAL_REASSIGNMENT!>value<!> = 1
    }

    <!UNINITIALIZED_VARIABLE!>value<!>.dec()
}
