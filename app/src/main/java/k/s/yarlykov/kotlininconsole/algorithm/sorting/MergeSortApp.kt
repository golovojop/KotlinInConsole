package k.s.yarlykov.kotlininconsole.algorithm.sorting

class MergeSortApp {
    val data = intArrayOf(101, 11, 79, 7, 78, 23, 21, 44, 47, 35, 54)

    fun mergeSort(source: IntArray): IntArray {
        if (source.size == 1) return source

        val a = IntArray(source.size / 2)
        val b = IntArray(source.size - source.size / 2)

        System.arraycopy(source, 0, a, 0, source.size / 2)
        System.arraycopy(source, source.size / 2, b, 0, source.size - source.size / 2)

        return merge(mergeSort(a), mergeSort(b))
    }


    fun merge(a: IntArray, b: IntArray): IntArray {
        val result = IntArray(a.size + b.size)

        var i = a.size - 1
        var j = b.size - 1
        var k = result.size - 1

        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                result[k--] = a[i--]
            } else {
                result[k--] = b[j--]
            }
        }

        while (i >= 0) {
            result[k--] = a[i--]
        }
        while (j >= 0) {
            result[k--] = b[j--]
        }

        return result
    }

}

fun main() {
    val app = MergeSortApp()
    val result = app.mergeSort(app.data)

    result.forEach { print("$it ") }
}
