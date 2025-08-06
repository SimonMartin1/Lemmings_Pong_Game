package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pause extends Lemmings_Screens {

    public Pause(Lemmings game) {
        super(game);
    }

    @Override
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
        g.drawString("Press ENTER continue playing - ESCAPE back to Menu", width / 2 - 220, height / 2 - 120);
    }
    @Override
    public void update(double delta){
        if(game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PLAYING);
        }
        if(game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
            game.resetLevel(game.getCurrentLevel());
        }
    }
    
    
}
