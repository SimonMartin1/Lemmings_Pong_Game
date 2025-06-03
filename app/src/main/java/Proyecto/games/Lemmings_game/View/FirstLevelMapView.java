package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class FirstLevelMapView extends JPanel {
    private FirstLevelMapModel model;

    public FirstLevelMapView(FirstLevelMapModel model) {
        this.model = model;
    }

    public void draw(Graphics g, int camX, int camY) {
        int tileWidth = model.getTileWidth();
        int tileHeight = model.getTileHeight();
        TileModel[][] tiles = model.getMapTiles();
    
        int startX = camX / tileWidth;
        int startY = camY / tileHeight;
        int endX = (camX + 800) / tileWidth + 1;
        int endY = (camY + 600) / tileHeight + 1;
    
        for (int y = startY; y < endY && y < tiles.length; y++) {
            for (int x = startX; x < endX && x < tiles[0].length; x++) {
                TileModel tile = tiles[y][x];
                if (tile != null && tile.getImage() != null) {
                    int drawX = x * tileWidth - camX;
                    int drawY = y * tileHeight - camY;
                    g.drawImage(tile.getImage(), drawX, drawY, null);
                }
            }
        }
    }
    
}
