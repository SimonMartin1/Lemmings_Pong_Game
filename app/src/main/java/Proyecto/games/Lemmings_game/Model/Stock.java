package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Ability, Integer> abilityCounts;
    private final Map<Ability, Integer> initialAbilityCounts;

    public Stock(Map<Ability, Integer> abilityCounts){
        this.abilityCounts = abilityCounts;
        this.initialAbilityCounts = new HashMap<>(abilityCounts);
    }

    public int getQuantityAbility(Ability a){
        return abilityCounts.get(a);
    }

    public boolean hasAbility(Ability a){
        return abilityCounts.get(a) > 0;
    }

    public void substractAbility(Ability a){
        abilityCounts.put(a, abilityCounts.get(a) - 1);
    }

    public void reset(){ this.abilityCounts = initialAbilityCounts; }
}
