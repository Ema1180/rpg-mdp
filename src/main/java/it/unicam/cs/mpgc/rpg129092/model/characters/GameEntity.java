package it.unicam.cs.mpgc.rpg129092.model.characters;

public interface GameEntity {
    String getName();
    int getHealth();
    int getMaxHealth();
    void takeDamage(int damage);
    void setHP(int hp);
    boolean isDead();
}

