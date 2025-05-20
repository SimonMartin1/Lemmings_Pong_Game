package Proyecto.games.Pong_game.View;

import javax.swing.*;
import java.awt.*;

public class PaddleView extends JPanel {
    Color color;
    public PaddleView(){
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.red);
        g.fillRect(30,30,20,90);
    }

}