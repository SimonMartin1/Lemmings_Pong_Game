package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class UmbrellaAbility extends AbilityClass {

    boolean isUsingUmbrella = false;

    public UmbrellaAbility(){
        super(Ability.UMBRELLA);
    }

    @Override
    public void apply(Lemming_Entity lemmingEntity, double delta) {
        System.out.println("Umbrella activada: " + isUsingUmbrella);

        int tileX = (lemmingEntity.getX()) / 8;
        int tileY = (lemmingEntity.getY()) / 8;
        boolean goingRight = lemmingEntity.isWalkingToRight();

        LemmingAnimationState umbrellaState = goingRight
                ? LemmingAnimationState.UMBRELLA_RIGHT
                : LemmingAnimationState.UMBRELLA_LEFT;

        if(lemmingEntity.isGoingToDieFromFall() || isUsingUmbrella){

            isUsingUmbrella = true;

            lemmingEntity.setCurrentStateAnimation(umbrellaState);
            lemmingEntity.setY(lemmingEntity.getY() + 1);

            if(!Color.BLACK.equals(lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX].getColor())) lemmingEntity.setAbility(null);
        }
        else {
            lemmingEntity.clearAbility();
        }
    }

}
