package advent

import org.junit.Test
import kotlin.Int.Companion.MAX_VALUE
import kotlin.test.assertEquals

class Teine : Advent {

    data class Coordinates(val xMin: Int = MAX_VALUE, val xMax: Int = 0, val yMin: Int = MAX_VALUE, val yMax: Int = 0)

    override fun task(input: List<String>): String {
        return input.foldIndexed(Coordinates()) { rowNr, result, line ->
            (line.indexOf('X') to line.lastIndexOf('X')).let { (first, last) ->
                if (first < 0) result else
                    result.copy(
                        yMin = minOf(rowNr, result.yMin),
                        yMax = maxOf(rowNr, result.yMax),
                        xMin = minOf(result.xMin, first),
                        xMax = maxOf(result.xMax, last)
                    )
            }
        }.let { (it.xMax - it.xMin + it.yMax - it.yMin + 4) * 2 }.toString()
    }


    @Test
    fun teineVastus() {
        val input = """
            __________
            __________
            _____X____
            _____X____
            _____X____
            _______X__
            ______X___
            _____X____
            __________
            __________ 
        """.trimIndent()
        assertEquals("22", task(input.split("\n")))
    }
}
