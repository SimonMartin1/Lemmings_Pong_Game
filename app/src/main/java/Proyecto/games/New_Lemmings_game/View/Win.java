package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Win extends Lemmings_Screens {

    // Botón "Volver al menú"
    

    public Win(int width, int height, Lemmings game) {
        super(width,height,game);
    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_Win.jpg").getImage();
        g.drawImage(lemmings,width/2-120 , height/2-70, width/4+50, height/4+50,null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Congratulations!", width / 2 - 140, 160);


    }

    @Override
    public void update(double delta) {
        if(game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
        }
    }
}