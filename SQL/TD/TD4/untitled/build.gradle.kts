plugins {
    kotlin("jvm") version "2.3.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.oracle.database.jdbc:ojdbc11:23.26.4.0.0")
    implementation(files("libs/ojdbc8.jar"))
    implementation(files("libs/orai18n.jar"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}