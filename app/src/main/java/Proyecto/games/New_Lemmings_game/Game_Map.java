package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.ScoreDatabase;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game_Map {
    private Tile[][] mapTiles;
    private int camX;
    private int camY = 0;
    private int screenWidth = 800;
    private int screenHeight = 600;
    //private final ExitModel exit;
    private final int level;
    private Spawn spawner;
    private Exit exit;

    public Game_Map(int level, int cameraX, ScoreDatabase db, int exitX, int exitY, Spawn spawner , Exit  exit) throws IOException {
        this.level = level;
        this.camX = cameraX;
        //this.exit = new ExitModel(exitX, exitY, this);
        loadMapFromImage(getMapImagePath(level));
        this.spawner = spawner;
        this.exit = exit; 
    }

    private String getMapImagePath(int level) {
        return "/map" + (4 + level) + ".png";
    }

    private void loadMapFromImage(String imagePath) throws IOException {
        BufferedImage fullImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        int tileRows = fullImage.getHeight() / LemmingConstants.TILE_HEIGHT;
        int tileCols = fullImage.getWidth() / LemmingConstants.TILE_WIDTH;


        mapTiles = new Tile[tileRows][tileCols];

        for (int y = 0; y < tileRows; y++) {
            for (int x = 0; x < tileCols; x++) {
                BufferedImage tileImage = fullImage.getSubimage(
                        x * LemmingConstants.TILE_WIDTH,
                        y * LemmingConstants.TILE_HEIGHT,
                        LemmingConstants.TILE_WIDTH,
                        LemmingConstants.TILE_HEIGHT
                );
                mapTiles[y][x] = new Tile(tileImage);
            }
        }
    }

    public void reset() throws IOException {
        loadMapFromImage(getMapImagePath(this.level));
    }

    public Tile getTileBelow(int x, int y) {
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

    public void draw(Graphics g) {

        //System.out.println("camX: " + camX + " camY: " + camY);

        Tile[][] tiles = getMapTiles();
        //g.setColor(Color.RED);
        //g.fillRect(0, 0, mapWidth, mapHeight);
        int startX = camX / LemmingConstants.TILE_WIDTH;
        int startY = camY / LemmingConstants.TILE_HEIGHT;
        int endX = (camX + screenWidth) / LemmingConstants.TILE_WIDTH + 1;
        int endY = (camY + screenHeight) / LemmingConstants.TILE_HEIGHT + 1;
    
        for (int y = startY; y < endY && y < tiles.length; y++) {
            for (int x = startX; x < endX && x < tiles[0].length; x++) {
                Tile tile = tiles[y][x];
                if (tile != null && tile.getImage() != null) {
                    int drawX = x * LemmingConstants.TILE_WIDTH - camX;
                    int drawY = y * LemmingConstants.TILE_HEIGHT - camY;
                    g.drawImage(tile.getImage(), drawX, drawY, null);
                }
            }
        }

        spawner.draw(g,camX,camY);
        exit.draw(g,camX,camY);
    }

    /*public ExitModel getExit() {
        return exit;
    }*/

    public int getLemmingsSaved() {
        //return exit.savedLemmings;
        return 0;
    }

    public Tile[][] getMapTiles() {
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

    public static Object of(Ability digger, int i, Ability climb, int j, Ability stop, int k, Ability umbrella, int l) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'of'");
    }

    public int getLevel(){
        return level;
    }

    public float getViewportWidth() {
        return 800;
    }

    public float getViewportHeight() {
        return 600;
    }
}
