package it.unicam.cs.mpgc.rpg129092;

import it.unicam.cs.mpgc.rpg129092.controller.scene.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Inizializziamo lo SceneManager
        SceneController.getInstance().init(primaryStage);
        primaryStage.setTitle("Mio RPG - Menu Principale");

        // Carichiamo il menu principale come prima scena dell'applicazione
        SceneController.getInstance().showMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}