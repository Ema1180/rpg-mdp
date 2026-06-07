package it.unicam.cs.mpgc.rpg129092.controller.scene;

import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.view.BattleFXController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129092.controller.battle.BattleController;
import java.io.IOException;

public class SceneController {
    private static SceneController instance;
    private Stage primaryStage;

    private SceneController() {
        // Costruttore privato per il pattern Singleton (opzionale, ma comodo per accedervi ovunque)
    }

    public static SceneController getInstance() {
        if (instance == null) {
            instance = new SceneController();
        }
        return instance;
    }

    public void init(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Cambia la scena caricando un FXML generico.
     */
    public void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo speciale per avviare il combattimento, perché dobbiamo "iniettare"
     * il BattleEngine (Model) dentro il BattleFXController (Controller grafico).
     */
    public void startBattle(BattleController battleController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BattleScreen.fxml"));
            Parent root = loader.load();

            // Recuperiamo il controller agganciato all'FXML tramite SceneBuilder
            BattleFXController controller = loader.getController();

            // Passiamo il motore logico al controller grafico (Dependency Injection)
            controller.setBattleEngine(battleController);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToEnemySelection(GameState gameState) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EnemySelection.fxml"));
            Parent root = loader.load();

            EnemySelectionController controller = loader.getController();
            controller.setGameState(gameState);

            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}