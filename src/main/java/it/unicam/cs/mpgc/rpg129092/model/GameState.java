package it.unicam.cs.mpgc.rpg129092.model;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;

import java.util.ArrayList;
import java.util.List;


public class GameState {
    private final Hero hero;
    private final List<Enemy> enemies;

    public  GameState(Hero hero) {
        this.hero = hero;
        this.enemies = new ArrayList<>();
        enemyList();

    }

    public void enemyList() {
        enemies.add(new Enemy("Goblin", 90, 7, 1));
        enemies.add(new Enemy("Lupo", 130, 13, 2));
        enemies.add(new Enemy("Koboldo",160 , 25, 3));
        enemies.add(new Enemy("Orso", 200, 35, 4));
        enemies.add(new Enemy("Troll", 300, 45, 5));
        enemies.add(new Enemy("Orco", 400, 55, 6));
        enemies.add(new Enemy("Vecna", 700, 56, 7));
        enemies.add(new Enemy("Balrog", 1000, 70, 10));
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Hero getHero() {
        return hero;
    }

}
