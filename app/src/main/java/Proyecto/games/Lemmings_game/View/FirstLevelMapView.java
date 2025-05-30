package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class FirstLevelMapView extends JPanel {
    private FirstLevelMapModel model;

    public FirstLevelMapView(FirstLevelMapModel model) {
        this.model = model;
    }

    public void draw(Graphics g) {
        int tileWidth = model.getTileWidth();
        int tileHeight = model.getTileHeight();
        TileModel[][] tiles = model.getMapTiles();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                TileModel tile = tiles[y][x];
                if (tile != null && tile.getImage() != null) {
                    g.drawImage(tile.getImage(), x * tileWidth, y * tileHeight, null);
                }
            }
        }
    }
}
