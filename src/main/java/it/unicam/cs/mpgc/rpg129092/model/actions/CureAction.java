package it.unicam.cs.mpgc.rpg129092.model.actions;

import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;

public class CureAction implements CombatAction {
    public String execute(AbstractCharacter caster, AbstractCharacter target) {
        caster.setHP(caster.getHealth() + caster.getMaxHealth()/10);

        return caster.getName() + " fa un incantesimo curativo ";
    }
}
