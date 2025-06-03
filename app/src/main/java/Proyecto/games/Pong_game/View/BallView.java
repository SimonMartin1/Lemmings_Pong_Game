package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.BallModel;

import javax.swing.*;
import java.awt.*;

public class BallView extends JPanel {
    private Color color = Color.ORANGE;
    BallModel ballModel;

    public BallView(BallModel ballModel){
        this.ballModel = ballModel;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval((int)ballModel.getPosX(),(int)ballModel.getPosY(),30,30);
    }


}
