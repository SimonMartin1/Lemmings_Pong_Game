package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.utils.Pong_InputEventsTracker;
import Proyecto.games.New_Pong_game.utils.Pong_Screens;
import Proyecto.games.utils.Drawable;
import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends Pong_Screens {
    private double blinkTime;
    private boolean showPressText = true;
    private Pong game;
    public Menu(int width, int height, Pong game) {
        super(width,height,game.getMouse(),game.getKeyboard());
        this.game=game;
    }

    public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
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
        if((pong_inputEvents.detecSettings(width - 250, height - 110, 150, 80) && game.getGameState().equals(GameState.ON_MENU)) || pong_inputEvents.detectSettingsKeyboard()){
            game.setGameState(GameState.ON_CONFIG);
        }

        if((pong_inputEvents.detecPlay(width / 2 - 100, 300, 200, 60) && game.getGameState().equals(GameState.ON_MENU)) || pong_inputEvents.detectPlayKeyboard()){
            game.setGameState(GameState.PLAYING);
        }

        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }

    }
}



