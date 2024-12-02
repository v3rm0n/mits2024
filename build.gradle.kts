import java.util.*

plugins {
    kotlin("jvm") version "2.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

fun removeImportsAndPackage(content: String): String =
    content.lines().filter { !it.contains("import") && !it.contains("package") }
        .joinToString("\n") {
            it.replace("<", "&lt;").replace(">", "&gt;")
        }

tasks {
    val generateHTML by creating {
        val items = mutableListOf<String>()
        file("$rootDir/src/main/kotlin/advent").list()!!.filter { it != "Advent.kt" }.map {
            copy {
                val name = it.removeSuffix(".kt")
                items.add(name)
                val content = file("$rootDir/src/main/kotlin/advent/$name.kt").readText()
                val testContent = file("$rootDir/src/test/kotlin/advent/${name}Test.kt").readText()
                from("$rootDir/src/main/resources/template.html") {
                    expand(
                        "CLASS" to removeImportsAndPackage(content),
                        "TESTCLASS" to removeImportsAndPackage(testContent)
                    )
                }
                into("build/docs")
                rename { "${name.lowercase(Locale.getDefault())}.html" }
            }
        }
        copy {
            from("$rootDir/src/main/resources/index.html") {
                expand(
                    "MENU" to items.sorted().joinToString { "\"${it}\"" }
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
