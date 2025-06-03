package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

public class DigAbility implements AbilityModel {

    private final String name = "Cavar";

    @Override
    public void apply(LemmingModel lemming, double delta) {
        int tileX = (lemming.getX() + lemming.getView().getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        // "Cava" el tile actual, es decir, lo pone blanco (vacío)
        lemming.getMap().getMapTiles()[tileY + 1][tileX].setColorPixelImage(tileX, tileY);
        lemming.setY(lemming.getY() + 1); // baja el lemming un poco
    }

    @Override
    public String getName() {
        return name;
    }
}
