package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.AbilityClass;

import java.awt.Color;

public class WallAbility extends AbilityClass {

    public WallAbility(){
        super(Ability.STOP);
    }

    @Override
    public void apply(LemmingModel lemming, double delta) {
        lemming.setCurrentLeemingState(LemmingAnimationState.STOPING);

        int tileX = (lemming.getX() + lemming.getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        lemming.setSpeed(0);

        //aca le seteo para que no pasen jajaja
        lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 1][tileX].setTileColor(Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 2][tileX].setTileColor(Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 3][tileX].setTileColor(Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 4][tileX].setTileColor(Color.GREEN);
    }

}
