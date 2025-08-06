package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.utils.Pong_Screens;
import Proyecto.games.utils.Drawable;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pause extends Pong_Screens {
    private Boolean prevPausePressed = null;

    public Pause(Pong game) {
        super(game);
    }

    @Override
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
        g.drawString("Press ESC continue playing - ENTER back to Menu", game.getWidth() / 2 - 210, game.getHeight() / 2 - 120);
    }
    @Override
    public void update(double delta) {
        if (game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
        }

        if (game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PLAYING);
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
