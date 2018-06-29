/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 20
 DESCRIPTION: 'When' with bound value and this expression in 'when condition'.
 */

open class A {
    val prop1 = 1
    val lamdba1 = {1}

    fun fun1(): Int {
        return 1
    }

    // CASE DESCRIPTION: 'When' with 'else' branch (as expression).
    fun case_1(value: Any?): Int = when (value) {
        this -> 1
        ((this)) -> 2
        this::prop1.get() -> 3
        this.prop1 -> 4
        this.lamdba1() -> 5
        this::lamdba1.get()() -> 6
        this.fun1() -> 7
        this::fun1.invoke() -> 8
        else -> 9
    }

    // CASE DESCRIPTION: 'When' without 'else' branch (as statement).
    fun case_2(value: Any?): Int {
        when (value) {
            this -> return 1
            ((this)) -> return 2
            this::prop1.get() -> return 3
            this.prop1 -> return 4
            this.lamdba1() -> return 5
            this::lamdba1.get()() -> return 6
            this.fun1() -> return 7
            this::fun1.invoke() -> return 8
        }

        return -1
    }
}
