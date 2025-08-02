package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

public class DeadState implements LemmingState {

    private int tickCounter = 0;
    private int currentFrame = 0;
    private static final int TICKS_PER_FRAME = 6;
    private static final int TOTAL_FRAMES = 8;
    private boolean finished = false;

    @Override
    
    public void onEnter(Lemming_Entity lemmingEntity) {
        lemmingEntity.setAnimationState(LemmingAnimationState.EXPLANTING_FALL);
        tickCounter = 0;
        currentFrame = 0;
        finished = false;
    }

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        if (finished) return;

        tickCounter++;

        if (tickCounter >= TICKS_PER_FRAME) {
            tickCounter = 0;
            currentFrame++;

            if (currentFrame >= TOTAL_FRAMES) {
                finished = true;
            }
        }
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        // No hace falta nada por ahora
    }

    public boolean isActive() {
        return !finished;
    }
}
