val kotlinVersion by extra { "1.6.21" }
val springBootVersion by extra { "2.7.3" }

val group by extra { "com.github.bedrockecs" }
val version by extra { "0.0.1-SNAPSHOT" }

plugins {
    id("maven-publish")
    id("org.springframework.boot") version "2.6.12"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

allprojects {
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
}
