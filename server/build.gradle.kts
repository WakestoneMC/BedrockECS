import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.6.21"
val springBootVersion = "2.6.12"

plugins {
    id("maven-publish")
    id("org.springframework.boot") version "2.6.12"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "com.github.bedrockecs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    // opencollab for Nukkit related stuff
    maven(url = "https://repo.opencollab.dev/maven-releases") {
        mavenContent {
            releasesOnly()
        }
    }
    maven(url = "https://repo.opencollab.dev/maven-snapshots") {
        mavenContent {
            snapshotsOnly()
        }
    }
}

// deps //

dependencies {
    // kotlin functionalities
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // spring boot, shell & telemetry
    api("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    api("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")

    // ECS library
    api("com.badlogicgames.ashley:ashley:1.7.4")

    // protocols & network
    api("com.nukkitx.protocol:bedrock-v486:2.9.5-SNAPSHOT")

    // testing
    testApi("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testApi("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

// compiling //

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
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
