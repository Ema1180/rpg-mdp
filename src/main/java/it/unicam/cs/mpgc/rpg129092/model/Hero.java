package it.unicam.cs.mpgc.rpg129092.model;

public class Hero extends AbstractCharacter {
    private int experience;
    private int level;

    public Hero(String name) {
        super(name, 100, 25);
        this.experience = 0;
        this.level = 1;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(this.attackpower);
    }

    public void gainExperience(int experience) {
        this.experience += experience;
    }

    public void checklevelUp() {
        this.level++;
        health += 25;
        attackpower += 10;
    }
}
