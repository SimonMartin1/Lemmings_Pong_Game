package Proyecto.games.Lemmings_game.View;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GameMenuView {
    private final int width;
    private final int height;
    private boolean animation;
    private boolean showPressText = true;
    private double blinkTime = 0;


    public GameMenuView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_back.png").getImage();
            g.drawImage(background, 0, 0, width, height,null);
            
            Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_title.png").getImage();
            g.drawImage(lemmings,width/2-290 , 125, width/2+200, 160,null);

            Image lemmings_button = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_button.png").getImage();
            g.drawImage(lemmings_button,width/2-55 , 275, 120, 120,null);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Settings", width-250 , 500);

        if (!animation && showPressText) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 71, 420);
        }
    }


    public boolean isStarting(Mouse mouse){
        if (mouse.isLeftButtonPressed()) {
            int mx = mouse.getX();
            int my = mouse.getY();
            int bx = width/2 - 100, by = 300, bw = 200, bh = 60;
            if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh) {
                animation = true;
            }
        }

        return animation;
    }

    public boolean isStarting(Keyboard k){
        if (k.isKeyPressed(10)) {
            animation = true;
        }

        return animation;
    }

    public void update(double delta){
        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }
    }
}
