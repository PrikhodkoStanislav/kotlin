// IGNORE_BACKEND: JS_IR
//KT-3042 Attempt to split long or double on the stack excepion

fun box(): String {
    var a: Long
    a = 12.toLong()
    fun f() {
        foo(a)
    }

    return "OK"
}

fun foo(l: Long) {}