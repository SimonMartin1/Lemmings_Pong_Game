package Proyecto.games.New_Pong_game.views;


import Proyecto.games.New_Pong_game.utils.Pong_Screens;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends Pong_Screens {

    private double blinkTime;
    private boolean showPressText = true;
    public Menu(int width, int height, Pong game) {
        super(width,height,game);
    }

    @Override
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
    @Override
    public void update(double delta){
        if((detecSettings(width - 250, height - 110, 150, 80)) || game.getKeyboard().isKeyPressed(KeyEvent.VK_F1)){
            game.setGameState(GameState.ON_CONFIG);
        }

        if(detecPlay(width / 2, height/2+50, 200, 60)|| game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PLAYING);
            game.startGame();
        }

        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }

    }
    public boolean isMouseOverClickArea(int x, int y, int width, int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && game.getMouse().isLeftButtonPressed();
    }

    protected boolean detecPlay(int x, int y, int width,int height){
        return isMouseOverClickArea(width / 2 - 100, 300, 200, 60);
    }

    protected boolean detecSettings(int x, int y, int width,int height){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }
}



