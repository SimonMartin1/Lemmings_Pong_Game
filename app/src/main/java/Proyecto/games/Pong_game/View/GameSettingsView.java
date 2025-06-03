package Proyecto.games.Pong_game.View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameSettingsView {
    private final int width;
    private final int height;


    public GameSettingsView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawmenu(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2 -140, 145, 400, 40, 20, 20); 
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width/2-50 , 70);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Music", width/2-250 , 125);
        g.drawString("Track Name", width/2-120 , 125);
        g.drawString("Off", width/2+20 , 125);
        g.drawString("1 Player", width/2-265 , 170);
        g.drawString("Difficulty", width/2-120 , 170);
        g.drawString("Hard", width/2 , 170);
        g.drawString("Medium", width/2+90 , 170);
        g.drawString("Easy", width/2+200 , 170);
        g.drawString("2 Players", width/2-265 , 215);
        g.drawString("Save", width-325 , 550);
        g.drawString("Cancel", width-245 , 550);
        g.drawString("Reset", width-145 , 550);
        
    }

    

}
