plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.google.code.gson:gson:2.13.2")

    implementation("org.openjfx:javafx-controls:25.0.3")
    implementation("org.openjfx:javafx-fxml:25.0.3")
    implementation("org.openjfx:javafx-web:25.0.3")

    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

javafx {
    version = "25.0.3"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "it.unicam.cs.mpgc.rpg129092.Main"
}

