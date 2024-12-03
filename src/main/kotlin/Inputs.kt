object Inputs {
    val esimene = readFile("esimene.txt")
    val teine = readFile("teine.txt")
    val kolmas = readFile("kolmas.txt")
    private fun readFile(name: String) = Inputs::class.java.getResource(name).readText()
}
