val kotlinVersion by extra { "1.6.21" }
val kotlinCoroutineVersion by extra { "1.6.4" }
val kotlinSerializationVersion by extra { "1.4.0" }

val group by extra { "com.github.bedrockecs" }
val version by extra { "0.0.1-SNAPSHOT" }

plugins {
    id("maven-publish")
    id("org.springframework.boot") version "2.6.12"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

allprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    group = rootProject.extra["group"]!!
    version = rootProject.extra["version"]!!
    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
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
        // for ktlint
        maven(url = "https://s01.oss.sonatype.org/content/repositories/releases") {
            mavenContent {
                releasesOnly()
            }
        }
    }
    dependencies {
        // kotlin functionalities
        api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutineVersion")
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

        // spring
        implementation("org.springframework.boot:spring-boot-starter")

        // testing
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    }

    // compiling //

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    // testing //

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
