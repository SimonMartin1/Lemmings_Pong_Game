package Proyecto.games.New_Pong_game.views;

import Proyecto.games.utils.Drawable;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pause implements Drawable {
    private Boolean prevPausePressed = null;
    private final Pong game;

    public Pause(Pong game) {
        this.game = game;
    }

    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        // Volver a opaco para dibujar el texto
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Pause", game.getWidth() / 2 - 100, game.getHeight() / 2 - 160);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press P continue playing - ENTER back to Menu", game.getWidth() / 2 - 210, game.getHeight() / 2 - 120);
    }

    public void wantsBackMenu() {
        if (game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PLAYING);
        }

        if (game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
        }
    }

    public void pauseGame() {
        boolean currentPressed = game.getKeyboard().isKeyPressed(KeyEvent.VK_P);

        if (prevPausePressed == null) {
            prevPausePressed = currentPressed;
        }
        else{
            boolean justPressed = currentPressed && !prevPausePressed;
            prevPausePressed = currentPressed;

            if(justPressed){
                game.setGameState(GameState.ON_PAUSE);
            }
        }

    }
}
