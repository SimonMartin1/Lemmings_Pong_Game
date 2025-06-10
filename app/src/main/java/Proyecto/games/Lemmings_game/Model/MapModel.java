package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.Lemmings_game.Utils.ScoreDatabase;

public class MapModel {
    private TileModel[][] mapTiles;
    private int camX;
    private int camY = 0;

    private final ExitModel exit;
    private final int level;

    public MapModel(int level, int cameraX, ScoreDatabase db, int exitX, int exitY) throws IOException {
        this.level = level;
        this.camX = cameraX;
        this.exit = new ExitModel(exitX, exitY, this);
        loadMapFromImage(getMapImagePath(level));
    }

    private String getMapImagePath(int level) {
        return "/map" + (4 + level) + ".png";
    }

    private void loadMapFromImage(String imagePath) throws IOException {
        BufferedImage fullImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        int tileRows = fullImage.getHeight() / LemmingConstants.TILE_HEIGHT;
        int tileCols = fullImage.getWidth() / LemmingConstants.TILE_WIDTH;

        mapTiles = new TileModel[tileRows][tileCols];

        for (int y = 0; y < tileRows; y++) {
            for (int x = 0; x < tileCols; x++) {
                BufferedImage tileImage = fullImage.getSubimage(
                        x * LemmingConstants.TILE_WIDTH,
                        y * LemmingConstants.TILE_HEIGHT,
                        LemmingConstants.TILE_WIDTH,
                        LemmingConstants.TILE_HEIGHT
                );
                mapTiles[y][x] = new TileModel(tileImage);
            }
        }
    }

    public void reset() throws IOException {
        loadMapFromImage(getMapImagePath(this.level));
    }

    public TileModel getTileBelow(int x, int y) {
        int tileX = (x - camX) / LemmingConstants.TILE_WIDTH;
        int tileY = (y + LemmingConstants.LEMMING_HEIGHT) / LemmingConstants.TILE_HEIGHT;
        if (isValidTilePosition(tileY, tileX)) {
            return mapTiles[tileY][tileX];
        }
        return null;
    }

    public Color getTileColor(int tileY, int tileX) {
        if (isValidTilePosition(tileY, tileX)) {
            return mapTiles[tileY][tileX].getColor();
        }
        return null;
    }

    private boolean isValidTilePosition(int y, int x) {
        return y >= 0 && y < mapTiles.length &&
                x >= 0 && x < mapTiles[0].length &&
                mapTiles[y][x] != null;
    }

    public ExitModel getExit() {
        return exit;
    }

    public int getLemmingsSaved() {
        return exit.savedLemmings;
    }

    public TileModel[][] getMapTiles() {
        return mapTiles;
    }

    // Cámara
    public int getCamX() {
        return camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamX(int cameraX){this.camX = cameraX; }


    public void setCameraPosition(int x, int y) {
        this.camX = x;
        this.camY = y;
    }
}
