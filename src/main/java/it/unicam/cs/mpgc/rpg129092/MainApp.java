package it.unicam.cs.mpgc.rpg129092;

import javafx.application.Application;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129092.controller.scene.SceneController;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg129092.controller.battle.BattleController;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Inizializziamo lo SceneManager con lo Stage principale
        SceneController.getInstance().init(primaryStage);
        primaryStage.setTitle("RPG - Test Combattimento");

        Hero testHero = new Hero("Geralt");
        Enemy testEnemy = new Enemy("Goblin", 80, 18, 5);

        BattleController testEngine = new BattleController(testHero, testEnemy);

        // 4. Diciamo allo SceneManager di caricare direttamente la battagliadsew
        SceneController.getInstance().startBattle(testEngine);
    }

    public static void main(String[] args) {
        launch(args);
    }
}