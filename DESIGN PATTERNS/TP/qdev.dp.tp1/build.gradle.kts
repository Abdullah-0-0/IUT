plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "iut.info2"
version = "2026.1.0"


repositories {
    maven {
        url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/maven/")
        //isAllowInsecureProtocol = true
    }
    //mavenCentral()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
    implementation(files("libs/univ.nantes.umlchecker-2.1.jar"))
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
