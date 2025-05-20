package Proyecto.games.Pong_game.View;

import javax.swing.*;
import java.awt.*;

public class BallView extends JPanel {

    public BallView(){
        setOpaque(false); // ¡IMPORTANTE! Para que no tape el fondo
        //dimensiones y demas
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(30,30,30,30);
    }

}
