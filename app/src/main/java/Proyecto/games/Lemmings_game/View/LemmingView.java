package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.LemmingAnimationState;
import Proyecto.games.Lemmings_game.Model.LemmingModel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LemmingView{

    private LemmingModel model;

    private int currentFrameIndex = 0;
    private long lastFrameChangeTime = 0;

    private Map<LemmingAnimationState, BufferedImage[]> animations = new HashMap<>();

    public LemmingView(LemmingModel model) {
        this.model = model;

        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        updateAnimation();

        BufferedImage[] frames = animations.get(model.getCurrentState());
        if (frames == null) return;
        BufferedImage currentFrame = frames[currentFrameIndex];
        g.drawImage(currentFrame, model.getX(), model.getY(), 13, 20, null);

        //g.drawImage(currentFrame, model.getX(), model.getY(), null);
    }



    private void loadAnimations() throws IOException {

        int frameWalkWidth = 6;
        int frameWalkHeight = 10;
        int frameWalkLength = 8;

        BufferedImage lemmingWalkingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_walk.png"));
        BufferedImage lemmingFallingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_fall.png"));
        BufferedImage lemmingDiggingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_dig.png"));

        BufferedImage[] walkRightFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] walkLeftFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] fallFrames = new BufferedImage[8];
        BufferedImage[] digFrames = new BufferedImage[16];


        // Asumiendo que fila 0 = caminar derecha, fila 1 = caminar izquierda (si no hay fila izquierda, se hace flip)
        for (int i = 0; i < frameWalkLength; i++) {
            walkRightFrames[i] = lemmingWalkingSprites.getSubimage(i * frameWalkWidth, 0, frameWalkWidth, frameWalkHeight);
        }

        // Generar caminar izquierda reflejando los frames de caminar derecha
        for (int i = 0; i < frameWalkLength; i++) {
            walkLeftFrames[i] = createFlippedImage(walkRightFrames[i]);
        }

        for (int i = 0; i < 8; i++){
            fallFrames[i] = lemmingFallingSprites.getSubimage(i * 6, 0, 6, 10);
        }

        for (int i = 0; i < 16; i++){
            digFrames[i] = lemmingDiggingSprites.getSubimage(i * 8, 0, 8, 14);
        }

        animations.put(LemmingAnimationState.WALKING_RIGHT, walkRightFrames);
        animations.put(LemmingAnimationState.WALKING_LEFT, walkLeftFrames);
        animations.put(LemmingAnimationState.FALLING, fallFrames);
        animations.put(LemmingAnimationState.DIGGING, digFrames);
        // Aca más animaciones
    }


    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100;

        if (now - lastFrameChangeTime > frameDuration) {
            currentFrameIndex = (currentFrameIndex + 1) % 8;
            lastFrameChangeTime = now;
        }
    }

    private BufferedImage createFlippedImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = flipped.createGraphics();
        g.drawImage(image, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flipped;
    }
}
