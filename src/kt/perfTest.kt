package kt

import java.io.File
import kotlin.system.measureNanoTime

fun timeComparisons(initialSize: Int = 0, stepSize: Int = 1, amountOfSteps: Int = 1_250, stepRepeats: Int = 100) {
    // Pair(arraySize, Pair(timeForFast, timeForMemFriendly))
    val results: Array<Pair<Int, Pair<Long, Long>>> = Array(amountOfSteps) { 0 to Pair(0L, 0L) }
    repeat(amountOfSteps) { run ->
        val arraySize = initialSize + (stepSize * run)
        val fastShiftTimes = LongArray(stepRepeats)
        val memFriendlyTimes = LongArray(stepRepeats)
        repeat(stepRepeats) {
            val items = randomItemArray(arraySize)
            val container1 = ItemContainer(items)
            val container2 = ItemContainer(items)
            fastShiftTimes[it] = measureNanoTime { container1.fastShift() }
            memFriendlyTimes[it] = measureNanoTime { container2.memoryFriendlyShift() }
        }
        results[run] = arraySize to Pair(fastShiftTimes.median(), memFriendlyTimes.median())
        if (run % 25 == 0) println(run)
    }

    var data = "Array Size,FastShift,MemFriendly,CollapseShift\n"

    for ((size, times) in results)
        data += "${if (size == 0) "" else "\n"}$size,${times.first},${times.second}"

    File("data.csv").writeText(data)
}
