// !LANGUAGE: +AllowContractsForCustomFunctions +UseCallsInPlaceEffect
// !DIAGNOSTICS: -INVISIBLE_REFERENCE -INVISIBLE_MEMBER
// !WITH_CONTRACT_FUNCTIONS

/*
 KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)

 SECTION: Contracts
 CATEGORY: analysis, smartcasts
 NUMBER: 12
 DESCRIPTION: Check smartcasts if pass same fields of instances of the same class in contract function with conjunction not-null condition.
 UNEXPECTED BEHAVIOUR
 ISSUES: KT-26300
 */

// FILE: contracts.kt

package contracts

import kotlin.internal.contracts.*

fun case_3(value1: Any?, value2: Any?, value3: Any?, value4: Any?) {
    contract { returns() implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null) }
    if (!(value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null)) throw Exception()
}

fun case_4(value1: Any?, value2: Any?, value3: Any?, value4: Any?): Boolean {
    contract { returns(true) implies (value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null) }
    return value1 is Float? && value1 != null && value2 != null && value3 != null && value4 != null
}

// FILE: usages.kt

import contracts.*

class case_1_class {
    val prop_1: Int? = 10
    fun case_1(value1: Any?, value2: Number?) {
        val o = case_1_class()
        funWithReturns(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null)
        println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
    }
}

class case_2_class {
    val prop_1: Int? = 10
    fun case_2(value1: Any?, value2: Number?) {
        val o = case_2_class()
        if (funWithReturnsTrue(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null)) {
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
        if (!funWithReturnsTrueAndInvertCondition(value1 is Float? && value1 != null && value2 != null && o.prop_1 != null && this.prop_1 != null)) {
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}

class case_3_class {
    val prop_1: Int? = 10
    fun case_3(value1: Any?, value2: Number?) {
        val o = case_3_class()
        contracts.case_3(value1, value2, o.prop_1, this.prop_1)
        println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
    }
}

class case_4_class {
    val prop_1: Int? = 10
    fun case_4(value1: Any?, value2: Number?) {
        val o = case_4_class()
        if (contracts.case_4(value1, value2, o.prop_1, this.prop_1)) {
            println(o.prop_1<!UNSAFE_CALL!>.<!>plus(3))
            println(this.prop_1<!UNSAFE_CALL!>.<!>plus(3))
        }
    }
}
