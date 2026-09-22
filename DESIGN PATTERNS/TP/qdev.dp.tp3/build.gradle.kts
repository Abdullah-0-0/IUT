plugins {
    kotlin("jvm") version "2.0.21"
}

group = "iut.info2"
version = "2026.1.0"

repositories {
    maven {
        url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/maven/")
    }
    maven {
        url = uri("https://nexus-prof.iut-nantes.univ-nantes.prive/repository/maven/")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("univ.nantes:univ.nantes.umlchecker:2.2.1")
    //implementation(kotlin("reflect"))
    //implementation("net.sourceforge.plantuml:plantuml:1.2023.5")
    //testImplementation("org.slf4j:slf4j-api:2.0.9")
    //implementation(files("libs/univ.nantes.umlchecker-2.1.jar"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}