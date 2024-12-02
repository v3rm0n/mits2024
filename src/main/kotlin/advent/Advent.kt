package advent

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

interface Advent {
    fun task(input: List<String>): String
}

fun main() {
    println("*********MITS Advent 2024*********")
    ServiceLoader.load(Advent::class.java).forEach {
        val advent = it.javaClass.simpleName
        println("$advent vastus: ${it.task(readFile("/$advent"))}")
        println("**********************************")
    }
    println("************Nägemiseni!***********")
}

fun readFile(filename: String): List<String> =
    Files.readAllLines(Path.of(Advent::class.java.getResource(filename).toURI()))
