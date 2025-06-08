package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.PaddleModel;

import java.awt.*;

public class PaddleView {
    private final PaddleModel model;
    private final Color paddleColor = Color.WHITE; // Color de la paleta
    private final int paddleWidth = 10;
    private final int paddleHeight = 150;
    private int fixedX = 10;


    public PaddleView(PaddleModel model, int fixedX) {
        this.model = model;
        this.fixedX = fixedX;
        //setOpaque(false); // Para que el fondo del panel sea transparente
    }

    public void draw(Graphics g) {
        //super.paintComponent(g); // esto siempre hay que llamarlo primero
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(paddleColor);
        g2d.fillRect(fixedX, model.getY(), paddleWidth, paddleHeight);
    }
}