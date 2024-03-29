plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {


    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.2.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.14.0")
    implementation ("org.apache.commons:commons-math3:3.6.1") //per le matrici (battlefield: campo di battaglia)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}