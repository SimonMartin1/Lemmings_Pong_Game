package Proyecto.games.New_Lemmings_game.States;

import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import Proyecto.games.utils.SoundManager;

public class WaitingState implements LemmingState {
    

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta, SoundManager soundManager) {
        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.STOPING);
        lemmingEntity.setSpeed(0);
    }

    @Override
    public void onEnter(Lemming_Entity lemmingEntity, SoundManager soundManager) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity, SoundManager soundManager) {
        // TODO Auto-generated method stub
    }
    
}
