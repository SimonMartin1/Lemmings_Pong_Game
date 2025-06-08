package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.Player;
import com.entropyinteractive.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverMenuView {

    private final int width;
    private final int height;

    public GameOverMenuView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g, Player winner, boolean isTwoPlayers) {

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Volver a opaco para dibujar el texto
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        if(isTwoPlayers){
        g.drawString(winner + " Player wins!", width / 2 - 150, height / 2 - 160);
        }
        else{
            if (winner == Player.LEFT) {
                g.drawString("IA wins!", width / 2 -60, height / 2 - 160);
            }
            else{
                g.drawString(" Player wins!", width / 2 - 110, height / 2 - 160);
            }
        }

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press ENTER play again - ESC back to Menu", width / 2 - 180, height / 2 - 120);
    }

    public boolean wantsRestart(Keyboard keyboard) {
        return keyboard.isKeyPressed(KeyEvent.VK_ENTER);
    }
    public boolean wantsBackMenu(Keyboard keyboard) {
        return keyboard.isKeyPressed(KeyEvent.VK_ESCAPE);
    }
}