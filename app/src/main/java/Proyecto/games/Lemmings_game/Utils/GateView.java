package Proyecto.games.Lemmings_game.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GateView {
    protected int x;
    protected int y;

    private BufferedImage spriteSheet;
    protected BufferedImage[] frames;
    protected int currentFrameIndex = 0;
    protected long lastFrameChangeTime = 0;

    protected int frameWidth;
    protected int frameHeight;
    protected int frameCount;

    private String path;

    public GateView(int x, int y, int frameWidth, int frameHeight, int frameCount, String path){
        this.x = x;
        this.y = y;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = frameCount;
        this.path = path;
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
