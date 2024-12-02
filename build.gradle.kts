plugins {
    application
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

fun removeImportsAndPackage(content: String): String =
    content.lines().filter { !it.contains("import") && !it.contains("package") }
        .joinToString("\n") {
            it.replace("<", "&lt;").replace(">", "&gt;")
        }

tasks {
    val generateHTML by creating {
        val items =
            file("$rootDir/src/main/kotlin/advent").list()!!.filter { it != "Advent.kt" }.map { it.removeSuffix(".kt") }
        copy {
            from("$rootDir/src/main/resources/index.html") {
                expand(
                    "CLASSES" to items.sorted().map { file("$rootDir/src/main/kotlin/advent/$it.kt").readText() }
                        .joinToString("") { removeImportsAndPackage(it) }
                        .replace("\n\n+".toRegex(), "\n\n")
                )
            }
            into("build/docs")
        }
    }


    processResources {
        dependsOn(generateHTML)
    }
}

application {
    mainClass.set("advent.AdventKt")
}
