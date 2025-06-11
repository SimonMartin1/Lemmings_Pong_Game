package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.Lemmings_game.Model.*;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class MapView {
    private MapModel model;
    private int camX;
    private int camY= 0;
    private SpawnerView spawnerView;
    private ExitView exitView;
    //private int mapWidth = 1366;
    //private int mapHeight = 768;
    private int screenWidth;
    private int screenHeight;

    public MapView(MapModel model, SpawnerView spawnerView, ExitView exitView, int camX, int camY, int screenWidth, int screenHeight) {
        this.camX = camX;
        //this.camY = camY;
        this.model = model;
        this.spawnerView = spawnerView;
        this.exitView = exitView;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void draw(Graphics g) {

        //System.out.println("camX: " + camX + " camY: " + camY);

        TileModel[][] tiles = model.getMapTiles();
        //g.setColor(Color.RED);
        //g.fillRect(0, 0, mapWidth, mapHeight);
        int startX = camX / LemmingConstants.TILE_WIDTH;
        int startY = camY / LemmingConstants.TILE_HEIGHT;
        int endX = (camX + screenWidth) / LemmingConstants.TILE_WIDTH + 1;
        int endY = (camY + screenHeight) / LemmingConstants.TILE_HEIGHT + 1;
    
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
