plugins {
    java
    application
}

group = "it.unicam.cs.mpgc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Questo dice a Gradle dove scaricare JavaFX e JUnit
}

dependencies {
    // Specifichiamo la tua piattaforma (windows, mac, o linux)
    val javafxVersion = "21"
    val platform = "win" // Cambia in "mac" se sei su Mac o "linux" se sei su Linux

    // Importiamo i moduli di JavaFX come normali librerie
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")

    // Supporto per i test unitari JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Libreria per il salvataggio dei dati (Persistenza)
    implementation("com.google.code.gson:gson:2.10.1")
}

application {
    // Sostituisci '123456' con la tua MATRICOLA reale
    mainClass.set("it.unicam.cs.mpgc.rpg129092.Main")
}

tasks.withType<Test> {
    useJUnitPlatform()
}