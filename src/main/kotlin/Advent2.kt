import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class Advent2 {

    data class Coordinates(val xMin: Int? = null, val xMax: Int? = null, val yMin: Int? = null, val yMax: Int? = null) {
        fun boundingBox() = (xMax!! - xMin!! + yMax!! - yMin!! + 4) * 2
    }

    fun teine(input: List<String>): String {
        return input.foldIndexed(Coordinates()) { rowNr, result, line ->
            line.indexOf('X').takeIf { it >= 0 }?.let { xMin ->
                val xMax = line.lastIndexOf('X')
                result.copy(
                    yMin = result.yMin ?: rowNr,
                    yMax = rowNr,
                    xMin = minOf(result.xMin ?: xMin, xMin),
                    xMax = maxOf(result.xMax ?: xMax, xMax)
                )
            } ?: result
        }
            .boundingBox()
            .toString()
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
