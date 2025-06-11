package Proyecto.games.Lemmings_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameWinView {
    private final int width;
    private final int height;

    // Botón "Volver al menú"
    

    public GameWinView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("You Win!", width / 2 - 90, height / 2 - 60);

    }
}