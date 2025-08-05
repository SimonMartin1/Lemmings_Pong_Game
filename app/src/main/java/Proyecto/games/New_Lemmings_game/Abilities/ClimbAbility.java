package Proyecto.games.New_Lemmings_game.Abilities;

import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.utils.Ability;
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
                lemming.clearAbility();
            }
        }else if(canUseAbility(lemming)){
            lemming.setCurrentStateAnimation(climbingState);
            isClimbing = true;
        }
    }

    @Override
    public boolean canUseAbility(Lemming_Entity lemming) {

        if(isClimbing) return true;

        int tileX = (lemming.getX()) / 8;
        int tileY = (lemming.getY()) / 8;

        int dx = lemming.isWalkingToRight() ? 1 : -1;

        boolean climb1 = isClimbeable(lemming.getLevel().getMap().getMapTiles()[tileY][tileX + dx].getColor());
        boolean climb2 = isClimbeable(lemming.getLevel().getMap().getMapTiles()[tileY - 1][tileX + dx].getColor());

        return climb1 && climb2;
    }

    public boolean isClimbeable(Color c){
        return !Color.BLACK.equals(c) && !Color.GREEN.equals(c);
    }
}
