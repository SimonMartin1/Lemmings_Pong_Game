package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Utils.GateView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnerView extends GateView {

    private final int frameWidth = 39;   // ajustá según tu spritesheet
    private final int frameHeight = 25;
    private final int frameCount = 10;    // poné la cantidad de frames reales

    private boolean animationsLoaded = false;

    public SpawnerView(int x, int y){

        super(x, y, 39, 25,10, "/lemming_spawner.png");

        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loadAnimations() throws IOException {
        if (animationsLoaded) return;

        super.loadAnimations();

        animationsLoaded = true;
    }


    protected void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 300; // duración de cada frame en ms

        if (now - lastFrameChangeTime > frameDuration) {
            if (currentFrameIndex < frameCount - 1) {
                currentFrameIndex++;
                lastFrameChangeTime = now;
            }

            // Si ya está en el último frame, no hace nada
        }
    }


    public void draw(Graphics g, int camX, int camY) {
        int drawX = x - camX;
        int drawY = y - camY;
        if (frames != null && frames[currentFrameIndex] != null) {
            updateAnimation();
            g.drawImage(frames[currentFrameIndex], drawX, drawY,110,70, null);
        }else{
            System.out.println("no pude leer la imagen");
        }
    }

}