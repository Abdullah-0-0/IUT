plugins {
    kotlin("jvm") version "2.1.10"
}

group = "iut.info.but2"
version = "2026.1.0"

repositories {
    maven {
        url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/maven/")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
    testImplementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("ch.qos.logback:logback-classic:1.5.20") {
        exclude(group = "org.glassfish", module = "jakarta.el")
    }
    testImplementation("univ.nantes:univ.nantes.umlchecker:2.2.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}