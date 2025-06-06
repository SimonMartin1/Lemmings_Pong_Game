package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FirstLevelMapModel {
    TileModel[][] mapTiles;
    private int cameraX = 0;
    private int cameraY = 0;

    public FirstLevelMapModel() throws Exception {
        BufferedImage fullImage = ImageIO.read(getClass().getResourceAsStream("/map5.png"));
        int altura = fullImage.getHeight() / LemmingConstants.TILE_HEIGHT;
        int ancho = fullImage.getWidth() / LemmingConstants.TILE_WIDTH;
        mapTiles = new TileModel[altura][ancho];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < ancho; x++) {
                BufferedImage tileImage = fullImage.getSubimage(x * LemmingConstants.TILE_WIDTH, y * LemmingConstants.TILE_HEIGHT, LemmingConstants.TILE_WIDTH, LemmingConstants.TILE_HEIGHT);
                mapTiles[y][x] = new TileModel(tileImage);
            }
        }
    }

    public TileModel getTileDebajo(int x, int y, int lemmingAltura) {
        int tileX = x / LemmingConstants.TILE_WIDTH;
        int tileY = (y + lemmingAltura) / LemmingConstants.TILE_HEIGHT;
        if (tileY >= 0 && tileY < mapTiles.length && tileX >= 0 && tileX < mapTiles[0].length) {
            return mapTiles[tileY][tileX];
        } else {
            return null;
        }
    }

    
    public TileModel[][] getMapTiles() {
        return mapTiles;
    }

    public Color getTileColor(int tileY, int tileX){
        Color res = null;

        if (tileY >= 0 && tileY < mapTiles.length &&
                tileX >= 0 && tileX < mapTiles[0].length &&
                mapTiles[tileY][tileX] != null &&
                mapTiles[tileY][tileX].getColor() != null) {
            res =  mapTiles[tileY][tileX].getColor();
        }

        return res;
    }
    
    public void setCameraPosition(int x, int y) {
        this.cameraX = x;
        this.cameraY = y;
    }
}
