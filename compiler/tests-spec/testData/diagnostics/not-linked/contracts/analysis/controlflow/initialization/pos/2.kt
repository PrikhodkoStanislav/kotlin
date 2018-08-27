// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (POSITIVE)

 SECTION: Contracts
 CATEGORY: analysis, controlflow, initialization
 NUMBER: 2
 DESCRIPTION: Nested val/var assignments and usages based on 'call in place' effect
 */

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce
        nested
    smartInit:val
    smartcast:inited
 */
fun case_1() {
    val value_1: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value_1 = 1
            funWithExactlyOnceCallsInPlace {
                value_1.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce,atMostOnce,unknown
        nested
    smartInit:val
    smartcast:inited
 */
fun case_2() {
    val value_1: Int

    funWithAtMostOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value_1 = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value_1.inc()
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce
        nested
    smartInit:var
    smartcast:inited
 */
fun case_3() {
    var value_1: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value_1 = 1
            funWithExactlyOnceCallsInPlace {
                value_1.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:exactlyOnce,atLeastOnce,atMostOnce,unknown
        nested
    smartInit:var
    smartcast:inited
 */
fun case_4() {
    var value_1: Int

    funWithAtMostOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value_1 = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value_1.inc()
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        funWithExactlyOnceCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,atMostOnce,unknown
        nested
    smartInit:var
    smartcast:inited
 */
fun case_7() {
    var value_1: Int

    funWithAtLeastOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value_1 = 1
            funWithAtMostOnceCallsInPlace {
                value_1.inc()
            }
        }
        funWithUnknownCallsInPlace {
            value_1.inc()
        }
        value_1.inc()
    }

    value_1.inc()
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,atMostOnce,unknown,exactlyOnce
        nested
    smartInit:var
    smartcast:inited
 */
fun case_8() {
    var value_1: Int

    funWithUnknownCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            funWithAtLeastOnceCallsInPlace {
                value_1 = 1
            }
            funWithExactlyOnceCallsInPlace {
                value_1.inc()
            }
            funWithAtLeastOnceCallsInPlace {
                value_1.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value_1.inc()
            }
            funWithUnknownCallsInPlace {
                value_1.inc()
            }
        }
    }
}

/*
 CASE KEYWORDS:
    effectsUsage
        callsInPlace:atLeastOnce,atMostOnce,unknown,exactlyOnce
        nested
    smartInit:var
    smartcast:inited
 */
fun case_9() {
    var value_1: Int

    funWithAtMostOnceCallsInPlace {
        funWithUnknownCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                value_1 = 1
            }
            funWithAtLeastOnceCallsInPlace {
                value_1.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value_1.inc()
            }
            funWithUnknownCallsInPlace {
                value_1.inc()
            }
        }
    }
}

