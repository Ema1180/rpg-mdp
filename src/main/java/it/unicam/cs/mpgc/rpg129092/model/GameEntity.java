package it.unicam.cs.mpgc.rpg129092.model;

public interface GameEntity {
    String getName();
    int getHealth();
    void takeDamage(int damage);
    boolean isDead();
}

