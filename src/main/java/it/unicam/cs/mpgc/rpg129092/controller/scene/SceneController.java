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


    /**
     * Apre il menu principale all'avvio del gioco
     */
    public void showMainMenu() {
        try {
            // Carica l'FXML del menu principale
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
     * Passo all'interfaccia di combattimento mantenendo lo stato di gioco
     * @param gameState
     * @param enemy
     */
    public void startBattle(GameState gameState, AbstractCharacter enemy) {
        try {
            //Imposta l'FXML dell'interfaccia di combattimento
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BattleScreen.fxml"));
            Parent root = loader.load();

            //Imposto la logica di combattimento
            BattleController engine = new BattleController(gameState.getHero(), enemy);

            BattleFXController controller = loader.getController();

            // Passo sia il motore di battaglia sia lo stato di gioco
            controller.setBattleContext(engine, gameState);

            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Passo al menu di selezione del nemico dopo un combattimento
     * @param gameState
     */
    public void navigateToEnemySelection(GameState gameState) {
        try {
            //Carico l'FXML del menu di selezione
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EnemySelection.fxml"));
            Parent root = loader.load();

            EnemySelectionController controller = loader.getController();
            controller.setGameState(gameState); //Aggiorno lo stato di gioco globale

            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}