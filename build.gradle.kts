plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks{
    named<Jar>("jar") {
        archiveBaseName.set("RandomRarity") // Set the JAR base name
        archiveVersion.set("1.0.0") // Set the version
        destinationDirectory.set(file("C:\\Alle Plugins\\Server\\Spare\\1.21.1\\plugins")) // Custom output directory
    }
}