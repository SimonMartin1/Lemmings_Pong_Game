package Proyecto.games.New_Lemmings_game.AbilitiesandStates;

import Proyecto.games.New_Lemmings_game.LemmingConstants;
import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class ClimbAbility extends AbilityClass {

    private boolean isClimbing = false;

    public ClimbAbility(){
        super(Ability.CLIMB);
    }

    @Override
    public void apply(Lemming_Entity lemming, double delta) {

        int tileX = (lemming.getX()) / 8;
        int tileY = (lemming.getY()) / 8;

        boolean goingRight = lemming.isWalkingToRight();
        int dx = goingRight ? 1 : -1;
        int moveX = goingRight ? 10 : -10;
        LemmingAnimationState climbingState = goingRight
                ? LemmingAnimationState.CLIMBING_RIGHT
                : LemmingAnimationState.CLIMBING_LEFT;

        if (isClimbing) {
            lemming.setY(lemming.getY() - 1);

            if (!isClimbeable(lemming.getLevel().getMap().getMapTiles()[tileY + 4][tileX + dx].getColor())) {
                lemming.setX(lemming.getX() + moveX);
                lemming.setAbility(null);
            }
        } else {
            boolean climb1 = isClimbeable(lemming.getLevel().getMap().getMapTiles()[tileY][tileX + dx].getColor());
            boolean climb2 = isClimbeable(lemming.getLevel().getMap().getMapTiles()[tileY - 1][tileX + dx].getColor());
            
            if (climb1 && climb2 ) {
                lemming.setCurrentStateAnimation(climbingState);
                isClimbing = true;
            }
            else {
                System.out.println("no climbeo nada");
                //lemming.clearAbility();

                tileX = lemming.getX() / LemmingConstants.TILE_WIDTH;
                tileY = (lemming.getY() + LemmingConstants.TILE_HEIGHT - 1) / LemmingConstants.TILE_HEIGHT; // Posición de los pies

                if (lemming.isWalkingToRight()) {
                    lemming.setCurrentStateAnimation(LemmingAnimationState.WALKING_RIGHT);

                    // Verificar obstáculo al frente y arriba (pared)
                    Color frontUpper = lemming.getLevel().getMap().getTileColor(tileY - 3, tileX + 1);
                    // Verificar suelo al frente (para subir escalones)
                    Color frontGround = lemming.getLevel().getMap().getTileColor(tileY, tileX + 1);

                    if (!Color.BLACK.equals(frontUpper)) { // Hay pared
                        lemming.setWalkingToRight(false);
                        return;
                    }

                    if (!Color.BLACK.equals(frontGround)) { // Hay escalón para subir
                        lemming.setY(lemming.getY() - lemming.getSpeed());
                    }

                    lemming.setX(lemming.getX() + lemming.getSpeed());
                } else {
                    lemming.setCurrentStateAnimation(LemmingAnimationState.WALKING_LEFT);

                    // Verificar obstáculo al frente y arriba (pared)
                    Color frontUpper = lemming.getLevel().getMap().getTileColor(tileY - 3, tileX - 1);
                    // Verificar suelo al frente (para subir escalones)
                    Color frontGround = lemming.getLevel().getMap().getTileColor(tileY, tileX - 1);

                    if (!Color.BLACK.equals(frontUpper)) { // Hay pared
                        lemming.setWalkingToRight(true);
                        return;
                    }

                    if (!Color.BLACK.equals(frontGround)) { // Hay escalón para subir
                        lemming.setY(lemming.getY() - lemming.getSpeed());
                    }

                    lemming.setX(lemming.getX() - lemming.getSpeed());
                }

                // Lógica de caída
                if (shouldFall(lemming)) {
                    //l.setState(new FallingState());
                    //return;
                }

                // Lógica de detección de salida
                if (lemming.getLevel().getExit().checkLemming(lemming)) {
                    lemming.setState(new SavedState());
                    System.out.println("Entre a la salida!!");
                }
            }
        }

    }


    public boolean isClimbeable(Color c){
        return !Color.BLACK.equals(c) && !Color.GREEN.equals(c);
    }

    private boolean shouldFall(Lemming_Entity l) {
        int tileX = l.getX() / LemmingConstants.TILE_WIDTH;
        int tileY = (l.getY() + LemmingConstants.TILE_HEIGHT) / LemmingConstants.TILE_HEIGHT; // Posición de los pies
        Color below = l.getLevel().getMap().getTileColor(tileY + 1, tileX);
        return Color.BLACK.equals(below); // Si no hay suelo, caer
    }
}
