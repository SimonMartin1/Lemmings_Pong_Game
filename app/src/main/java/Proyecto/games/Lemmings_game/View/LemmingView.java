package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.LemmingAnimationState;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.Model.LemmingState;

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
    private int frameClimbLength = 8;
    private int frameExplatingFallLength = 8;

    private LemmingAnimationState lastState;

    private Map<LemmingAnimationState, BufferedImage[]> animations = new HashMap<>();

    public LemmingView(LemmingModel model) {
        this.model = model;

        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g, int camX, int camY) {
        //System.out.println("camX: " + camX + " camY: " + camY);
        int drawX = model.getX() - camX;
        //int drawY = model.getY() - camY;
        //System.out.println("drawX: " + drawX);

        if(!model.getOnExit()){
            updateAnimation();
            BufferedImage[] frames = animations.get(model.getCurrentState());

            if(lastState != model.getCurrentState()) {
                lastState = model.getCurrentState();
                currentFrameIndex = 0;
            }

            if (frames == null) return;

            BufferedImage currentFrame = frames[currentFrameIndex];
            g.drawImage(currentFrame, drawX, model.getY(), 20, 30, null);

        }
    }



    private void loadAnimations() throws IOException {

        int frameWalkWidth = 13;
        int frameWalkHeight = 21;
        frameWalkLength = 8;
        frameStopLength = 6;
        frameFallLength = 4;
        frameDigLength = 16;

        BufferedImage lemmingWalkingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_walk_v2.png"));
        BufferedImage lemmingFallingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_fall_v2.png"));
        BufferedImage lemmingDiggingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_dig.png"));
        BufferedImage lemmingStopingSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_stop_v2.png"));
        BufferedImage lemmingClimbSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_climb.png"));
        BufferedImage lemmingExplatingFallSprites = ImageIO.read(getClass().getResourceAsStream("/lemming_explanting_fall.png"));

        BufferedImage[] walkRightFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] walkLeftFrames = new BufferedImage[frameWalkLength];
        BufferedImage[] fallFrames = new BufferedImage[frameFallLength];
        BufferedImage[] digFrames = new BufferedImage[frameDigLength];
        BufferedImage[] stopFrames = new BufferedImage[frameStopLength];

        BufferedImage[] clibLeftFrame = new BufferedImage[frameClimbLength];
        BufferedImage[] clibRightFrame = new BufferedImage[frameClimbLength];

        BufferedImage[] explatingFallFrame = new BufferedImage[frameExplatingFallLength];

        // Asumiendo que fila 0 = caminar derecha, fila 1 = caminar izquierda (si no hay fila izquierda, se hace flip)
        for (int i = 0; i < frameWalkLength; i++) {
            walkRightFrames[i] = lemmingWalkingSprites.getSubimage(i * frameWalkWidth, 0, frameWalkWidth, frameWalkHeight);
        }

        // Generar caminar izquierda reflejando los frames de caminar derecha
        for (int i = 0; i < frameWalkLength; i++) {
            walkLeftFrames[i] = createFlippedImage(walkRightFrames[i]);
        }

        for (int i = 0; i < 4; i++){
            fallFrames[i] = lemmingFallingSprites.getSubimage(i * 12, 0, 12, 20);
        }

        for (int i = 0; i < 16; i++){
            digFrames[i] = lemmingDiggingSprites.getSubimage(i * 8, 0, 8, 14);
        }

        for (int i = 0; i < 6; i++){
            stopFrames[i] = lemmingStopingSprites.getSubimage(i * 20, 0, 20, 20);
        }

        for (int i = 0; i < frameClimbLength; i++){
            clibRightFrame[i] = lemmingClimbSprites.getSubimage(i * 17, 0,17, 22);
        }

        for (int i = 0; i < frameClimbLength; i++){
            clibLeftFrame[i] = createFlippedImage(clibRightFrame[i]);
        }

        for (int i = 0; i < frameExplatingFallLength; i++){
            explatingFallFrame[i] = lemmingExplatingFallSprites.getSubimage(i * 29, 0,29, 20);
        }

        //stopFrames[0] = lemmingStopingSprites.getSubimage(0, 0, 20, 20);

        animations.put(LemmingAnimationState.WALKING_RIGHT, walkRightFrames);
        animations.put(LemmingAnimationState.WALKING_LEFT, walkLeftFrames);
        animations.put(LemmingAnimationState.FALLING, fallFrames);
        animations.put(LemmingAnimationState.DIGGING, digFrames);
        animations.put(LemmingAnimationState.STOPING, stopFrames);
        animations.put(LemmingAnimationState.CLIMBING_RIGHT, clibRightFrame);
        animations.put(LemmingAnimationState.CLIMBING_LEFT, clibLeftFrame);
        animations.put(LemmingAnimationState.EXPLANTING_FALL, explatingFallFrame);
        // Aca más animaciones
    }


    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100;

        if (now - lastFrameChangeTime > frameDuration) {
            switch(model.getCurrentState()){
                case WALKING_RIGHT:
                case WALKING_LEFT:
                case CLIMBING_RIGHT:
                case CLIMBING_LEFT:
                case EXPLANTING_FALL:
                    currentFrameIndex = (currentFrameIndex + 1) % 8;
                    break;
                case FALLING:
                    currentFrameIndex = (currentFrameIndex + 1) % 4;
                    break;
                case DIGGING:
                    currentFrameIndex = (currentFrameIndex + 1) % 16;
                    break;
                case STOPING:
                    currentFrameIndex = (currentFrameIndex + 1) % 6;
                    break;
            }

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

    public LemmingModel getModel(){ return model; }
}
