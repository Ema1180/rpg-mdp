package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Enemy extends AbstractCharacter {

    public Enemy (String name,int health, int attackpower) {
        super(name, health, attackpower);
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
