package Proyecto.games.New_Lemmings_game;


public interface LemmingState {
    void update(Lemming_Entity lemmingEntity, double delta);
    void onEnter(Lemming_Entity lemmingEntity); // opcional
    void onExit(Lemming_Entity lemmingEntity);  // opcional
}
