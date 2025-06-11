package Proyecto.games.Lemmings_game.View;

import java.awt.*;

import java.awt.event.KeyEvent;

import com.entropyinteractive.Keyboard;
public class GamePauseView {
    private int width;
    private int height;

    public GamePauseView(int width, int height) {
    this.width=width;
        this.height=height;
    }

    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Volver a opaco para dibujar el texto
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Pause", width / 2 - 100, height / 2 - 160);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press M continue playing - ENTER back to Menu", width / 2 - 210, height / 2 - 120);
    }
        public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
    }
    
    
}
