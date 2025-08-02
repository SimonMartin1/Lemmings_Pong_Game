package Proyecto.games.New_Lemmings_game.utils;


import Proyecto.games.New_Lemmings_game.Lemming_Entity;


public abstract class AbilityClass{

    private final Ability name;

    public AbilityClass(Ability name){
        this.name = name;
    }

    public abstract void apply(Lemming_Entity lemming, double delta);
    public Ability getName(){ return name; }
}
