import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import kotlin.math.pow

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

    fun kolmas(input: List<String>): String {
        return input.first().split(" ")
            .let { letters ->
                (BigDecimal(2.0).pow(letters.count()) - letters.zip(letters.count() - 1 downTo 0)
                    .sumOf { (letter, depth) ->
                        if (letter == "v") BigDecimal(2.0).pow(depth) else BigDecimal.ZERO
                    })
                    .toBigInteger()
                    .toString()
            }
    }

    @Test
    fun kolmasTest1() {
        val input = "v p v v"
        assertEquals("5", kolmas(input.split("\n")))
    }


    @Test
    fun kolmasTest2() {
        val input = "v v p p p p p v p v"
        assertEquals("251", kolmas(input.split("\n")))
    }

    @Test
    fun kolmasTest3() {
        val input = "p v v v p v p p"
        assertEquals("140", kolmas(input.split("\n")))
    }

    @Test
    fun kolmasVastus() {
        assertEquals("2710512493338385384", kolmas(Inputs.kolmas.lines()))
    }
}
