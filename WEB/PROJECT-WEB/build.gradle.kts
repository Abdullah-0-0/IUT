import org.gradle.internal.impldep.org.apache.http.client.methods.RequestBuilder.options

plugins {
    id("war")
}

group = "PROJECT-WEB"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.2.0-M2")
}

tasks.war{
    archiveFileName.set("premierServlet.war")
}
tasks.register("DeleteMyWar", Delete::class){
    delete("tomcat/webapps/premierServlet.war")
    System.out.println("Deleted avec succes")
}

tasks.register("copyMyWar", Copy::class) {
    doLast {
        println("Copy ${tasks.war.get().archiveFileName.get()} from \${project.buildDir}/libs to ./tomcat/webapps\")")
        println("Done")
    }
    from("${project.buildDir}/libs")
    into  ("./tomcat/webapps")
    include ("${tasks.war.get().archiveFileName.get()}")
}
//
//tasks.withType(JavaCompile) {
//    options.encoding = 'UTF-8'
//}
tasks.test {
    useJUnitPlatform()
}