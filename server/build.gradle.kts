import com.github.jengelman.gradle.plugins.shadow.ShadowExtension
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.springframework.boot") version "2.6.12"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
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

extra["springShellVersion"] = "2.1.1"

dependencies {
    // kotlin functionalities
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // spring boot, shell & telemetry
    implementation("org.springframework.shell:spring-shell-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // ECS library
    implementation("com.badlogicgames.ashley:ashley:1.7.4")

    // protocols & network
    implementation("com.nukkitx.protocol:bedrock-v486:2.9.5-SNAPSHOT")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.shell:spring-shell-dependencies:${property("springShellVersion")}")
    }
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

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = false
}

tasks.withType<ShadowJar> {
    isZip64 = true

    archiveClassifier.set("app")

    manifest {
        attributes["Main-Class"] = "com.github.bedrockecs.server.BedrockECS"
    }

    // Required for Spring
    // see: https://github.com/spring-projects/spring-boot/issues/1828#issuecomment-607352468
    // TODO: this does not work for spring boot 2.7.3, we had to downgrade to 2.6.12
    mergeServiceFiles()
    append("META-INF/spring.handlers")
    append("META-INF/spring.schemas")
    append("META-INF/spring.tooling")
    transform(
        PropertiesFileTransformer().apply {
            paths = listOf("META-INF/spring.factories")
            mergeStrategy = "append"
        }
    )
}

val pluginDepsShadowJar = tasks.register<ShadowJar>("pluginDepsShadowJar") {
    from(sourceSets["main"].output)
    configurations = listOf(project.configurations.runtimeClasspath.get())

    isZip64 = true

    archiveClassifier.set("plugin-deps")

    // we are using another jar for this because this make the jar not executable
    // Required to let IDEA kotlin highlighting work when used as dep
    // see: https://youtrack.jetbrains.com/issue/KT-25709/IDE-Unresolved-reference-from-fat-jar-dependency-with-Kotlin-runtime#focus=Comments-27-5180542.0-0
    exclude("**/*.kotlin_metadata")
    exclude("**/*.kotlin_module")
    exclude("**/*.kotlin_builtins")
}

// publishing //

publishing {
    publications {
        create<MavenPublication>("appWithPluginDep") {
            project.extensions.configure<ShadowExtension> {
                component(this@create)
                artifact(pluginDepsShadowJar)
            }
        }
    }
}
