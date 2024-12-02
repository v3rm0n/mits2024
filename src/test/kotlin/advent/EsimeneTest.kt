package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class EsimeneTest {

    private val esimene = Esimene()

    @Test
    fun `test vastus on 2,81`() {
        val esimene = Esimene()
        val input = """
            Kelluke: 1.64
            ForMe: 1.89
            Dynamit: 0.92
            odForMedFuDrDynamiti
        """.trimIndent()
        assertEquals("2.81", esimene.task(input.split("\n")))
    }
}
