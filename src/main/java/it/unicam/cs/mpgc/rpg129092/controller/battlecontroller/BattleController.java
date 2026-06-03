package it.unicam.cs.mpgc.rpg129092.controller.battlecontroller;

import it.unicam.cs.mpgc.rpg129092.model.actions.AttackAction;
import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;
import it.unicam.cs.mpgc.rpg129092.model.actions.CombatAction;

public class BattleController {
    public class BattleLoopController {
        public enum BattleState {
            HERO_TURN,
            ENEMY_TURN,
            VICTORY,
            DEFEAT
        }

        private final AbstractCharacter hero;
        private final AbstractCharacter enemy;
        private BattleState currentState;

        public BattleLoopController(AbstractCharacter hero, AbstractCharacter enemy) {
            this.hero = hero;
            this.enemy = enemy;
            this.currentState = BattleState.HERO_TURN; // Inizia l'eroe di default
        }

        /**
         * Il giocatore (o la View per lui) invoca questo metodo passando l'azione scelta.
         */
        public String handleHeroAction(CombatAction action) {
            if (currentState != BattleState.HERO_TURN) {
                return "Non è il tuo turno!";
            }

            // Esegui l'azione sul nemico
            String logResult = action.execute(hero, enemy);

            // Verifica lo stato del combattimento
            if (enemy.isDead()) {
                currentState = BattleState.VICTORY;
                return logResult + "\n" + enemy.getName() + " è stato gonfiato di botte! ";
            }

            // Passa il turno al nemico
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
            // Semplice IA: Il nemico attacca sempre (in futuro si potrebbero aggiungere altre possibilità)
            CombatAction enemyAction = new AttackAction();
            String logResult = enemyAction.execute(enemy, hero);

            if (!hero.isDead()) {
                currentState = BattleState.DEFEAT;
                return logResult + "\n" + hero.getName() + " è caduto in battaglia... Niente lieto fine.";
            }

            // Torna il turno all'eroe
            currentState = BattleState.HERO_TURN;
            return logResult;
        }

        // Getters di stato utili sia per i test che per la futura UI
        public BattleState getCurrentState() { return currentState; }
        public AbstractCharacter getHero() { return hero; }
        public AbstractCharacter getEnemy() { return enemy; }
        public boolean isBattleOver() {
            return currentState == BattleState.VICTORY || currentState == BattleState.DEFEAT;
        }
    }
}
