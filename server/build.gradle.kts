import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by rootProject.extra
val springBootVersion: String by rootProject.extra

plugins {
    id("maven-publish")
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
}

group = rootProject.extra["group"]!!
version = rootProject.extra["version"]!!
java.sourceCompatibility = JavaVersion.VERSION_17

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
