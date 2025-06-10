package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LemmingView {

    private final LemmingModel model;

    private int currentFrameIndex = 0;
    private long lastFrameChangeTime = 0;

    private LemmingAnimationState lastState;
    private final Map<LemmingAnimationState, BufferedImage[]> animations = new HashMap<>();
    private final Map<LemmingAnimationState, Integer> frameLengths = new HashMap<>();

    public LemmingView(LemmingModel model) {
        this.model = model;
        try {
            loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int camX, int camY) {
        if (model.getOnExit()) return;

        int drawX = model.getX() - camX;
        int drawY = model.getY();

        updateAnimation();

        LemmingAnimationState state = model.getCurrentStateAnimation();
        BufferedImage[] frames = animations.get(state);

        if (frames == null) return;

        if (lastState != state) {
            lastState = state;
            currentFrameIndex = 0;
        }

        BufferedImage currentFrame = frames[currentFrameIndex];
        g.drawImage(currentFrame, drawX, drawY, 20, 30, null);
    }

    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100;

        if (now - lastFrameChangeTime > frameDuration) {
            LemmingAnimationState state = model.getCurrentStateAnimation();
            int length = frameLengths.getOrDefault(state, 1);
            currentFrameIndex = (currentFrameIndex + 1) % length;
            lastFrameChangeTime = now;
        }
    }

    private void loadAnimations() throws IOException {
        BufferedImage walkSprites = load("/lemming_walk_v2.png");
        BufferedImage fallSprites = load("/lemming_fall_v2.png");
        BufferedImage digSprites = load("/lemming_dig.png");
        BufferedImage stopSprites = load("/lemming_stop_v2.png");
        BufferedImage climbSprites = load("/lemming_climb.png");
        BufferedImage explFallSprites = load("/lemming_explanting_fall.png");
        BufferedImage umbrellaSprites = load("/lemming_umbrella.png");
        BufferedImage nukeSprites = load("/lemming_nuke.png");


        // Animations

        animations.put(LemmingAnimationState.WALKING_RIGHT, sliceFrames(walkSprites, 13, 21, 8, 0));
        animations.put(LemmingAnimationState.WALKING_LEFT, flipFrames(animations.get(LemmingAnimationState.WALKING_RIGHT)));

        animations.put(LemmingAnimationState.FALLING, sliceFrames(fallSprites, 12, 20, 4, 0));
        animations.put(LemmingAnimationState.DIGGING, sliceFrames(digSprites, 8, 14, 16, 0));
        animations.put(LemmingAnimationState.STOPING, sliceFrames(stopSprites, 20, 20, 6, 0));

        animations.put(LemmingAnimationState.CLIMBING_RIGHT, sliceFrames(climbSprites, 17, 22, 8, 0));
        animations.put(LemmingAnimationState.CLIMBING_LEFT, flipFrames(animations.get(LemmingAnimationState.CLIMBING_RIGHT)));

        animations.put(LemmingAnimationState.EXPLANTING_FALL, sliceFrames(explFallSprites, 29, 20, 8, 0));

        animations.put(LemmingAnimationState.UMBRELLA_RIGHT, sliceFrames(umbrellaSprites, 20, 31, 10, 0));
        animations.put(LemmingAnimationState.UMBRELLA_LEFT, sliceFrames(umbrellaSprites, 20, 31, 10, 0));

        animations.put(LemmingAnimationState.NUKE, sliceFrames(nukeSprites, 16, 20, 12,0));

        //state - length
        frameLengths.put(LemmingAnimationState.WALKING_RIGHT, 8);
        frameLengths.put(LemmingAnimationState.WALKING_LEFT, 8);
        frameLengths.put(LemmingAnimationState.FALLING, 4);
        frameLengths.put(LemmingAnimationState.DIGGING, 16);
        frameLengths.put(LemmingAnimationState.STOPING, 6);
        frameLengths.put(LemmingAnimationState.CLIMBING_RIGHT, 8);
        frameLengths.put(LemmingAnimationState.CLIMBING_LEFT, 8);
        frameLengths.put(LemmingAnimationState.EXPLANTING_FALL, 8);
        frameLengths.put(LemmingAnimationState.UMBRELLA_RIGHT, 10);
        frameLengths.put(LemmingAnimationState.UMBRELLA_LEFT, 10);
        frameLengths.put(LemmingAnimationState.NUKE, 12);
    }

    private BufferedImage[] sliceFrames(BufferedImage spriteSheet, int frameWidth, int frameHeight, int frameCount, int row) {
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, row * frameHeight, frameWidth, frameHeight);
        }
        return frames;
    }

    private BufferedImage[] flipFrames(BufferedImage[] original) {
        BufferedImage[] flipped = new BufferedImage[original.length];
        for (int i = 0; i < original.length; i++) {
            flipped[i] = createFlippedImage(original[i]);
        }
        return flipped;
    }

    private BufferedImage createFlippedImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = flipped.createGraphics();
        g.drawImage(image, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flipped;
    }

    private BufferedImage load(String path) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(path));
    }

    public LemmingModel getModel() {
        return model;
    }
}
