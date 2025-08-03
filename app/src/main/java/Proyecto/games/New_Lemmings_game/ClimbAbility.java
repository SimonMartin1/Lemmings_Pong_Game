package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class ClimbAbility extends AbilityClass {
    private boolean isClimbing = false;
    private static final int CLIMB_SPEED = 1;
    private static final int TILE_SIZE = 8;
    private static final int SIDE_MOVE = 10; // Píxeles a mover lateralmente al terminar de escalar

    public ClimbAbility() {
        super(Ability.CLIMB);
    }

    @Override
    public void apply(Lemming_Entity lemming, double delta) {
        int tileX = (lemming.getX() + lemming.getLevel().getCamX()) / TILE_SIZE;
        int tileY = lemming.getY() / TILE_SIZE;

        boolean goingRight = lemming.isWalkingToRight();
        int direction = goingRight ? 1 : -1;
        LemmingAnimationState climbingState = goingRight 
                ? LemmingAnimationState.CLIMBING_RIGHT
                : LemmingAnimationState.CLIMBING_LEFT;

        if (isClimbing) {
            // Comportamiento mientras escala
            handleClimbing(lemming, tileX, tileY, direction, climbingState);
        } else {
            // Intentar comenzar a escalar
            tryStartClimbing(lemming, tileX, tileY, direction, climbingState);
        }
    }

    private void handleClimbing(Lemming_Entity lemming, int tileX, int tileY, int direction, 
                              LemmingAnimationState climbingState) {
        // Mover hacia arriba
        lemming.setY(lemming.getY() - CLIMB_SPEED);
        
        // Verificar si debe dejar de escalar
        if (!canContinueClimbing(lemming, tileX, tileY, direction)) {
            finishClimbing(lemming, direction);
        }
    }

    private void tryStartClimbing(Lemming_Entity lemming, int tileX, int tileY, int direction,
                                LemmingAnimationState climbingState) {
        if (canStartClimbing(lemming, tileX, tileY, direction)) {
            lemming.setCurrentStateAnimation(climbingState);
            isClimbing = true;
        } else {
            lemming.clearAbility();
        }
    }

    private boolean canStartClimbing(Lemming_Entity lemming, int tileX, int tileY, int direction) {
        // Verificar si hay pared delante (no negro) y espacio para comenzar a escalar
        boolean wallInFront = isWall(lemming.getLevel().getMap().getMapTiles()[tileY][tileX + direction].getColor());
        boolean spaceAbove = isWall(lemming.getLevel().getMap().getMapTiles()[tileY - 1][tileX + direction].getColor());
        
        return wallInFront && spaceAbove;
    }

    private boolean canContinueClimbing(Lemming_Entity lemming, int tileX, int tileY, int direction) {
        // Verificar si todavía hay pared para seguir escalando
        return isWall(lemming.getLevel().getMap().getMapTiles()[tileY + 4][tileX + direction].getColor());
    }

    private void finishClimbing(Lemming_Entity lemming, int direction) {
        // Mover al lemming hacia un lado al terminar de escalar
        lemming.setX(lemming.getX() + (direction * SIDE_MOVE));
        lemming.setAbility(null);
        isClimbing = false;
        //lemming.walk(); // Volver al estado de caminar
    }

    public boolean isWall(Color c) {
        // Pared es cualquier color que no sea negro
        return !Color.BLACK.equals(c);
    }
}