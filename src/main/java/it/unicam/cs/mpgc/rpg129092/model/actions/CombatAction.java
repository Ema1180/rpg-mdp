package it.unicam.cs.mpgc.rpg129092.model.actions;

import it.unicam.cs.mpgc.rpg129092.model.characters.AbstractCharacter;

public interface CombatAction {
    String execute(AbstractCharacter caster, AbstractCharacter target);
}
