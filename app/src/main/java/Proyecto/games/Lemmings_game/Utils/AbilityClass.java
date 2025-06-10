package Proyecto.games.Lemmings_game.Utils;

import Proyecto.games.Lemmings_game.Model.Ability;
import Proyecto.games.Lemmings_game.Model.LemmingModel;

public abstract class AbilityClass{

    private final Ability name;

    public AbilityClass(Ability name){
        this.name = name;
    }

    public void apply(LemmingModel lemming, double delta){}
    public Ability getName(){ return name; }
}
