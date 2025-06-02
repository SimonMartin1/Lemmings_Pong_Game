package Proyecto.games.Pong_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GameMenuView {
    private final int width;
    private final int height;

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
        
    }
}
