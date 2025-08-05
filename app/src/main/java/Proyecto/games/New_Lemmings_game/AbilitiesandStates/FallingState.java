package Proyecto.games.New_Lemmings_game.AbilitiesandStates;

import Proyecto.games.New_Lemmings_game.LemmingState;
import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class FallingState implements LemmingState {

    private int startFallTileY;
    private int ticks;
    @Override
    public void onEnter(Lemming_Entity lemmingEntity) {
        lemmingEntity.startFalling();
        ticks = 0;
        startFallTileY = lemmingEntity.getTileY();  // guardás desde dónde empezó a caer
        lemmingEntity.setAnimationState(LemmingAnimationState.FALLING);
    }

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        int tileX = lemmingEntity.getTileX();
        int tileY = lemmingEntity.getTileY();
    
        // Chequeamos el piso de abajo antes de moverlo
        Color tileBelow = lemmingEntity.getLevel().getMap().getTileColor(tileY + 1, tileX);
    
        if (Color.BLACK.equals(tileBelow)) {
            // Sigue cayendo
            lemmingEntity.setY(lemmingEntity.getY() + lemmingEntity.getSpeed());
        } else {
            // Hay piso
            int fallDistance = tileY - startFallTileY;
            boolean hasUmbrella = lemmingEntity.getAbilityClass() instanceof UmbrellaAbility;
    
            if (fallDistance > 25 && !hasUmbrella) {
                if (ticks == 0) {
                    
                    // Primera vez que toca piso: mostrar animación de muerte y detenerlo
                    lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.EXPLANTING_FALL);
                }
                ticks++;
    
                if (ticks > 30) { // Espera un poco y lo mata
                    lemmingEntity.setState(new DeadState());
                }
            } else {
                // No es caída mortal, vuelve a caminar
                lemmingEntity.setState(new WalkingState());
            }
        }
    }
    
    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        lemmingEntity.stopFalling();
    }
}
