package Proyecto.games.Lemmings_game.View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ExitView {

    private final int x;
    private final int y;

    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrameIndex = 0;
    private long lastFrameChangeTime = 0;

    private final int frameWidth = 33;   // ajustá según tu spritesheet
    private final int frameHeight = 25;
    private final int frameCount = 1;    // poné la cantidad de frames reales

    public ExitView(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimations() throws IOException {
        // Carga la imagen desde el paquete (tiene que estar en src/main/resources si usás Maven)
        spriteSheet = ImageIO.read(getClass().getResourceAsStream("/lemming_exit.png"));

        frames = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }

    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100; // duración de cada frame en ms

        if (now - lastFrameChangeTime > frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % frameCount;
            lastFrameChangeTime = now;
        }
    }

    public void draw(Graphics g) {
        if (frames != null && frames[currentFrameIndex] != null) {
            updateAnimation();
            g.drawImage(frames[currentFrameIndex], x, y,100 ,80,null);
        }
    }
}
