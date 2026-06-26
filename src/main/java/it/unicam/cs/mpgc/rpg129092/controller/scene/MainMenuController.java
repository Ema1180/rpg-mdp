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

    private SceneController sceneController;
    private GameSaver gameSaver;
    private final String savePath = "savegame.json";

    public MainMenuController() {
        this.gameSaver = new JsonSaver();
    }

    /**
     * Questo metodo permette a SceneController di iniettare se stesso
     * subito dopo il caricamento dell'FXML.
     */
    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
    public void setGameSaver(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }

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
        sceneController.navigateToNameSelection(gameState);
    }

    @FXML
    private void onContinueClick() {
        try {
            GameState gameState = gameSaver.load(savePath);

            if (gameState != null && sceneController != null) {
                System.out.println("Salvataggio caricato");
                sceneController.navigateToEnemySelection(gameState);
            }
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento");
        }
    }

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}
