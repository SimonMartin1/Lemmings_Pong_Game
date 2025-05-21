package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.PaddleModel;

import javax.swing.*;
import java.awt.*;

public class PaddleView extends JPanel {
    private PaddleModel model;
    private Color paddleColor = Color.RED; // Color de la paleta
    private int paddleWidth = 20;
    private int paddleHeight = 90;
    private int fixedX = 30;

    public PaddleView(PaddleModel model, int fixedX) {
        this.model = model;
        this.fixedX = fixedX;
        setOpaque(false); // Para que el fondo del panel sea transparente
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.red);
        g.fillRect(this.fixedX, model.getY(),paddleWidth,paddleHeight);
    }

}