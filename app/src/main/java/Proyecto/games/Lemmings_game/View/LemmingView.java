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
    private int frameWalkLength = 8;
    private int frameFallLength = 8;
    private int frameDigLength = 16;
    private int frameStopLength = 1;    

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

        if(!model.getOnExit()){
            updateAnimation();
            BufferedImage[] frames = animations.get(model.getCurrentState());
            if (frames == null) return;
            BufferedImage currentFrame = frames[currentFrameIndex];
            g.drawImage(currentFrame, model.getX(), model.getY(), 20, 30, null);
        }
        //g.drawImage(currentFrame, model.getX(), model.getY(), null);
    }



    private void loadAnimations() throws IOException {

        int frameWalkWidth = 6;
        int frameWalkHeight = 10;
        frameWalkLength = 8;
        frameStopLength = 1;
        frameFallLength = 8;
        frameDigLength = 16;

        BufferedImage lemmingWalkingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_walk.png"));
        BufferedImage lemmingFallingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_fall.png"));
        BufferedImage lemmingDiggingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_dig.png"));
        BufferedImage lemmingStopingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_stop.png"));

        BufferedImage[] walkRightFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] walkLeftFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] fallFrames = new BufferedImage[frameFallLength];
        BufferedImage[] digFrames = new BufferedImage[frameDigLength];
        BufferedImage[] stopFrames = new BufferedImage[frameStopLength];

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

        stopFrames[0] = lemmingStopingSprites.getSubimage(0, 0, 10, 10);

        animations.put(LemmingAnimationState.WALKING_RIGHT, walkRightFrames);
        animations.put(LemmingAnimationState.WALKING_LEFT, walkLeftFrames);
        animations.put(LemmingAnimationState.FALLING, fallFrames);
        animations.put(LemmingAnimationState.DIGGING, digFrames);
        animations.put(LemmingAnimationState.STOPING, stopFrames);
        // Aca más animaciones
    }


    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100;

        if (now - lastFrameChangeTime > frameDuration) {
            switch(model.getCurrentState()){
                case WALKING_RIGHT: 
                    currentFrameIndex = (currentFrameIndex + 1) % 8;
                    break;
                case WALKING_LEFT:
                    currentFrameIndex = (currentFrameIndex + 1) % 8;
                case FALLING:
                    currentFrameIndex = (currentFrameIndex + 1) % 8;
                    break;
                case DIGGING:
                    currentFrameIndex = (currentFrameIndex + 1) % 8;
                    break;
                case STOPING:
                    currentFrameIndex = (currentFrameIndex + 1) % 1;
                    break;
            }
            //currentFrameIndex = (currentFrameIndex + 1) % 1; // tiene que ser una variable que se pueda cambiar
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
