package Proyecto.games.Lemmings_game.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import Proyecto.games.Lemmings_game.Lemmings;

public class GameMenuView {
    private final int width;
    private final int height;
    private boolean animation;
    private double blinkTime;
    private boolean showPressText = true;
    private Boolean prevPausePressed = null;
    private final Lemmings game;


    public GameMenuView(int width, int height, Lemmings game) {
        this.width = width;
        this.height = height;
        this.game=game;
    }

    public void drawmenu(Graphics2D g) {

        System.out.println("DrawMenu");

        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_back.png").getImage();
            g.drawImage(background, 0, 0, width, height,null);
            
            Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_title.png").getImage();
            g.drawImage(lemmings,width/2-290 , 125, width/2+200, 160,null);

            Image lemmings_button = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_button.png").getImage();
            g.drawImage(lemmings_button,width/2-50 , height/2-40, 120, 120,null);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Settings", width-250 , height-60);

        if (!animation && showPressText) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 71, 420);
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