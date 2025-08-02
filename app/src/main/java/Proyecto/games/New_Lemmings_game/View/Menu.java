package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.utils.Screen;

import javax.swing.*;
import java.awt.*;


public class Menu extends Screen {
    private int width;
    private int height;
    private double blinkTime;
    private boolean showPressText = true,prevMousePressed;
    private Boolean prevPausePressed = null;


    public Menu(int width, int height) {
        super(width,height);
    }

    @Override
    public void draw(Graphics2D g) {

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

//        if (game.getIsinMenu() && showPressText && !game.getIsinScore()) {
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Arial", Font.BOLD, 24));
//            g.drawString("Click or Enter", width/2 - 71, 420);
//        }
    }
    @Override
    public void update(double delta){
        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }
    }


}