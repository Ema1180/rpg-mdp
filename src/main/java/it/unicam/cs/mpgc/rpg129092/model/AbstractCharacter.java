package it.unicam.cs.mpgc.rpg129092.model;

public abstract class AbstractCharacter implements GameEntity{
    protected String name;
    protected int health;
    protected int attackpower;

    public AbstractCharacter (String name, int health, int attackpower) {
        this.name = name;
        this.health = health;
        this.attackpower = attackpower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    @Override
    public boolean isDead() {
        return health == 0;
    }

    public int getAttackpower() {
        return attackpower;
    }

    public abstract void attack(GameEntity target);




}
