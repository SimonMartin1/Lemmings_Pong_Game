package Proyecto.games.Lemmings_game.Model;

public interface AbilityModel {
    void apply(LemmingModel lemming, double delta);
    Ability getName();
}
