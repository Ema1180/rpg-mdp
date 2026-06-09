package it.unicam.cs.mpgc.rpg129092.controller.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;
import it.unicam.cs.mpgc.rpg129092.controller.battle.BattleController;
import it.unicam.cs.mpgc.rpg129092.view.BattleFXController;
import java.io.IOException;

public class SceneController {
    private static SceneController instance;
    private Stage primaryStage;

    public static SceneController getInstance() {
        if (instance == null) instance = new SceneController();
        return instance;
    }

    public void init(Stage stage) { this.primaryStage = stage; }

    public void showMainMenu() {
        try {
            // Carica l'FXML dedicato al menu principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
            Parent root = loader.load();

            // Imposta la nuova scena sullo stage principale
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Errore nel caricamento del Menu Principale: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Avvia la battaglia passando il GameState (Model globale)
     */
    public void startBattle(GameState gameState, AbstractCharacter enemy) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BattleScreen.fxml"));
            Parent root = loader.load();

            // Ipotizzo che nel tuo GameState ci sia un metodo getHero()
            BattleController engine = new BattleController(gameState.getHero(), enemy);

            BattleFXController controller = loader.getController();
            // Passiamo sia il motore di battaglia sia lo stato globale al controller grafico
            controller.setBattleContext(engine, gameState);

            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Torna indietro portando il GameState aggiornato
     */
    public void navigateToEnemySelection(GameState gameState) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EnemySelection.fxml"));
            Parent root = loader.load();

            EnemySelectionController controller = loader.getController();
            controller.setGameState(gameState); // Re-iniettiamo lo stato modificato

            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}