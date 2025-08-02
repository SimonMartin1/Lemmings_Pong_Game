package Proyecto.games.New_Pong_game.views;

import Proyecto.games.utils.Drawable;
import Proyecto.games.New_Pong_game.utils.Player;
import com.entropyinteractive.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Over implements Drawable {
    private int width;
    private int height;
    private Player winner;
    private boolean isTwoPlayers;

    public Over(int width, int height) {
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

    public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        isTwoPlayers = twoPlayers;
    }
}
