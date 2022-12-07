import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by rootProject.extra
val kotlinCoroutineVersion: String by rootProject.extra
val kotlinSerializationVersion: String by rootProject.extra

plugins {
    id("maven-publish")
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
}

buildscript {
    dependencies {
        classpath("com.github.bedrockecs:datacompiler")
    }
}

group = rootProject.extra["group"]!!
version = rootProject.extra["version"]!!
java.sourceCompatibility = JavaVersion.VERSION_17

// deps //

val ktlint by configurations.creating

dependencies {
    // kotlin functionalities
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutineVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    // spring boot, shell & telemetry
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-actuator")

    // ECS library
    api("com.badlogicgames.ashley:ashley:1.7.4")

    // protocols & network
    api("com.nukkitx.protocol:bedrock-v486:2.9.5-SNAPSHOT")

    // testing
    testApi("org.springframework.boot:spring-boot-starter-test")
    testApi("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // subproject deps
    api(project(":plugin"))

    // datacompiler linting infrastructure
    ktlint("com.pinterest:ktlint:0.47.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

// compiling //

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
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

// testing //

tasks.withType<Test> {
    useJUnitPlatform()
}

// packaging //

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.jar {
    archiveClassifier.set("")
}

tasks.bootJar {
    archiveClassifier.set("app")
}

// publishing //

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks.findByName("bootJar"))
        }
    }
}
