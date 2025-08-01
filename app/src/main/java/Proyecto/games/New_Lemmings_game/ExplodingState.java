package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

public class ExplodingState implements LemmingState {

    private int tickCounter = 0;
    private final int explosionDuration = 60; // duración de la explosión en ticks

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        tickCounter++;

        // Mientras explota, mostrás la animación de explosión
        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);

        // Cuando termina la animación, lo sacás del juego
        if (tickCounter >= explosionDuration) {
            lemmingEntity.setState(new DeadState());
        }
    }

    @Override
    public void onEnter(Lemming_Entity lemmingEntity) {
        tickCounter = 0;
        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        // Por lo general nada, porque lo borrás antes
    }
}
