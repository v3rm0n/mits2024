import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.Int.Companion.MAX_VALUE

class Advent {

    fun esimene(input: List<String>): String {
        return input.dropLast(1)
            .map { it.split(": ") }
            .map { it[0].toRegex().findAll(input.last()).count().toBigDecimal() * it[1].toBigDecimal() }
            .sumOf { it }
            .toPlainString()
    }

    @Test
    fun esimeneTest() {
        val input = """
            Kelluke: 1.64
            ForMe: 1.89
            Dynamit: 0.92
            odForMedFuDrDynamiti
        """.trimIndent()
        assertEquals("2.81", esimene(input.split("\n")))
    }

    @Test
    fun esimeneVastus() {
        assertEquals("56.98", esimene(Inputs.esimene.lines()))
    }

    data class Coordinates(val xMin: Int = MAX_VALUE, val xMax: Int = 0, val yMin: Int = MAX_VALUE, val yMax: Int = 0)

    fun teine(input: List<String>): String {
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
    fun teineTest() {
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
        assertEquals("22", teine(input.split("\n")))
    }

    @Test
    fun teineVastus() {
        assertEquals("2998", teine(Inputs.teine.lines()))
    }
}
