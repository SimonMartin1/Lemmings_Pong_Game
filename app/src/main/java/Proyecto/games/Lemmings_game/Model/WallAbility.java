package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

public class WallAbility implements AbilityModel {

    private final Ability name = Ability.STOP;

    @Override
    public void apply(LemmingModel lemming, double delta) {
        lemming.setCurrentLeemingState(LemmingAnimationState.STOPING);

        int tileX = (lemming.getX() + lemming.getView().getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        //System.out.println("me frene");
        //pruebo aca si se para el lemming
        lemming.setSpeed(0);

        //aca le seteo para que no pasen jajaja
        lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(tileX, tileY, Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 1][tileX].setTileColor(tileX, tileY, Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 2][tileX].setTileColor(tileX, tileY, Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 3][tileX].setTileColor(tileX, tileY, Color.GREEN);
        lemming.getMap().getMapTiles()[tileY - 4][tileX].setTileColor(tileX, tileY, Color.GREEN);


        //lemming.getMap().getMapTiles()[tileY][tileX + 1].setTileColor(tileX, tileY, Color.GREEN);
        //lemming.getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(tileX + 1, tileY, Color.GREEN);


        //despues le seteo donde esta parado color brown a los tiles 

    }

    @Override
    public Ability getName() {
        return name;
    }
}
