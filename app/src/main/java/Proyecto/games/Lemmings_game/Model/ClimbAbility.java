package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import java.awt.*;

public class ClimbAbility extends AbilityClass {

    private boolean isClimbing = false;

    public ClimbAbility(){
        super(Ability.CLIMB);
    }

    @Override
    public void apply(LemmingModel lemming, double delta) {

        int tileX = (lemming.getX() + lemming.getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        boolean goingRight = lemming.isWalkingToRight;
        int dx = goingRight ? 1 : -1;
        int moveX = goingRight ? 10 : -10;
        LemmingAnimationState climbingState = goingRight
                ? LemmingAnimationState.CLIMBING_RIGHT
                : LemmingAnimationState.CLIMBING_LEFT;

        if (isClimbing) {
            lemming.setY(lemming.getY() - 1);

            if (!isClimbeable(lemming.getMap().getMapTiles()[tileY + 4][tileX + dx].getColor())) {
                lemming.setX(lemming.getX() + moveX);
                lemming.setAbility(null);
            }
        } else {
            boolean climb1 = isClimbeable(lemming.getMap().getMapTiles()[tileY][tileX + dx].getColor());
            boolean climb2 = isClimbeable(lemming.getMap().getMapTiles()[tileY - 1][tileX + dx].getColor());

            if (climb1 && climb2) {
                lemming.setCurrentLeemingState(climbingState);
                isClimbing = true;
            } else {
                if (lemming.shouldItFall()) {
                    lemming.fall();
                } else {
                    lemming.walk();
                }
            }
        }

    }


    public boolean isClimbeable(Color c){
        return !Color.BLACK.equals(c) && !Color.GREEN.equals(c);
    }
}
