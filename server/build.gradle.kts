plugins {
    id("maven-publish")
    id("org.springframework.boot")
}

dependencies {
    // spring boot, shell & telemetry
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-actuator")

    // subproject deps
    api(project(":plugin"))
    api(project(":filesystem"))
    api(project(":threading"))
    api(project(":game"))
    api(project(":comm"))
    api(project(":storegen"))
    implementation("com.github.bedrockecs:common")
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
