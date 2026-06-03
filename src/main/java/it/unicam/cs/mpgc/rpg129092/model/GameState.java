package it.unicam.cs.mpgc.rpg129092.model;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;

import java.util.List;


public class GameState {
    private Hero hero;
    private List<Enemy> remainingEnemies;
    private int currentLevel;

    public  GameState(Hero hero, List<Enemy> remaining, int currentLevel) {
        this.hero = hero;
        this.remainingEnemies = remaining;
        this.currentLevel = currentLevel;
    }

    public Hero getHero() {
        return hero;
    }

}
