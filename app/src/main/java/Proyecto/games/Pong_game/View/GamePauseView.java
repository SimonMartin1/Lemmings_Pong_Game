package Proyecto.games.Pong_game.View;
import java.awt.*;

import java.awt.event.KeyEvent;

import com.entropyinteractive.Keyboard;
public class GamePauseView {
    private final int width;
    private final int height;
    private Boolean prevPausePressed = null;

    public GamePauseView(int width, int height) {
        this.width = width;
        this.height = height;
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
        g.drawString("Press P continue playing - ENTER back to Menu", width / 2 - 210, height / 2 - 120);
    }
    
    public boolean wantsBackMenu(Keyboard keyboard) {
        return keyboard.isKeyPressed(KeyEvent.VK_ENTER);
    }

    public boolean pauseGame(Keyboard keyboard) {
        boolean currentPressed = keyboard.isKeyPressed(KeyEvent.VK_P);

        if (prevPausePressed == null) {
            prevPausePressed = currentPressed;
            return false;
        }

        boolean justPressed = currentPressed && !prevPausePressed;
        prevPausePressed = currentPressed;
        return justPressed;
    }
}
