package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Hero extends AbstractCharacter {
    private int experience;
    private int level;

    public Hero(String name) {
        super(name, 100, 35);
        this.experience = 0;
        this.level = 1;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(this.attackpower);
    }

    public void levelUp() {
        this.level++;
        maxHealth += 25;
        attackpower += 10;
    }

}
