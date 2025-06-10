package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;

import java.awt.Color;

public class DigAbility extends AbilityClass {

    public DigAbility(){
        super(Ability.DIGGER);
    }

    @Override
    public void apply(LemmingModel lemming, double delta) {

        lemming.setCurrentLeemingState(LemmingAnimationState.DIGGING);

        int tileX = (lemming.getX() + lemming.getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        if(!Color.BLACK.equals(lemming.getMap().getMapTiles()[tileY + 3][tileX].getColor())){

            lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);

            lemming.getMap().getMapTiles()[tileY][tileX + 1].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

            lemming.setY(lemming.getY() + 1); // baja el lemming un poco
        }
        else{

            lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);

            lemming.getMap().getMapTiles()[tileY][tileX +1].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

            lemming.getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

            lemming.getMap().getMapTiles()[tileY + 2][tileX].setTileColor(Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 2][tileX + 1].setTileColor(Color.BLACK);

            lemming.setAbility(null);
        }
    }

}
