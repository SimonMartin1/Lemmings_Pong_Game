package Proyecto.games.New_Lemmings_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Minimap {
    private final Game_Map map;
    private Level level;
    private Cursor cursor;

    private int x = 540;
    private int y = 480;
    private int width = 250;
    private int height = 100;

    private int mapWidth = 450;
    private int mapHeight = 1536;

    private BufferedImage minimapImage;

    public Minimap(Game_Map map, Level level, Cursor cursor) {
        this.map = map;
        this.level = level;
        this.cursor = cursor;
    }

    public void setNewCam(int clickX, int clickY){

        float scaleX = (float) mapWidth / width;
        float scaleY = (float) mapHeight / height;

        int worldX = (int) ((clickX - x) * scaleX);
        //int worldY = (int) ((clickY - minimapY) * scaleY);

        System.out.println("worldX: " + worldX);

        map.setCameraPosition(worldX,0);
        level.setCamX(worldX);          
        
        try {
            minimapImage = ImageIO.read(getClass().getResourceAsStream("/map" + (4 + map.getLevel()) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cursorModel.setCamX(worldX);
    }
    
    public void handleClick(int x, int y){
        setNewCam(x, y);
    }

    public void drawMinimap(Graphics2D g) {
        // Marco marrón oscuro para el minimapa
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x, y, width, height);
    
        // Borde negro para que resalte
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawRect(x, y, width, height);
        
        // Si la imagen fue cargada correctamente, la dibujamos
        if (minimapImage != null) {
            g.drawImage(minimapImage, x + 4, y + 4, width - 8, height - 8, null);
        } else {
            // En caso de que no se cargue la imagen, podés poner un mensaje de error visual
            g.setColor(Color.RED);
            g.drawString("No se pudo cargar el minimapa", x + 10, y + height / 2);
        }
    }

    public int getMinimapPositionX(){
        return x;
    }

    public int getMinimapPositionY(){
        return y;
    }
}
