package Proyecto.games.Lemmings_game.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import Proyecto.games.Lemmings_game.Lemmings;

public class GameMenuView {
    private final int width;
    private final int height;
    private double blinkTime;
    private boolean showPressText = true,prevMousePressed;
    private Boolean prevPausePressed = null;
    private final Lemmings game;


    public GameMenuView(int width, int height, Lemmings game) {
        this.width = width;
        this.height = height;
        this.game=game;
    }

    public void drawmenu(Graphics2D g) {

        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_back.png").getImage();
            g.drawImage(background, 0, 0, width, height,null);
            
            Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_title.png").getImage();
            g.drawImage(lemmings,width/2-width/4-90 , 125, width/2+200, height/4,null);

            Image lemmings_button = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_button.png").getImage();
            g.drawImage(lemmings_button,width/2-50 , height/2-40, 120, 120,null);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Settings", width-250 , height-60);
            g.drawString("Score", 250 , height-60);

        if (game.getIsinMenu() && showPressText) {
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

    public boolean mouseTracker(int x, int y, int width,int height, Mouse m){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && m.isLeftButtonPressed() ;
    }

    public boolean detectPlay(Mouse m) {
        return mouseTracker(width/2 - 100, 300,200,60, m);
    }

    public boolean detectPlay(Keyboard k){
        return k.isKeyPressed(10);
    }

    public boolean detectSetting(Mouse m) {
        return  mouseTracker(width - 250,height-110,150,80, m) && !game.getIsinsettings();
    }
        public boolean detectScore(Mouse m) {
        return  mouseTracker(250,height-110,150,80, m) && !game.getIsinsettings();
    }

}