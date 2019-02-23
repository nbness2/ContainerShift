package kt

inline fun <reified T> Array<T>.partitionJoin(predicate: (T) -> Boolean): Array<T> {
    val results = partition(predicate)
    return (results.second + results.first).toTypedArray()
}

fun LongArray.noOverflowAverage(): Long {
    var average = this[0]
    for ((index, number) in this.withIndex())
        average += (number - average) / (index + 1)
    return average
}

fun LongArray.median(): Long {
    val sorted = this.sortedArray()
    return if (sorted.size % 2 == 0) (sorted[(size/2)] + sorted[(size/2)-1]) / 2
    else this.sortedArray()[size/2]
}