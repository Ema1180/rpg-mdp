package it.unicam.cs.mpgc.rpg129092;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        //elementi grafici vari
        Label label = new Label("Se vedi questa finestra, JavaFX gira");
        Button button = new Button("Cliccami");

        //evetno di test
        button.setOnAction(event -> label.setText("Fin qua torna tutto"));


        // setup elementi
        VBox root = new VBox(15, label, button);
        root.setAlignment(Pos.CENTER);

        // setup scena
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Test JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}