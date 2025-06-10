package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Utils.GateView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ExitView extends GateView {

    public ExitView(int x, int y) {

        super(x,y,33, 25, 1, "/lemming_exit.png");

        try {
            this.loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int camX, int camY) {
        int drawX = x - camX;
        int drawY = y - camY;
        if (frames != null && frames[currentFrameIndex] != null) {
            updateAnimation();
            g.drawImage(frames[currentFrameIndex], drawX, drawY,100 ,80,null);
        }
    }
}
