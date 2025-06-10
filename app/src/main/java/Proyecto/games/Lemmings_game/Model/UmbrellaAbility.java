package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;

import java.awt.*;

public class UmbrellaAbility extends AbilityClass {

    boolean isUsingUmbrella = false;

    public UmbrellaAbility(){
        super(Ability.UMBRELLA);
    }

    @Override
    public void apply(LemmingModel lemming, double delta) {

        int tileX = (lemming.getX() + lemming.getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;
        boolean goingRight = lemming.isWalkingToRight();

        LemmingAnimationState umbrellaState = goingRight
                ? LemmingAnimationState.UMBRELLA_RIGHT
                : LemmingAnimationState.UMBRELLA_LEFT;

        if(lemming.HeIsGoingToDie() || isUsingUmbrella){

            isUsingUmbrella = true;

            lemming.setCurrentLeemingState(umbrellaState);
            lemming.setY(lemming.getY() + 1);

            if(!Color.BLACK.equals(lemming.getMap().getMapTiles()[tileY + 1][tileX].getColor())) lemming.setAbility(null);
        }
        else {
            if (lemming.shouldItFall()) {
                lemming.fall();
            } else {
                lemming.walk();
            }
        }
    }
}
