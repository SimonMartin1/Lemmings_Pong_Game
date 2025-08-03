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
        loadMinimapImage();
    }

    public void handleClick(int clickX, int clickY) {
        if (!isClickInsideMinimap(clickX, clickY)) {
            return;
        }

        Point worldPosition = convertMinimapToWorld(clickX, clickY);
        moveCameraTo(worldPosition.x, worldPosition.y);
    }

    private boolean isClickInsideMinimap(int clickX, int clickY) {
        return clickX >= x && clickX <= x + width &&
               clickY >= y && clickY <= y + height;
    }

    private Point convertMinimapToWorld(int clickX, int clickY) {
        // Normaliza las coordenadas (0-1)
        float normalizedX = (float)(clickX - x) / width;
        float normalizedY = (float)(clickY - y) / height;
        
        // Convierte a coordenadas del mundo
        int worldX = (int)(normalizedX * mapWidth);
        int worldY = (int)(normalizedY * mapHeight);
        
        return new Point(worldX, worldY);
    }

    private void moveCameraTo(int worldX, int worldY) {
        // Asegúrate de que la cámara no se salga de los límites
        worldX = (int) Math.max(0, Math.min(worldX, mapWidth - map.getViewportWidth()));
        worldY = (int) Math.max(0, Math.min(worldY, mapHeight - map.getViewportHeight()));
        
        map.setCameraPosition(worldX, worldY);
        level.setCamX(worldX);
        cursor.setCamX(worldX); // Si es necesario
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
            drawViewportIndicator(g);
        } else {
            g.setColor(Color.RED);
            g.drawString("Minimapa no disponible", x + 10, y + height / 2);
        }
    }

    private void drawViewportIndicator(Graphics2D g) {
        // Calcula la posición y tamaño del viewport en el minimapa
        float scaleX = width / (float)mapWidth;
        float scaleY = height / (float)mapHeight;
        
        int indicatorX = x + (int)(map.getCamX() * scaleX);
        int indicatorY = y + (int)(map.getCamY() * scaleY);
        int indicatorWidth = (int)(map.getViewportWidth() * scaleX);
        int indicatorHeight = (int)(map.getViewportHeight() * scaleY);
        
        // Dibuja el rectángulo del viewport
        g.setColor(new Color(255, 255, 255, 150)); // Blanco semitransparente
        g.drawRect(indicatorX, indicatorY, indicatorWidth, indicatorHeight);
    }

    // Getters para la posición del minimapa
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}