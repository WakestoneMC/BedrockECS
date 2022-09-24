import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.github.bedrockecs"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenLocal()
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

configurations {
    create("testServer")
}

dependencies {
    "testServer"("com.github.bedrockecs:server:0.0.1-SNAPSHOT")
    compileOnly("com.github.bedrockecs:server:0.0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

// compiling //

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

// testing //

tasks.test {
    useJUnitPlatform()
}

tasks.create<JavaExec>("runTestServer") {
    classpath = sourceSets["main"].runtimeClasspath + configurations["testServer"]

    mainClass.set("com.github.bedrockecs.server.BedrockECS")

    workingDir("../becs")

    val manifest = sourceSets["main"].output.resourcesDir!!.toPath().resolve("bedrockecs.yaml")
    args = listOf("--classpathPluginManifest=$manifest")
}

// packaging //

tasks.shadowJar {
    dependencies {
        exclude(dependency("com.github.bedrockecs:server:0.0.1-SNAPSHOT"))
    }
}
