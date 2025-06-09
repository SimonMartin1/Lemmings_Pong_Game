package Proyecto.games.Lemmings_game.View;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;



public class Buttons {
    String text;        
    float relX, relY, relWidth, relHeight; // proporciones relativas
    private Color baseColor = new Color(101, 67, 33); // marrón tierra oscuro

    public Buttons(String text, float relX, float relY, float relWidth, float relHeight) {
        this.text = text;
        this.relX = relX;
        this.relY = relY;
        this.relWidth = relWidth;
        this.relHeight = relHeight;
    }

    public void draw(Graphics2D g, int screenWidth, int screenHeight) {
        // Convertimos proporciones a píxeles reales
        int x = (int)(relX * screenWidth);
        int y = (int)(relY * screenHeight);
        int width = (int)(relWidth * screenWidth);
        int height = (int)(relHeight * screenHeight);

        // Degradado vertical marrón
        GradientPaint gradient = new GradientPaint(
            x, y, new Color(184, 134, 11),
            x, y + height, baseColor
        );
        g.setPaint(gradient);

        RoundRectangle2D.Float buttonShape = new RoundRectangle2D.Float(x, y, width, height, height * 0.2f, height * 0.2f);
        g.fill(buttonShape);

        // Borde
        g.setColor(new Color(60, 40, 20));
        g.setStroke(new BasicStroke(Math.max(2, height / 40)));
        g.draw(buttonShape);

        // Fuente escalada
        int fontSize = Math.max(12, height / 3); // más visible
        Font font = new Font("Courier New", Font.BOLD, height / 10);

        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2 - 3;

        // Sombra
        g.setColor(new Color(0, 0, 0, 200));
        g.drawString(text, textX + 1, textY + 1);

        // Texto
        g.setColor(new Color(245, 245, 220));
        g.drawString(text, textX, textY);
    }
}
