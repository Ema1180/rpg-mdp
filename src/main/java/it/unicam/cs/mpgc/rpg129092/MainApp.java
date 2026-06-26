package it.unicam.cs.mpgc.rpg129092;

import it.unicam.cs.mpgc.rpg129092.controller.saving.GameSaver;
import it.unicam.cs.mpgc.rpg129092.controller.saving.JsonSaver;
import it.unicam.cs.mpgc.rpg129092.controller.scene.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mio RPG - Menu Principale");

        GameSaver gameSaver = new JsonSaver();
        SceneController sceneController = new SceneController(primaryStage,  gameSaver);
        sceneController.showMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}