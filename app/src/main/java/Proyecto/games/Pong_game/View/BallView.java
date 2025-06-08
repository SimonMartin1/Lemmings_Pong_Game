package Proyecto.games.Pong_game.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Proyecto.games.Pong_game.Model.BallModel;

public class BallView extends JPanel {
    private BufferedImage spriteSheet;
    private int frameWidth = 32;   // Cambia si tus frames son de otro tamaño
    private int frameHeight = 32;
    private int totalFrames = 8;   // Cambia si hay más/menos frames por fila
    private int currentFrame = 0;
    private int frameDelay = 5;
    private int frameDelayCounter = 0;
    private int ballType = 0;      // 0: tenis, 1: fútbol, 2: basket
    BallModel ballModel;
    public boolean defaultBall, crazyBall;

    public BallView(BallModel ballModel){
        this.ballModel = ballModel;
        try {
            spriteSheet = ImageIO.read(new File("app\\src\\main\\resources\\balls_sprite.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        if (spriteSheet == null || defaultBall || crazyBall) {
            if(crazyBall){
                g.setColor(new Color(randomNumber(), randomNumber(), randomNumber()));
            } else {
                g.setColor(Color.ORANGE);
            }
            g.fillOval((int)(ballModel.getPosX() - frameWidth/2),(int)(ballModel.getPosY() - frameHeight/2),frameWidth, frameHeight);
        } else {
            int x = (int)(ballModel.getPosX() - frameWidth/2);
            int y = (int)(ballModel.getPosY() - frameHeight/2);
            int sx = currentFrame * frameWidth;
            int sy = ballType * frameHeight;

            Graphics2D g2d = (Graphics2D) g.create();
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, frameWidth, frameHeight);
            g2d.setClip(circle);

            g2d.drawImage(spriteSheet, x, y, x+frameWidth, y+frameHeight,
                          sx, sy, sx+frameWidth, sy+frameHeight, null);

            g2d.dispose();

            // Avanza el frame cada ciertos ciclos para animar
            frameDelayCounter++;
            if (frameDelayCounter >= frameDelay) {
                currentFrame = (currentFrame + 1) % totalFrames;
                frameDelayCounter = 0;
            }
        }
    }

    // Permite cambiar el tipo de pelota en tiempo de ejecución
    public void setBallType(BallSkins type) {
        switch(type){
            case TENNIS -> { ballType = 0; defaultBall = false; crazyBall = false; }
            case FOOTBALL -> { ballType = 1; defaultBall = false; crazyBall = false; }
            case BASKET -> { ballType = 2; defaultBall = false; crazyBall = false; }
            case NORMAL -> { defaultBall = true; crazyBall = false; }
            case CRAZY -> { crazyBall = true; defaultBall = false; }
        }
    }

    public static int randomNumber() {
        return 50 + (int)(Math.random() * (256 - 50));
    }
}
