package it.unicam.cs.mpgc.rpg129092.model.actions;

import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;

public class AttackAction implements CombatAction {
    @Override
    public String execute(AbstractCharacter caster, AbstractCharacter target) {
        int damage = caster.getAttackpower();
        target.takeDamage(damage);

        return caster.getName() + " ha attaccato " + target.getName() + " infliggendo " + damage + " danni";
    }
}
