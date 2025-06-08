package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.Lemmings_game.Model.*;

import javax.swing.JPanel;
import java.awt.Graphics;

public class MapView {
    private MapModel model;
    private int camX;
    private int camY;
    private SpawnerView spawnerView;
    private ExitView exitView;

    public MapView(MapModel model, SpawnerView spawnerView, ExitView exitView, int camX, int camY) {
        this.camX = camX;
        this.camY = camY;
        this.model = model;
        this.spawnerView = spawnerView;
        this.exitView = exitView;
    }

    public void draw(Graphics g) {

        //System.out.println("camX: " + camX + " camY: " + camY);

        TileModel[][] tiles = model.getMapTiles();

        int startX = camX / LemmingConstants.TILE_WIDTH;
        int startY = camY / LemmingConstants.TILE_HEIGHT;
        int endX = (camX + 800) / LemmingConstants.TILE_WIDTH + 1;
        int endY = (camY + 600) / LemmingConstants.TILE_HEIGHT + 1;
    
        for (int y = startY; y < endY && y < tiles.length; y++) {
            for (int x = startX; x < endX && x < tiles[0].length; x++) {
                TileModel tile = tiles[y][x];
                if (tile != null && tile.getImage() != null) {
                    int drawX = x * LemmingConstants.TILE_WIDTH - camX;
                    int drawY = y * LemmingConstants.TILE_HEIGHT - camY;
                    g.drawImage(tile.getImage(), drawX, drawY, null);
                }
            }
        }

        spawnerView.draw(g,camX,camY);
        exitView.draw(g,camX,camY);
    }

    public int getCamX() {
        System.out.println("camX en mapView: " + camX);
        return camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCameraPosition(int x, int y) {
        this.camX = x;
        this.camY = y;
    }
}
