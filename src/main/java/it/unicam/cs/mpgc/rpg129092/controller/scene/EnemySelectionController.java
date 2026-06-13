package it.unicam.cs.mpgc.rpg129092.controller.scene;
import it.unicam.cs.mpgc.rpg129092.controller.saving.JsonSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;

import java.io.IOException;

public class EnemySelectionController {

    @FXML private ListView<AbstractCharacter> enemyListView;
    @FXML private Button startButton;

    private GameState gameState;

    @FXML
    public void initialize() {
        startButton.setDisable(true);

        // Gestisce l'attivazione del pulsante di scontro
        enemyListView.getSelectionModel().selectedItemProperty().addListener((newValue) -> {
            startButton.setDisable(newValue == null);
        });
    }

    /**
     * Questo metodo riceve lo stato del gioco e popola la grafica di conseguenza
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;

        if (gameState != null) {
            // Prendiamo la lista dei superstiti dal Model e la impacchettiamo per JavaFX
            ObservableList<AbstractCharacter> observableEnemies =
                    FXCollections.observableArrayList(gameState.getEnemies());

            enemyListView.setItems(observableEnemies);

            // Controllo extra: se non ci sono più nemici, l'utente ha vinto il gioco!
            if (gameState.getEnemies().isEmpty()) {
                // Gestisci la vittoria totale del gioco (es. mostra un messaggio o i crediti)
            }
        }
    }

    @FXML
    public void onStartClick() {
        AbstractCharacter selectedEnemy = enemyListView.getSelectionModel().getSelectedItem();
        if (selectedEnemy == null || gameState == null) return;
        // Avvia il combattimento passando lo stato corrente e il bersaglio scelto
        SceneController.getInstance().startBattle(gameState, selectedEnemy);
    }

    @FXML
    public void onSaveClick() throws IOException {
        JsonSaver saver = new JsonSaver();
        saver.save(gameState, "savegame.json");
    }
}