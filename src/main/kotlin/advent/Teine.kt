package advent

import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE

class Teine : Advent {
    override fun task(input: List<String>): String {
        val xMin = input.minOf {
            it.foldIndexed(MAX_VALUE) { i, acc, char -> if (char == 'X' && acc > i) i else acc }
        }
        val xMax = input.maxOf {
            it.foldIndexed(MIN_VALUE) { i, acc, char -> if (char == 'X' && acc < i) i else acc }
        }
        val yMax = input.foldIndexed(MAX_VALUE) { j, acc, line -> if (line.contains("X")) j else acc }
        val yMin = input.foldRightIndexed(MIN_VALUE) { j, line, acc -> if (line.contains("X")) j else acc }
        return (((xMax + 1 - xMin + 1) + (yMax + 1 - yMin + 1)) * 2).toString()
    }
}
