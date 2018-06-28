/*
 KOTLIN SPEC TEST (POSITIVE)

 SECTION 16.30: When expression
 PARAGRAPH: 4
 SENTENCE 7: Any other expression.
 NUMBER: 14
 DESCRIPTION: When with bound value and indexing expressions in when entry.
 */

fun test1(value: Int?, value1: List<Int>, value2: List<List<List<List<Int>>?>>, value3: List<List<() -> List<List<Int>>>>, value4: List<List<(() -> List<List<Int>>)?>>): Int = when (value) {
    value1[0] -> 1
    value1[1] -> 2
    value2[0][11]!![-90][0L.toInt()] -> 2
    value3[0][11]()[-90][0L.toInt()] -> 2
    value4[0][11]!!()[-90][0L.toInt()] -> 2
    null -> 3
    else -> 4
}

fun test2(value: Int?, value1: List<Int>, value2: List<List<List<List<Int>>?>>, value3: List<List<() -> List<List<Int>>>>, value4: List<List<(() -> List<List<Int>>)?>>): Int {
    when (value) {
        value1[0] -> return 1
        value1[1] -> return 2
        value2[0][11]!![-90][0L.toInt()] -> return 2
        value3[0][11]()[-90][0L.toInt()] -> return 2
        value4[0][11]!!()[-90][0L.toInt()] -> return 2
        null -> return 3
    }

    return -1
}