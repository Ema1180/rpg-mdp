package it.unicam.cs.mpgc.rpg129092.controller.battle;

import it.unicam.cs.mpgc.rpg129092.controller.scene.SceneController;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.model.actions.CombatAction;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import it.unicam.cs.mpgc.rpg129092.model.actions.AttackAction;
import it.unicam.cs.mpgc.rpg129092.model.actions.CureAction;

public class BattleFXController {

    @FXML private ProgressBar heroHpBar;
    @FXML private ProgressBar enemyHpBar;
    @FXML private Label logLabel;
    @FXML private Button attackButton;
    @FXML private Button healButton;
    @FXML private Label heroName;
    @FXML private Label enemyName;

    private BattleController battleEngine;
    private GameState gameState;

    @FXML
    public void onAttackClick() {
        processHeroTurn(new AttackAction());
    }

    @FXML
    public void onHealClick() {
        processHeroTurn(new CureAction());
    }

    private void processHeroTurn(CombatAction action) {
        if (!battleEngine.isHeroTurn() || battleEngine.isBattleOver()) return;

        // Esegue la mossa dell'eroe
        String result = battleEngine.handleHeroAction(action);
        updateUI(result);

        // Controlla se l'eroe ha vinto
        if (battleEngine.isBattleOver()) {
            endMatch("VITTORIA!");
            return;
        }

        // 3. Disabilita l'input e passa il turno al nemico
        setControlsDisabled(true);

        PauseTransition enemyDelay = new PauseTransition(Duration.seconds(2.0));
        enemyDelay.setOnFinished(event -> {
            // Esegui la mossa dell'I.A. del nemico nel Model
            String enemyResult = battleEngine.handleEnemyTurn();
            updateUI(enemyResult);

            // Controlla se il nemico ha vinto
            if (battleEngine.isBattleOver()) {
                endMatch("Oggi vince il male...");
            } else {
                // Riapri i controlli per il nuovo turno dell'eroe
                setControlsDisabled(false);
                logLabel.setText(logLabel.getText() + "\nÈ di nuovo il tuo turno!");
            }
        });
        enemyDelay.play();
    }

    public void setBattleContext(BattleController engine, GameState gameState) {
        this.battleEngine = engine;
        this.gameState = gameState;
        updateUI(battleEngine.getEnemy().getName() + " ha voglia di botte!");
        heroName.setText(battleEngine.getHero().getName());
        enemyName.setText(battleEngine.getEnemy().getName());
    }

    private void updateUI(String logMessage) {
        logLabel.setText(logMessage);

        double heroProgress = (double) battleEngine.getHero().getHealth() / battleEngine.getHero().getMaxHealth();
        double enemyProgress = (double) battleEngine.getEnemy().getHealth() / battleEngine.getEnemy().getMaxHealth();

        heroHpBar.setProgress(heroProgress);
        enemyHpBar.setProgress(enemyProgress);

        if (battleEngine.getCurrentState() == BattleController.BattleState.VICTORY) {
            // Dopo 2 secondi dalla vittoria, torna automaticamente alla selezione nemici
            PauseTransition returnDelay = new PauseTransition(Duration.seconds(2));
            returnDelay.setOnFinished(event -> {
                // Passiamo il gameState allo SceneManager
                SceneController.getInstance().navigateToEnemySelection(gameState);
            });
            returnDelay.play();
        }
    }

    private void setControlsDisabled(boolean disabled) {
        attackButton.setDisable(disabled);
        healButton.setDisable(disabled);
    }

    private void endMatch(String finalMessage) {
        logLabel.setText(finalMessage);
        setControlsDisabled(true);
        // In futuro qui si potrà dire allo SceneManager di caricare la schermata di Game Over
    }
}