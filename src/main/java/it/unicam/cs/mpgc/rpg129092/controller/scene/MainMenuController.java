package it.unicam.cs.mpgc.rpg129092.controller.scene;

import it.unicam.cs.mpgc.rpg129092.controller.saving.GameSaver;
import it.unicam.cs.mpgc.rpg129092.controller.saving.JsonSaver;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {

    @FXML private Button continueButton;

    private final GameSaver gameSaver = new JsonSaver();
    private final String savePath = "savegame.json";

    @FXML
    private void initialize() {
        java.io.File f = new java.io.File(savePath);
        if (!f.exists()) {
            continueButton.setDisable(true);
        }
    }

    @FXML
    private void onNewGameClick() {
        Hero eroe = new Hero("Geralt");

        GameState gameState = new GameState(eroe);

        SceneController.getInstance().navigateToEnemySelection(gameState);
    }

    @FXML
    private void onContinueClick() {
        try {
            GameState gameState = gameSaver.load(savePath);

            if (gameState != null) {
                System.out.println("Salvataggio caricato");
                SceneController.getInstance().navigateToEnemySelection(gameState);
            }
        }  catch (IOException e) {
            System.out.println("Errore durante il caricamento");
        }
    }
}
