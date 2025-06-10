package Proyecto.games.Pong_game.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Proyecto.games.Pong_game.Model.BallModel;

public class BallView extends JPanel {
    private BufferedImage spriteSheet;
    private final int frameWidth = 32;   
    private final int frameHeight = 32;
    BallModel ballModel;
    public boolean defaultBall, crazyBall;

    public BallView(BallModel ballModel){
        this.ballModel = ballModel;
    }

    public void draw(Graphics g){
        if (spriteSheet == null || defaultBall || crazyBall) {
            if(crazyBall){
                g.setColor(new Color(randomNumber(), randomNumber(), randomNumber()));
            } else {
                g.setColor(Color.ORANGE);
            }
            g.fillOval((int)(ballModel.getPosX() - frameWidth/2),(int)(ballModel.getPosY() - frameHeight/2),frameWidth, frameHeight);
        }
    }

    public void setBallType(BallSkins type) {
        switch(type){
            case NORMAL -> { defaultBall = true; crazyBall = false; }
            case CRAZY -> { crazyBall = true; defaultBall = false; }
        }
    }

    public static int randomNumber() {
        return 50 + (int)(Math.random() * (256 - 50));
    }
}
