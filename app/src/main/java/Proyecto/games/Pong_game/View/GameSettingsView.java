package Proyecto.games.Pong_game.View;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

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
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width-250 , 500);
    }

    

}
