package it.unicam.cs.mpgc.rpg129092.controller.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129092.view.*;
import it.unicam.cs.mpgc.rpg129092.view.ViewLoader.ViewResult;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.controller.battle.*;
import it.unicam.cs.mpgc.rpg129092.controller.saving.GameSaver;

public class SceneController {

    private final Stage primaryStage;
    private final GameSaver gameSaver;

    public SceneController(Stage primaryStage, GameSaver gameSaver) {
        this.primaryStage = primaryStage;
        this.gameSaver = gameSaver;
    }

    public void showMainMenu() {
        AssetManager.loadMainMenuFont();
        ViewResult<MainMenuController> result = ViewLoader.load(Menu.MAIN_MENU);

        MainMenuController controller = result.fxController();
        if (controller != null) {
            controller.setSceneController(this);
            controller.setGameSaver(gameSaver);
        }
        cambiaScena(result.root(), true);
    }

    public void navigateToEnemySelection(GameState gameState) {
        ViewResult<EnemySelectionController> result = ViewLoader.load(Menu.ENEMY_SELECTION);

        EnemySelectionController controller = result.fxController();
        if (controller != null) {
            controller.setGameState(gameState);
            controller.setSceneController(this);
            controller.setGameSaver(gameSaver);
        }
        cambiaScena(result.root(), true);
    }

    public void startBattle(GameState gameState, BattleController battleEngine) {
        ViewResult<BattleFXController> result = ViewLoader.load(Menu.BATTLE);
        BattleFXController fxController = result.fxController();
        if (fxController != null) {
            fxController.setBattleContext(battleEngine, gameState);
            fxController.setSceneController(this);
        }
        cambiaScena(result.root(), true);
    }

    public void navigateToNameSelection(GameState gameState) {
        AssetManager.loadGenericFont();
        ViewResult<NameSelectionController> result = ViewLoader.load(Menu.NAME_SELECTION);
        if (result.fxController() != null) {
            result.fxController().setSceneController(this);
        }
        cambiaScena(result.root(), true);
    }

    private void cambiaScena(Parent root, boolean applicaCss) {
        Scene scene = new Scene(root);
        if (applicaCss) {
            AssetManager.applyStile(scene);
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}