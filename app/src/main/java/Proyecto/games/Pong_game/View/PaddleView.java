package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.PaddleModel;

import javax.swing.*;
import java.awt.*;

public class PaddleView {
    private PaddleModel model;
    private Color paddleColor = Color.WHITE; // Color de la paleta
    private int paddleWidth = 10;
    private int paddleHeight = 150;
    private int fixedX = 10;
    private int initialY = 250;
    private int dx = 180;

    public PaddleView(PaddleModel model, int fixedX, int initialY) {
        this.model = model;
        this.fixedX = fixedX;
        this.initialY = initialY;
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