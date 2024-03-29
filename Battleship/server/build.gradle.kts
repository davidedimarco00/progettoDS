plugins {
    java
    `java-library`
    application
}

repositories {
    mavenCentral()
}


group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {

    api(project(":common"))
    api(project(":presentation"))

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation ("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("com.google.code.gson:gson:2.10")
    annotationProcessor("io.javalin.community.openapi:openapi-annotation-processor:5.1.3")
    implementation("io.javalin.community.openapi:javalin-openapi-plugin:5.1.3") // for /openapi route with JSON scheme
    implementation("io.javalin.community.openapi:javalin-swagger-plugin:5.1.3") // for Swagger UI
    implementation ("com.j2html:j2html:1.6.0")
    implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.14.0")


}

application {
    mainClass.set("server.BattleshipService")
}