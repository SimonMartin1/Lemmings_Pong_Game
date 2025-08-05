package Proyecto.games.New_Lemmings_game.Abilities;


import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.Ability;


public abstract class AbilityClass{

    private final Ability name;

    public AbilityClass(Ability name){
        this.name = name;
    }

    public abstract void apply(Lemming_Entity lemming, double delta);
    public abstract boolean canUseAbility(Lemming_Entity lemming);

    public Ability getName(){ return name; }
}
