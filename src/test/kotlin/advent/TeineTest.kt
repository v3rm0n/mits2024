package advent

import org.junit.Test
import kotlin.test.assertEquals

class TeineTest {
    private val teine = Teine()

    @Test
    fun `test vastus on 22`() {
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
        assertEquals("22", teine.task(input.split("\n")))
    }
}
//
