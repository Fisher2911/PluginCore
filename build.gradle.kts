plugins {
    id("java")
}

group = "io.github.fisher2911"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
//    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation("com.google.collections:google-collections:1.0")
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("com.google.auto.service:auto-service:1.0-rc5")
}