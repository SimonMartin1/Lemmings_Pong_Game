package Proyecto.games.New_Lemmings_game.States;


import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.utils.SoundManager;

public interface LemmingState {
    void update(Lemming_Entity lemmingEntity, double delta, SoundManager soundManager);
    void onEnter(Lemming_Entity lemmingEntity, SoundManager soundManager);
    void onExit(Lemming_Entity lemmingEntity, SoundManager soundManager);
}
