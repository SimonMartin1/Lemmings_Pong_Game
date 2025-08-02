package Proyecto.games.New_Lemmings_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Gate {
    protected int x;
    protected int y;
    protected int currentFrameIndex;
    protected long lastFrameChangeTime;
    protected int frameHeight;
    protected int frameCount;;
    protected String path;
    private BufferedImage spriteSheet;
    protected BufferedImage[] frames;
    private int frameWidth;
    //private int camX;
    

    protected abstract void draw(Graphics g, int camX, int camY);

    public Gate(int x, int y, int frameWidth, int frameHeight, int frameCount, String path, int camX){
        this.x = x;
        this.y = y;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = frameCount;
        this.path = path;
        //this.camX = camX; 
    }

    protected void loadAnimations() throws IOException {
        // Carga la imagen desde el paquete (tiene que estar en src/main/resources si usás Maven)
        spriteSheet = ImageIO.read(getClass().getResourceAsStream(path));

        frames = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }

    protected void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100; // duración de cada frame en ms

        if (now - lastFrameChangeTime > frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % frameCount;
            lastFrameChangeTime = now;
        }
    }
}
