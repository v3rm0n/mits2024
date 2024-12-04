import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class Advent3 {
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