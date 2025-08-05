package Proyecto.games.Pong_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import Proyecto.games.Pong_game.Pong;

public class GameMenuView {
    private int width;
    private int height;
    private double blinkTime;
    private boolean showPressText = true;
    private Boolean prevPausePressed = null;
    private final Pong game;


    public GameMenuView(int width, int height, Pong game) {
        this.width = width;
        this.height = height;
        this.game=game;
    }
        public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
    }

    public void drawmenu(Graphics2D g) {
        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Pong_back.jpg").getImage();
        g.drawImage(background, width/2-width/4, 15, width/2, height/2,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Play Game!", width/2-70, height/2+50);
        g.drawString("Settings", width-250 , height-60);

        if (showPressText) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 70, height/2+140);
        }
    }

    public void update(double delta){
        blinkTime += delta;

        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }
    }

    public boolean detectPlay(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2, by = height/2, bw = 150, bh = 60;
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed() && !game.getIsinsettings();
    }

    public boolean detectPlay(Keyboard k){
        boolean currentPressed = k.isKeyPressed(KeyEvent.VK_ENTER);

        if (prevPausePressed == null) {
            prevPausePressed = currentPressed;
            return false;
        }

        boolean justPressed = currentPressed && !prevPausePressed;
        prevPausePressed = currentPressed;
        return justPressed;
    }


    public boolean detectSetting(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width - 250, by = height-110, bw = 150, bh = 80;
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed() && !game.getIsinsettings();
    }

}
