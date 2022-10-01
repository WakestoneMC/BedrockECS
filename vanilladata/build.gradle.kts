val kotlinVersion: String by rootProject.extra

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = rootProject.extra["group"]!!
version = rootProject.extra["version"]!!
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
