package it.unicam.cs.mpgc.rpg129092.controller.battle;

import it.unicam.cs.mpgc.rpg129092.model.actions.AttackAction;
import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;
import it.unicam.cs.mpgc.rpg129092.model.actions.CombatAction;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;

public class BattleController {
    public enum BattleState {
        HERO_TURN,
        ENEMY_TURN,
        VICTORY,
        DEFEAT
    }

    private final Hero hero;
    private final Enemy enemy;
    private BattleState currentState;

    public BattleController(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
        hero.setHP(hero.getMaxHealth());
        enemy.setHP(enemy.getMaxHealth()); //Curo sia l'eroe che il nemico per ricominciare il combattimento da capo
        this.currentState = BattleState.HERO_TURN; // Inizia l'eroe di default
    }

    /**
     * Il giocatore invoca questo metodo passando l'azione scelta.
     */
    public String handleHeroAction(CombatAction action) {
        if (currentState != BattleState.HERO_TURN) {
            return "Non è il tuo turno!";
        }

        // Eseguo l'azione sul nemico
        String logResult = action.execute(hero, enemy);

        // Verifico lo stato del combattimento
        if (enemy.isDead()) {
            currentState = BattleState.VICTORY;
            logResult = " " + enemy.getName() + " è stato gonfiato di schiaffi!";
            int oldLevel= hero.getLevel();
            hero.levelUp(enemy.getExp()); //Faccio salire di livello il giocatore
            if (hero.getLevel() > oldLevel) {
                logResult += "\n Sei salito di livello!";
            }
            return logResult;
        }

        // Passo il turno al nemico
        currentState = BattleState.ENEMY_TURN;
        return logResult;
    }

    /**
     * Fa avanzare l'I.A. del nemico.
     */
    public String handleEnemyTurn() {
        if (currentState != BattleState.ENEMY_TURN) {
            return "Non è il turno del nemico!";
        }
        // Il nemico attacca sempre (in futuro si potrebbero aggiungere altre possibilità)
        CombatAction enemyAction = new AttackAction();
        String logResult = enemyAction.execute(enemy, hero);

        if (hero.isDead()) {
            currentState = BattleState.DEFEAT;
            return logResult + "\n" + hero.getName() + " è caduto in battaglia... Niente lieto fine.";
        }
        // Torna il turno all'eroe
        currentState = BattleState.HERO_TURN;
        return logResult;
    }

    // Getters di stato
    public BattleState getCurrentState() { return currentState; }
    public AbstractCharacter getHero() { return hero; }
    public AbstractCharacter getEnemy() { return enemy; }
    public boolean isBattleOver() {
        return currentState == BattleState.VICTORY || currentState == BattleState.DEFEAT;
    }
    public boolean isHeroTurn() {
        return currentState == BattleState.HERO_TURN;
    }
}
