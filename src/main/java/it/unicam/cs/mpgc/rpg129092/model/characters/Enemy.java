package it.unicam.cs.mpgc.rpg129092.model.characters;

public class Enemy extends AbstractCharacter {
    private int expreward;

    public Enemy (String name,int health, int attackpower,  int expreward) {
        super(name, health, attackpower);
        this.expreward = expreward;
    }

    @Override
    public void attack(GameEntity target) {
        target.takeDamage(attackpower);
    }

    public int  getExpreward() {
        return expreward;
    }
}
