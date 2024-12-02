package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Esimene : Advent {

    override fun task(input: List<String>): String {
        return input.dropLast(1)
            .map { it.split(": ") }
            .map { it[0].toRegex().findAll(input.last()).count().toBigDecimal() * it[1].toBigDecimal() }
            .sumOf { it }
            .toPlainString()
    }

    @Test
    fun esimeneVastus() {
        val input = """
            Kelluke: 1.64
            ForMe: 1.89
            Dynamit: 0.92
            odForMedFuDrDynamiti
        """.trimIndent()
        assertEquals("2.81", task(input.split("\n")))
    }
}



