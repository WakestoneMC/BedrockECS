val kotlinDataFrameVersion = "0.8.1"
plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlinx.dataframe") version "0.8.1"
    application
}

group = "com.github.bedrockecs"
version = "0.1.0-SNAPSHOT"

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

dependencies {
    // utils
    implementation("com.google.guava:guava:31.1-jre")

    // nbt parsing
    implementation("com.github.bedrockecs:common")

    // json parsing
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // logging
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // data wrangling
    implementation("org.jetbrains.kotlinx:dataframe:$kotlinDataFrameVersion")

    // codegen
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("org.reflections:reflections:0.10.2")

    // integrate with gradle
    implementation(gradleApi())

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

// source set for manual playground //

sourceSets {
    create("playground") {
        compileClasspath += sourceSets.main.get().output
        compileClasspath += sourceSets.main.get().compileClasspath
        runtimeClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().runtimeClasspath
    }
}

// let IDE pickup generated code
kotlin.sourceSets.getByName("main").kotlin.srcDir("build/generated/ksp/main/kotlin/")

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
