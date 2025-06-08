package Proyecto.games.Lemmings_game.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnerView {
    private int x;
    private int y;
    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrameIndex = 0;
    private long lastFrameChangeTime = 0;
    private final int frameWidth = 39;   // ajustá según tu spritesheet
    private final int frameHeight = 25;
    private final int frameCount = 10;    // poné la cantidad de frames reales
    private boolean animationsLoaded = false;

    public SpawnerView(int x, int y){

        this.x = x;
        this.y = y;

        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimations() throws IOException {
        if (animationsLoaded) return;
        spriteSheet = ImageIO.read(getClass().getResourceAsStream("/lemming_spawner.png"));
        frames = new BufferedImage[frameCount];
        for (int i= 0 ; i < frameCount; i++) {
            //System.out.println("i: " + i);
            frames[i] = spriteSheet.getSubimage(i* frameWidth, 0, frameWidth, frameHeight);
        }
        animationsLoaded = true;
    }

    private void updateAnimation() {
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
            //System.out.println(" lei bien ");
            g.drawRect(x, y, frameWidth, frameHeight);
            updateAnimation();
            g.drawImage(frames[currentFrameIndex], drawX, drawY,110,70, null);
        }else{
            System.out.println("no pude leer la imagen");
        }
    }

}