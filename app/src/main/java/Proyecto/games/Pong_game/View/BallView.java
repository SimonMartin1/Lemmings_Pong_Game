package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.BallModel;

import javax.swing.*;
import java.awt.*;

public class BallView extends JPanel {
    private Color color = Color.ORANGE;
    private int radius = 30;
    private int initialY;
    private int initialX;
    BallModel ballModel;

    public BallView(int initialY, int initialX, int radius, BallModel ballModel){
        this.initialX = initialX;
        this.initialY = initialY;
        this.radius = radius;
        this.ballModel = ballModel;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval((int)ballModel.getPosX(),(int)ballModel.getPosY(),30,30);
    }


}
