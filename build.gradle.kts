plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "com.aesuriagasalazar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
    implementation(files("libs/AbsoluteLayout.jar"))
    implementation(files("libs/jcalendar-1.4.jar"))
    implementation(files("libs/jasperreports-6.0.0.jar"))
    implementation(files("libs/JTattoo-1.6.13.jar"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}