buildscript {
    repositories {
        mavenCentral()
        // for ktlint
        maven(url = "https://s01.oss.sonatype.org/content/repositories/releases") {
            mavenContent {
                releasesOnly()
            }
        }
    }
    dependencies {
        classpath("com.github.bedrockecs:datacompiler")
    }
}

// deps //

val ktlint by configurations.creating

dependencies {
    api(project(":vanilla:generated-base"))
    // datacompiler linting infrastructure
    ktlint("com.pinterest:ktlint:0.47.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

// codegen : datacompiler //

tasks {
    val compilerOutputDir = layout.projectDirectory.dir("src/main/kotlin")
    val linterDir = layout.projectDirectory.dir("src/main/kotlin/com/github/bedrockecs/vanilla/data/")
    compilerOutputDir.asFile.mkdir()
    register<com.github.bedrockecs.datacompiler.DataCompilerTask>("runDataCompiler") {
        group = "datacompiler"
        outputDir.set(compilerOutputDir)

        doLast {
            javaexec {
                classpath = ktlint
                mainClass.set("com.pinterest.ktlint.Main")
                // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
                args = listOf("-F", "$linterDir/**/*.kt")
                // see https://github.com/pinterest/ktlint/issues/1195 for why
                jvmArgs = listOf(
                    "--add-opens",
                    "\"java.base/java.util=ALL-UNNAMED\"",
                    "--add-opens",
                    "\"java.base/java.lang=ALL-UNNAMED\"",
                    "--add-exports=\"java.base/sun.nio.ch=ALL-UNNAMED\""
                )
            }
        }
    }
}
tasks.compileKotlin {
    dependsOn(tasks.getByName("runDataCompiler"))
}
