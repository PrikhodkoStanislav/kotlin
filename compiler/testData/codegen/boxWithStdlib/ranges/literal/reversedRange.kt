// Auto-generated by org.jetbrains.kotlin.generators.tests.GenerateRangesCodegenTestData. DO NOT EDIT!
import java.util.ArrayList

fun box(): String {
    val list1 = ArrayList<Int>()
    for (i in (3..5).reversed()) {
        list1.add(i)
        if (list1.size() > 23) break
    }
    if (list1 != listOf<Int>(5, 4, 3)) {
        return "Wrong elements for (3..5).reversed(): $list1"
    }

    val list2 = ArrayList<Int>()
    for (i in (3.toShort()..5.toShort()).reversed()) {
        list2.add(i)
        if (list2.size() > 23) break
    }
    if (list2 != listOf<Int>(5, 4, 3)) {
        return "Wrong elements for (3.toShort()..5.toShort()).reversed(): $list2"
    }

    val list3 = ArrayList<Long>()
    for (i in (3.toLong()..5.toLong()).reversed()) {
        list3.add(i)
        if (list3.size() > 23) break
    }
    if (list3 != listOf<Long>(5, 4, 3)) {
        return "Wrong elements for (3.toLong()..5.toLong()).reversed(): $list3"
    }

    val list4 = ArrayList<Char>()
    for (i in ('a'..'c').reversed()) {
        list4.add(i)
        if (list4.size() > 23) break
    }
    if (list4 != listOf<Char>('c', 'b', 'a')) {
        return "Wrong elements for ('a'..'c').reversed(): $list4"
    }

    return "OK"
}
