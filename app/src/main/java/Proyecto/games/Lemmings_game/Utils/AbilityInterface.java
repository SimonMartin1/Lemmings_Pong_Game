package Proyecto.games.Lemmings_game.Utils;

import Proyecto.games.Lemmings_game.Model.Ability;
import Proyecto.games.Lemmings_game.Model.LemmingModel;

public interface AbilityInterface {
    void apply(LemmingModel lemming, double delta);
    Ability getName();
}
