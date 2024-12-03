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
        copy {
            from("$rootDir/src/main/resources/index.html") {
                expand(
                    "CLASSES" to file("$rootDir/src/main/kotlin/Advent.kt").readText()
                        .replace("<", "&lt;").replace(">", "&gt;"),
                    "DATA" to file("$rootDir/src/main/resources").list()!!.filter { it.endsWith(".txt") }
                        .map { it to file("$rootDir/src/main/resources/$it").readText() }
                        .joinToString("\n") { (name, content) -> "val ${name.removeSuffix(".txt")} = ${"\"\"\""}$content${"\"\"\""}" }
                )
            }
            into("build/docs")
        }
    }

    processResources {
        dependsOn(generateHTML)
    }
}
