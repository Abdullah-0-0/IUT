plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.serialization") version "2.1.21"
}

group = "iut.info.but2"
version = "2026.1.0"

repositories {
    maven {
        url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/maven/")
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.21")
    implementation("io.ktor:ktor-client-core:3.3.1")
    implementation("io.ktor:ktor-client-cio:3.3.1")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
    testImplementation("net.sourceforge.plantuml:plantuml:1.2025.2")
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