package it.unicam.cs.mpgc.rpg129092.model.characters;

public abstract class AbstractCharacter implements GameEntity {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackpower;;

    public AbstractCharacter (String name, int maxHealth, int attackpower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
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
    public int getMaxHealth() {return maxHealth;}

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

    @Override
    public void setHP(int hp) {
        health = hp;
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
