package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.PaddleModel;

import java.awt.*;

public class PaddleView {
    private final PaddleModel model;
    private final Color paddleColor = Color.WHITE; // Color de la paleta
    private int paddleWidth = 10;
    private int paddleHeight = 150;
    private int fixedX;


    public PaddleView(PaddleModel model, int fixedX) {
        this.model = model;
        this.fixedX = fixedX;
        //setOpaque(false); // Para que el fondo del panel sea transparente
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(paddleColor);
        g2d.fillRect(fixedX, model.getY(), paddleWidth, paddleHeight);
    }

    public void updateSize(int width,int paddleWidth,int paddleHeight){
        this.fixedX=width;
        this.paddleWidth=paddleWidth;
        this.paddleHeight=paddleHeight;
    }
}