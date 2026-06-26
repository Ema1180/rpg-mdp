package it.unicam.cs.mpgc.rpg129092.controller.scene;

import it.unicam.cs.mpgc.rpg129092.controller.saving.GameSaver;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import it.unicam.cs.mpgc.rpg129092.model.GameState;

import java.io.IOException;

public class EnemySelectionController {

    @FXML private ListView<Enemy> enemyListView;
    @FXML private Button startButton;

    // Riferimento locale allo SceneController iniettato dall'esterno
    private SceneController sceneController;
    private GameState gameState;

    //Riferimento al GameSaver per la persistenza
    private  GameSaver gameSaver;
    private final String savePath = "savegame.json";

    /**
     * Questo metodo permette a SceneController di iniettare se stesso
     * subito dopo il caricamento della vista.
     */
    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    public void initialize() {
        startButton.setDisable(true);

        // Gestisce l'attivazione del pulsante di scontro
        enemyListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            startButton.setDisable(newValue == null);
        });
    }

    /**
     * Questo metodo riceve lo stato del gioco e popola la grafica di conseguenza
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;

        if (gameState != null) {
            ObservableList<Enemy> observableEnemies = FXCollections.observableArrayList(gameState.getEnemies());

            enemyListView.setItems(observableEnemies);
        }
    }

    @FXML
    public void onStartClick() {
        Enemy selectedEnemy = enemyListView.getSelectionModel().getSelectedItem();
        if (selectedEnemy == null || gameState == null) return;

        // Utilizziamo l'istanza dello SceneController iniettata
        if (sceneController != null) {
            var battleEngine = new it.unicam.cs.mpgc.rpg129092.controller.battle.BattleController(gameState.getHero(), selectedEnemy);
            sceneController.startBattle(gameState, battleEngine);
        }
    }

    @FXML
    public void onSaveClick() {
        try {
            if (gameState != null && gameSaver != null) {
                gameSaver.save(gameState, savePath);
                System.out.println("Gioco salvato con successo.");
            }
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public void setGameSaver(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }
}