package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import java.awt.*;

public class WalkingState implements LemmingState {
    
    @Override
    public void update(Lemming_Entity l, double delta) {
        int tileX = l.getX() / LemmingConstants.TILE_WIDTH;
        int tileY = (l.getY() + LemmingConstants.TILE_HEIGHT - 1) / LemmingConstants.TILE_HEIGHT; // Posición de los pies
        
        if (l.isWalkingToRight()) {
            l.setCurrentStateAnimation(LemmingAnimationState.WALKING_RIGHT);
            
            // Verificar obstáculo al frente y arriba (pared)
            Color frontUpper = l.getLevel().getMap().getTileColor(tileY - 3, tileX + 1);
            // Verificar suelo al frente (para subir escalones)
            Color frontGround = l.getLevel().getMap().getTileColor(tileY, tileX + 1);
            
            if (!Color.BLACK.equals(frontUpper)) { // Hay pared
                l.setWalkingToRight(false);
                return;
            }
            
            if (!Color.BLACK.equals(frontGround)) { // Hay escalón para subir
                l.setY(l.getY() - l.getSpeed());
            }
            
            l.setX(l.getX() + l.getSpeed());
        } else {
            l.setCurrentStateAnimation(LemmingAnimationState.WALKING_LEFT);
            
            // Verificar obstáculo al frente y arriba (pared)
            Color frontUpper = l.getLevel().getMap().getTileColor(tileY - 3, tileX - 1);
            // Verificar suelo al frente (para subir escalones)
            Color frontGround = l.getLevel().getMap().getTileColor(tileY, tileX - 1);
            
            if (!Color.BLACK.equals(frontUpper)) { // Hay pared
                l.setWalkingToRight(true);
                return;
            }
            
            if (!Color.BLACK.equals(frontGround)) { // Hay escalón para subir
                l.setY(l.getY() - l.getSpeed());
            }
            
            l.setX(l.getX() - l.getSpeed());
        }
        
        // Lógica de caída
        if (shouldFall(l)) {
            l.setState(new FallingState());
            return;
        }
        
        // Lógica de detección de salida
        if (l.getLevel().getExit().checkLemming(l)) {
            l.setState(new SavedState());
            System.out.println("Entre a la salida!!");
        }
    }
    
    private boolean shouldFall(Lemming_Entity l) {
        int tileX = l.getX() / LemmingConstants.TILE_WIDTH;
        int tileY = (l.getY() + LemmingConstants.TILE_HEIGHT) / LemmingConstants.TILE_HEIGHT; // Posición de los pies
        Color below = l.getLevel().getMap().getTileColor(tileY + 1, tileX);
        return Color.BLACK.equals(below); // Si no hay suelo, caer
    }
    
    @Override 
    public void onEnter(Lemming_Entity l) {
        // Inicialización si es necesaria
    }
    
    @Override 
    public void onExit(Lemming_Entity l) {
        // Limpieza si es necesaria
    }
}