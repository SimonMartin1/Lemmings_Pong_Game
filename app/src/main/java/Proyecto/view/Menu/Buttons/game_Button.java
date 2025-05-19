package Proyecto.view.Menu.Buttons;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class game_Button extends JPanel{
    final private Image background;
    public game_Button(String url){
        background = new ImageIcon(url).getImage();
        this.setOpaque(false); 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}


