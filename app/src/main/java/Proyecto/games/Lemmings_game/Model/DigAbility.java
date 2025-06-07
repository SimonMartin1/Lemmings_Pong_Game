package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

public class DigAbility implements AbilityModel {

    private final Ability name = Ability.DIGGER;

    @Override
    public void apply(LemmingModel lemming, double delta) {

        lemming.setCurrentLeemingState(LemmingAnimationState.DIGGING);

        int tileX = (lemming.getX() + lemming.getView().getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        //System.out.println("i'M CAVANDO HARD");

        // "Cava" el tile actual, es decir, lo pone negro (vacío)
        if(!lemming.getMap().getMapTiles()[tileY + 1][tileX].getColor().equals(Color.BLACK)){

            lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(tileX, tileY, Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX].setTileColor(tileX, tileY,Color.BLACK);

            lemming.getMap().getMapTiles()[tileY][tileX + 1].setTileColor(tileX, tileY,Color.BLACK);
            lemming.getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(tileX + 1, tileY,Color.BLACK);

            lemming.setY(lemming.getY() + 1); // baja el lemming un poco
        }

        if(lemming.getMap().getMapTiles()[tileY + 2][tileX].getColor().equals(Color.BLACK)){
            lemming.setAbility(null);
        }


    }

    @Override
    public Ability getName() {
        return name;
    }
}
