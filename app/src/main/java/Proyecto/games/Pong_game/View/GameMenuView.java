package Proyecto.games.Pong_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

public class GameMenuView {
    private final int width;
    private final int height;
    private double blinkTime;
    private boolean showPressText = true;
    private Boolean prevPausePressed = null;
    private Boolean prevSettingsPressed = null;


    public GameMenuView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawmenu(Graphics2D g) {
        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Pong_back.jpg").getImage();
        g.drawImage(background, 215, 15, width/2-20, height/2,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Play Game!", width/2 - 60, 370);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width-250 , 500);

        if (showPressText) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 71, 450);
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
        boolean isClicked = false;


        int mx = m.getX();
        int my = m.getY();
        int bx = width/2 - 100, by = 300, bw = 150, bh = 60;

        if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed()) {
            isClicked = true;
        }


        return isClicked;
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

    public boolean detectSettings(Keyboard k){
        boolean currentPressed = k.isKeyPressed(KeyEvent.VK_C);

        if (prevSettingsPressed == null) {
            prevSettingsPressed = currentPressed;
            return false;
        }

        boolean justPressed = currentPressed && !prevSettingsPressed;
        prevSettingsPressed = currentPressed;
        return justPressed;
    }

    public boolean detectSetting(Mouse m) {
        boolean isClicked = false;


        int mx = m.getX();
        int my = m.getY();
        int bx = width - 250, by = 420, bw = 150, bh = 80;

        if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed()) {
            isClicked = true;
        }


        return isClicked;
    }

}
