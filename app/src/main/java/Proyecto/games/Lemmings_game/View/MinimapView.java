package Proyecto.games.Lemmings_game.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MinimapView {
    int x, y, width, height;
    BufferedImage minimapImage;

    public MinimapView(int x, int y, int width, int height, int level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            minimapImage = ImageIO.read(getClass().getResourceAsStream("/map" + (4 + level) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.minimapImage = minimapImage;
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
