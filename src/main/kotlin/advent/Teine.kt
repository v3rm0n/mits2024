package advent

import kotlin.Int.Companion.MAX_VALUE

class Teine : Advent {

    data class Coordinates(val xMin: Int = MAX_VALUE, val xMax: Int = 0, val yMin: Int = MAX_VALUE, val yMax: Int = 0)

    override fun task(input: List<String>): String {
        return input.foldIndexed(Coordinates()) { rowNr, result, line ->
            (line.indexOf('X') to line.lastIndexOf('X')).let { (first, last) ->
                result.copy(
                    yMin = if (first >= 0) minOf(rowNr, result.yMin) else result.yMin,
                    yMax = if (first >= 0) maxOf(rowNr, result.yMax) else result.yMax,
                    xMin = if (first >= 0) minOf(result.xMin, first) else result.xMin,
                    xMax = if (last >= 0) maxOf(result.xMax, last) else result.xMax
                )
            }
        }.let { (it.xMax - it.xMin + it.yMax - it.yMin + 4) * 2 }.toString()
    }
}
