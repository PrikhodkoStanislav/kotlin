// Внутри условия nullable receiver

val t = Test<Nothing>().method()

if (t is Nothing) {
    println(t.toByte())
}