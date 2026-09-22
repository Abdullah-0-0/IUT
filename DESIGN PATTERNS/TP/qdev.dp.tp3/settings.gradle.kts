pluginManagement {
    repositories {
        maven {
            url = uri("https://nexus-proxy.iut-nantes.univ-nantes.prive/repository/gradle/")
        }
        //gradlePluginPortal()        
    }
}

rootProject.name = "qdev.dp.tp3"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.8.0")
}
