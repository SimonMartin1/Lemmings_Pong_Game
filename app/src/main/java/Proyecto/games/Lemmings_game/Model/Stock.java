package Proyecto.games.Lemmings_game.Model;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Ability, Integer> abilityCounts;

    public Stock(Map<Ability, Integer> abilityCounts){
        this.abilityCounts = abilityCounts;
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


}
