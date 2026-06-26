package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Hero extends AbstractCharacter {
    private int level;
    private int currentXP;

    public Hero(String name) {
        super(name, 100, 35);
        this.currentXP = 0;
        this.level = 1;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(this.attackpower);
    }

    public void levelUp(int experience) {
        this.currentXP += experience;
        if (currentXP >= level) {
            level++;
            maxHealth += 25;
            this.setHP(this.getMaxHealth());
            attackpower += 10;
            currentXP = 0;
        }
    }

    public int getLevel() {
        return this.level;
    }

}
