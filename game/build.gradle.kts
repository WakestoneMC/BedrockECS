import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by rootProject.extra
val kotlinCoroutineVersion: String by rootProject.extra
val kotlinSerializationVersion: String by rootProject.extra

plugins {
    id("org.springframework.boot").apply(false)
    id("io.spring.dependency-management")
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
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutineVersion")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    // spring
    implementation("org.springframework.boot:spring-boot-starter")

    // ECS library
    api("com.badlogicgames.ashley:ashley:1.7.4")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
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
