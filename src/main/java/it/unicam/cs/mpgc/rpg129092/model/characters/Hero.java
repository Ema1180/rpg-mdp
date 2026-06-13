package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Hero extends AbstractCharacter {
    private int exp;
    private int level;

    public Hero(String name) {
        super(name, 100, 35);
        this.exp = 0;
        this.level = 1;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(this.attackpower);
    }

    public void levelUp() {
        if (exp >= level) {
            level++;
            maxHealth += 25;
            this.setHP(this.getMaxHealth());
            attackpower += 10;
            exp = 0;
        }
    }

}
