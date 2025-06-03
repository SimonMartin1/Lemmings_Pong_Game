package Proyecto.games.Lemmings_game.View;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Buttons {
    String text;
    int x, y, width, height;
    private Color baseColor = new Color(101, 67, 33); // marrón tierra oscuro

    public Buttons(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        // Antialiasing off para ese look pixelado noventero
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        // Degradado vertical marrón (claro arriba, oscuro abajo)
        GradientPaint gradient = new GradientPaint(
            x, y, new Color(184, 134, 11),   // marrón claro (dark goldenrod)
            x, y + height, baseColor          // marrón oscuro
        );
        g.setPaint(gradient);

        // Botón con bordes cuadraditos redondeados, más chato
        RoundRectangle2D.Float buttonShape = new RoundRectangle2D.Float(x, y, width, height, 8, 8);
        g.fill(buttonShape);

        // Borde marrón oscuro bien marcado
        g.setColor(new Color(60, 40, 20));
        g.setStroke(new BasicStroke(3));
        g.draw(buttonShape);

        // Texto estilo arcade, un poco más pixelado y contrastado
        Font font = new Font("Courier New", Font.BOLD, height / 10);
        g.setFont(font);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2 - 3;

        // Sombra negra del texto para que resalte
        g.setColor(new Color(0, 0, 0, 200));
        g.drawString(text, textX + 1, textY + 1);

        // Texto principal color crema viejo (beige claro)
        g.setColor(new Color(245, 245, 220));
        g.drawString(text, textX, textY);
    }
}
