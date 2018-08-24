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
    val value: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
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
    val value: Int

    funWithAtMostOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
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
    var value: Int

    funWithExactlyOnceCallsInPlace {
        funWithExactlyOnceCallsInPlace {
            value = 1
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
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
    var value: Int

    funWithAtMostOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value = 1
        }
        funWithAtLeastOnceCallsInPlace {
            value.inc()
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        funWithExactlyOnceCallsInPlace {
            value.inc()
        }
        value.inc()
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
    var value: Int

    funWithAtLeastOnceCallsInPlace {
        funWithAtLeastOnceCallsInPlace {
            value = 1
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
        }
        funWithUnknownCallsInPlace {
            value.inc()
        }
        value.inc()
    }

    value.inc()
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
    var value: Int

    funWithUnknownCallsInPlace {
        funWithAtMostOnceCallsInPlace {
            funWithAtLeastOnceCallsInPlace {
                value = 1
            }
            funWithExactlyOnceCallsInPlace {
                value.inc()
            }
            funWithAtLeastOnceCallsInPlace {
                value.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
            funWithUnknownCallsInPlace {
                value.inc()
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
    var value: Int

    funWithAtMostOnceCallsInPlace {
        funWithUnknownCallsInPlace {
            funWithExactlyOnceCallsInPlace {
                value = 1
            }
            funWithAtLeastOnceCallsInPlace {
                value.inc()
            }
            funWithAtMostOnceCallsInPlace {
                value.inc()
            }
            funWithUnknownCallsInPlace {
                value.inc()
            }
        }
    }
}

