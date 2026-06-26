package it.unicam.cs.mpgc.rpg129092.model.actions;

import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;

public class CureAction implements CombatAction {
    public String execute(AbstractCharacter caster, AbstractCharacter target) {
        if (caster.getHealth() == caster.getMaxHealth()) {
            return "Sei in piena salute!";
        }
        int newHealth = caster.getHealth() + 10 + caster.getHealth();
        if (caster.getMaxHealth() < newHealth) {
            caster.setHP(caster.getMaxHealth());
        }
        else {
            caster.setHP(newHealth);
        }

        return caster.getName() + " fa un incantesimo curativo ";
    }
}
