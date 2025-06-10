package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MapModel {
    TileModel[][] mapTiles;
    //private int cameraX = 0;
    private int cameraY = 0;
    private int cameraX;
    private ExitModel exit;



    public MapModel(int level, int exitX, int exitY, int cameraX) throws Exception {
        BufferedImage fullImage = ImageIO.read(getClass().getResourceAsStream("/map" + (4 + level) + ".png"));
        this.cameraX = cameraX;
        int cantTilesY = fullImage.getHeight() / LemmingConstants.TILE_HEIGHT;
        int cantTilesX = fullImage.getWidth() / LemmingConstants.TILE_WIDTH;
        mapTiles = new TileModel[cantTilesY][cantTilesX];

        this.exit = new ExitModel(exitX,exitY, this); //ACA A FUTURO LE PASAMOS LA SALIDA PARA QUE CAMBIE

        for (int y = 0; y < cantTilesY; y++) {
            for (int x = 0; x < cantTilesX; x++) {
                BufferedImage tileImage = fullImage.getSubimage(x * LemmingConstants.TILE_WIDTH, y * LemmingConstants.TILE_HEIGHT, LemmingConstants.TILE_WIDTH, LemmingConstants.TILE_HEIGHT);
                mapTiles[y][x] = new TileModel(tileImage);
            }
        }
    }

    public TileModel getTileDebajo(int x, int y) {
        int tileX = (x - cameraX) / LemmingConstants.TILE_WIDTH;    
        //int tileX = x / LemmingConstants.TILE_WIDTH;
        int tileY = (y + LemmingConstants.LEMMING_HEIGHT) / LemmingConstants.TILE_HEIGHT;
        if (tileY >= 0 && tileY < mapTiles.length && tileX >= 0 && tileX < mapTiles[0].length) {
            return mapTiles[tileY][tileX];
        } else {
            return null;
        }
    }

    public int getLemmingsSaved(){
        return exit.savedLemmings;
    }
    
    public TileModel[][] getMapTiles() {
        return mapTiles;
    }

    public ExitModel getExit() {
        return exit;
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

    public void setCamX(int camX){
        this.cameraX = camX;
    }

    public int getCameraX(){
        return this.cameraX; 
    }

    public int getCameraY(){
        return this.cameraY;
    }
    
    public void setCameraPosition(int x, int y) {
        this.cameraX = x;
        this.cameraY = y;
    }

    public ExitModel getExitModel(){
        return exit;
    }
}
