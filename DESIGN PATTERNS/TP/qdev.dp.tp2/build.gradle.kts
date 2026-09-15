plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "iut.info2"
version = "2026.1.0"


repositories {
    maven {
        url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/maven/")
    }
    //mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
