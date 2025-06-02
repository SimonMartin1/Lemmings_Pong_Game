package Proyecto.games.Lemmings_game.Model;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FirstLevelMapModel {
    TileModel[][] mapTiles;
    final int tileWidth = 8;
    final int tileHeight = 8;
    private int cameraX = 0;
    private int cameraY = 0;

    public FirstLevelMapModel() throws Exception {
        BufferedImage fullImage = ImageIO.read(getClass().getResourceAsStream("/map4.png"));
        int altura = fullImage.getHeight() / tileHeight;
        int ancho = fullImage.getWidth() / tileWidth;
        mapTiles = new TileModel[altura][ancho];
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < ancho; x++) {
                BufferedImage tileImage = fullImage.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                mapTiles[y][x] = new TileModel(tileImage);
            }
        }
    }
    public TileModel[][] getMapTiles() {
        return mapTiles;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
    
    public void setCameraPosition(int x, int y) {
        this.cameraX = x;
        this.cameraY = y;
    }
}
