package Proyecto.games.New_Lemmings_game;

import javax.imageio.ImageIO;

import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Minimap {
    private final Game_Map map;
    private Level level;
    private Cursor cursor;
    private Mouse mouse; 
    private int x = 540;
    private int y = 480;
    private int width = 250;
    private int height = 100;
    private int minimapX =480;
    private int mapWidth = 450;
    private int mapHeight = 1536;

    private BufferedImage minimapImage;

    public Minimap(Game_Map map, Level level, Cursor cursor) {
        this.map = map;
        this.level = level;
        this.cursor = cursor;
        loadMinimapImage();
    }

    public void handleClick(int clickX, int clickY) {
        if (!isClickInsideMinimap(clickX, clickY)) {
            return;
        }
        moveCameraTo(clickX);
    }

    private boolean isClickInsideMinimap(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width &&
               clickY >= y && clickY <= y + height;
    }


    private void moveCameraTo(int clickX) {
        // Asegúrate de que la cámara no se salga de los límites
        //worldX = (int) Math.max(0, Math.min(worldX, mapWidth - map.getViewportWidth()));
        //worldY = (int) Math.max(0, Math.min(worldY, mapHeight - map.getViewportHeight()));
        float scaleX = (float) mapWidth / width;

        int worldX = (int) ((clickX - minimapX) * scaleX);

        map.setCameraPosition(worldX, 0);
        level.setCamX(worldX);
        //cursor.setCamX(worldX); // Si es necesario
    }

    private void loadMinimapImage() {
        try {
            String imagePath = "/map" + (4 + map.getLevel()) + ".png";
            minimapImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen del minimapa: " + e.getMessage());
            minimapImage = null;
        }
    }

    public void drawMinimap(Graphics2D g) {
        // Dibuja el fondo del minimapa
        g.setColor(new Color(101, 67, 33)); // Marrón oscuro
        g.fillRect(x, y, width, height);
    
        // Dibuja el borde
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawRect(x, y, width, height);
        
        // Dibuja la imagen del mapa
        if (minimapImage != null) {
            g.drawImage(minimapImage, x + 4, y + 4, width - 8, height - 8, null);
            
            // Opcional: dibuja un rectángulo indicando la vista actual
            //drawViewportIndicator(g);
        } else {
            g.setColor(Color.RED);
            g.drawString("Minimapa no disponible", x + 10, y + height / 2);
        }
    }

    private void drawViewportIndicator(Graphics2D g) {
        // Escala entre mapa real y minimapa
        float scaleX = width / (float) mapWidth;
        float scaleY = height / (float) mapHeight;
        
        // Posición del viewport
        int indicatorX = x + Math.round(level.getCamX() * scaleX);
        int indicatorY = y + Math.round(0 * scaleY);
        
        // Tamaño del viewport en el minimapa
        int indicatorWidth = Math.round(map.getViewportWidth() * scaleX);
        int indicatorHeight = Math.round(map.getViewportHeight() * scaleY);
        
        // Dibujar
        g.setColor(new Color(255, 255, 255, 150));
        g.drawRect(indicatorX, indicatorY, indicatorWidth, indicatorHeight);
    }
    

    // Getters para la posición del minimapa
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}