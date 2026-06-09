package it.unicam.cs.mpgc.rpg129092.model;
import it.unicam.cs.mpgc.rpg129092.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;

import java.util.ArrayList;
import java.util.List;


public class GameState {
    private Hero hero;
    private List<Enemy> remainingEnemies;

    public  GameState(Hero hero) {
        this.hero = hero;
        this.remainingEnemies = new ArrayList<>();
        enemyList();

    }

    public void enemyList() {
        remainingEnemies.add(new Enemy("Re Goblin", 180, 15));
        remainingEnemies.add(new Enemy("Demone maggiore", 250, 25));
        remainingEnemies.add(new Enemy("Balrog", 350, 35));
    }

    public void removeEnemy(Enemy enemy) {
        for  (int i = 0; i < remainingEnemies.size(); i++) {
            if (enemy.getName().equals(remainingEnemies.get(i).getName())) {
                remainingEnemies.remove(i);
            }
        }
    }

    public List<Enemy> getRemainingEnemies() {
        return remainingEnemies;
    }

    public Hero getHero() {
        return hero;
    }

}
