package advent

import java.math.BigDecimal

class Esimene : Advent {

    override fun task(input: List<String>): String {
        return input.dropLast(1)
            .map { it.split(": ") }
            .associate { it[0] to BigDecimal(it[1]) }
            .map { allMatches(input.last(), it.key).count().toBigDecimal() * it.value }
            .sumOf { it }
            .toPlainString()
    }

    private tailrec fun allMatches(
        source: String,
        element: String,
        start: Int = 0,
        matches: List<Int> = emptyList()
    ): List<Int> {
        val firstMatch = source.indexOf(element, start)
        return if (firstMatch == -1) matches else allMatches(source, element, firstMatch + 1, matches + firstMatch)
    }
}

