package Proyecto.games.New_Lemmings_game.AbilitiesandStates;

import Proyecto.games.New_Lemmings_game.LemmingState;
import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

public class WaitingState implements LemmingState {
    

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.STOPING);
        lemmingEntity.setSpeed(0);
    }

    @Override
    public void onEnter(Lemming_Entity lemmingEntity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        // TODO Auto-generated method stub
    }
    
}
