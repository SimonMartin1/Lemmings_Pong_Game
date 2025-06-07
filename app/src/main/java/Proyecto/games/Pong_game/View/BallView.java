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
    private int frameWidth = 32;   // Cada frame es de 32x32 píxeles
    private int frameHeight = 32;
    private int totalFrames = 8;   // 8 frames por tipo de pelota
    private int currentFrame = 0;
    private int frameDelay = 5;
    private int frameDelayCounter = 0;
    private int ballType = 1;      // 0: gris, 1: playa, 2: fútbol, 3: basket
    BallModel ballModel;
    public boolean defaultBall=true;

    public BallView(BallModel ballModel){
        this.ballModel = ballModel;
        try {
            spriteSheet = ImageIO.read(new File("app\\src\\main\\resources\\balls_sprite.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        if (spriteSheet == null || defaultBall) {
            g.setColor(new Color(randomNumber(), randomNumber(), randomNumber()));
            g.fillOval(
                (int)(ballModel.getPosX() - frameWidth/2),
                (int)(ballModel.getPosY() - frameHeight/2),
                frameWidth, frameHeight
            );
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
    public void setBallType(int type) {
        
        switch(type){
            case 0 -> ballType = 0; 
            case 1 -> ballType = 1; 
            case 2 -> ballType = 2; 
            default -> defaultBall=true;
        }        
    }

    public static int randomNumber() {
    return 50 + (int)(Math.random() * (256 - 50));
}
}
