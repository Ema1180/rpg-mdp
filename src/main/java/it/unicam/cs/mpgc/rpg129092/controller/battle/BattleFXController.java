package it.unicam.cs.mpgc.rpg129092.controller.battle;

import it.unicam.cs.mpgc.rpg129092.model.actions.CombatAction;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import it.unicam.cs.mpgc.rpg129092.controller.battle.BattleController;
import it.unicam.cs.mpgc.rpg129092.model.actions.AttackAction;
import it.unicam.cs.mpgc.rpg129092.model.actions.CureAction;

public class BattleFXController {

    @FXML private ProgressBar heroHpBar;
    @FXML private ProgressBar enemyHpBar;
    @FXML private Label logLabel;
    @FXML private Button attackButton;
    @FXML private Button healButton;

    private BattleController battleEngine;

    public void setBattleEngine(BattleController battleEngine) {
        this.battleEngine = battleEngine;
        updateUI(battleEngine.getEnemy().getName() + " ha voglia di botte!");
    }

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

        // 1. Esegui la mossa dell'eroe
        String result = battleEngine.handleHeroAction(action);
        updateUI(result);

        // 2. Controlla se l'eroe ha vinto
        if (battleEngine.isBattleOver()) {
            endMatch("VITTORIA! Hai riconquistato Gerusalemme");
            return;
        }

        // 3. Disabilita l'input e passa il turno al nemico con un delay di 1.5 secondi
        setControlsDisabled(true);

        PauseTransition enemyDelay = new PauseTransition(Duration.seconds(3.0));
        enemyDelay.setOnFinished(event -> {
            // Esegui la mossa dell'I.A. del nemico nel Model
            String enemyResult = battleEngine.handleEnemyTurn();
            updateUI(enemyResult);

            // Controlla se il nemico ha vinto
            if (battleEngine.isBattleOver()) {
                endMatch("Anche oggi vince il sionismo...");
            } else {
                // Riapri i controlli per il nuovo turno dell'eroe
                setControlsDisabled(false);
                logLabel.setText(logLabel.getText() + "\nÈ di nuovo il tuo turno!");
            }
        });
        enemyDelay.play();
    }

    private void updateUI(String logMessage) {
        logLabel.setText(logMessage);

        double heroProgress = (double) battleEngine.getHero().getHealth() / battleEngine.getHero().getMaxHealth();
        double enemyProgress = (double) battleEngine.getEnemy().getHealth() / battleEngine.getEnemy().getMaxHealth();

        heroHpBar.setProgress(heroProgress);
        enemyHpBar.setProgress(enemyProgress);
    }

    private void setControlsDisabled(boolean disabled) {
        attackButton.setDisable(disabled);
        healButton.setDisable(disabled);
    }

    private void endMatch(String finalMessage) {
        logLabel.setText(finalMessage);
        setControlsDisabled(true);
        // In futuro qui potrai dire allo SceneManager di caricare la schermata di Game Over
    }
}