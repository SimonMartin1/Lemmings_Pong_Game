package Proyecto.games.Lemmings_game.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MinimapView {
    int x, y, width, height;
    BufferedImage minimapImage;

    public MinimapView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    
        // Dibujamos la imagen del minimapa escalada dentro del rectángulo
        //g.drawImage(minimapImage, x + 4, y + 4, width - 8, height - 8, null);
    
        // Acá podrías dibujar el ícono del jugador encima, si querés
        // g.drawImage(playerIcon, playerX, playerY, iconWidth, iconHeight, null);
    }
    
}
