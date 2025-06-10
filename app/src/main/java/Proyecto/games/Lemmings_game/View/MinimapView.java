package Proyecto.games.Lemmings_game.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MinimapView {
    private int x, y, width, height;
    private BufferedImage minimapImage;


    public MinimapView(int baseX, int baseY, int baseWidth, int baseHeight, int level, int screenWidth, int screenHeight) {
        // Resolución BASE de diseño
        int baseScreenWidth = 800;
        int baseScreenHeight = 600;
    
        // Escalamos desde la resolución base hacia la actual
        this.width = baseWidth * screenWidth / baseScreenWidth;
        this.height = baseHeight * screenHeight / baseScreenHeight;
        
        int margen = 20;
        this.x = screenWidth - this.width - margen;
        this.y = screenHeight - this.height - margen;
        
        try {
            minimapImage = ImageIO.read(getClass().getResourceAsStream("/map" + (4 + level) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
