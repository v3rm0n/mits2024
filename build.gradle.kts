plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))
    implementation(kotlin("test"))
    implementation(kotlin("test-junit"))
}

tasks {
    val generateHTML by creating {
        val items = mutableListOf<String>()
        file("$rootDir/src/main/kotlin").list()!!.filter { it.startsWith("Advent") }.map {
            copy {
                val name = it.removeSuffix(".kt")
                items.add(name)
                val content = file("$rootDir/src/main/kotlin/$name.kt").readText()
                from("$rootDir/src/main/resources/template.html") {
                    expand(
                        "CLASS" to content
                            .replace("<", "&lt;").replace(">", "&gt;"),
                        "DATA" to file("$rootDir/src/main/resources").list()!!.filter { it.endsWith(".txt") }
                            .map { it to file("$rootDir/src/main/resources/$it").readText() }
                            .joinToString("\n") { (name, content) -> "val ${name.removeSuffix(".txt")} = ${"\"\"\""}$content${"\"\"\""}" }
                    )
                }
                into("build/docs")
                rename { "${name.lowercase()}.html" }
            }
        }
        copy {
            from("$rootDir/src/main/resources/index.html") {
                expand(
                    "BLOCKS" to file("build/docs").list()!!
                        .filter { it.startsWith("advent") }
                        .sortedBy { it }
                        .joinToString("") { file("build/docs/$it").readText() }
                )
            }
            into("build/docs")
        }
    }

    processResources {
        dependsOn(generateHTML)
    }
}
