package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.Drawable;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pause implements Drawable {
    private Boolean prevPausePressed = null;
    private final Pong pong;

    public Pause(Pong pong) {
        this.pong = pong;
    }

    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pong.getWidth(), pong.getHeight());

        // Volver a opaco para dibujar el texto
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Pause", pong.getWidth() / 2 - 100, pong.getHeight() / 2 - 160);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press P continue playing - ENTER back to Menu", pong.getWidth() / 2 - 210, pong.getHeight() / 2 - 120);
    }

    public void wantsBackMenu() {
        if (pong.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            pong.setGameState(GameState.PLAYING);
        }

        if (pong.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            pong.setGameState(GameState.ON_MENU);
        }
    }

    public void pauseGame() {
        boolean currentPressed = pong.getKeyboard().isKeyPressed(KeyEvent.VK_P);

        if (prevPausePressed == null) {
            prevPausePressed = currentPressed;
        }
        else{
            boolean justPressed = currentPressed && !prevPausePressed;
            prevPausePressed = currentPressed;

            if(justPressed){
                pong.setGameState(GameState.ON_PAUSE);
            }
        }

    }
}
