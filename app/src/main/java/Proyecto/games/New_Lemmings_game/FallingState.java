package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class FallingState implements LemmingState {

    private int startFallTileY;

    @Override
    public void onEnter(Lemming_Entity lemmingEntity) {
        lemmingEntity.startFalling();
        startFallTileY = lemmingEntity.getTileY();  // guardás desde dónde empezó a caer
        lemmingEntity.setAnimationState(LemmingAnimationState.FALLING);
    }

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        int tileX = lemmingEntity.getTileX();
        int tileY = lemmingEntity.getTileY();
        
        // lo hacés caer
        lemmingEntity.setY(lemmingEntity.getY() + lemmingEntity.getSpeed());

        //chequeamos el piso de abajo
        Color tileBelow = lemmingEntity.getLevel().getMap().getTileColor(tileY + 1, tileX);

        // Si el color debajo NO es negro, es porque hay piso
        if (!Color.BLACK.equals(tileBelow)) {
            int fallDistance = tileY - startFallTileY;
        
            if (fallDistance > 20 && !lemmingEntity.hasUmbrella()) {
                lemmingEntity.setState(new DeadState());
            } else {
                lemmingEntity.setState(new WalkingState());
            }
        }
        
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        lemmingEntity.stopFalling();
    }
}
