package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.Drawable;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu implements Drawable {
    private int width;
    private int height;
    private double blinkTime;
    private boolean showPressText = true;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
    }

    public void draw(Graphics2D g2d) {

        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Pong_back.jpg").getImage();
        g2d.drawImage(background, width/2-width/4, 15, width/2, height/2,null);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.drawString("Play Game!", width/2-70, height/2+50);
        g2d.drawString("Settings", width-250 , height-60);

        if (showPressText) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString("Press Enter", width/2 - 70, height/2+140);
        }
    }

    public void update(double delta, Pong game){
        if(game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_CONFIG);
        }
        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }

    }
}



