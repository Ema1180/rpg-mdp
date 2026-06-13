package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Enemy extends AbstractCharacter {
    private int exp;


    public Enemy (String name,int health, int attackpower, int exp) {
        super(name, health, attackpower);
        this.exp = exp;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(attackpower);
    }

    @Override
    public String toString() {
        return this.getName() + " ATK: " + this.getAttackpower();
    }
}
