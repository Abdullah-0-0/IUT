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
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.slf4j:slf4j-simple:1.7.30")

    testImplementation(kotlin("reflect"))
    testImplementation("net.sourceforge.plantuml:plantuml:1.2023.5")
    //implementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("ch.qos.logback:logback-classic:1.5.20") {
        exclude(group = "org.glassfish", module = "jakarta.el")
    }
    testImplementation("univ.nantes:univ.nantes.umlchecker:2.2.1")

    implementation("org.apache.commons:commons-csv:1.5") // Apache Commons CSV
    implementation("com.jsoizo:kotlin-csv-jvm:1.10.0")      // Kotlin CSV
    implementation("org.xerial:sqlite-jdbc:3.39.3.0")       // SQLite JDBC Driver
    implementation("com.h2database:h2:2.1.214")             // H2 Database JDBC Driver
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.0") // MariaDB JDBC Driver

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}