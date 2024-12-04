import org.junit.Assert.assertEquals
import org.junit.Test

class Advent1 {

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
}