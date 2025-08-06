package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.Abilities.UmbrellaAbility;
import Proyecto.games.New_Lemmings_game.States.*;
import Proyecto.games.New_Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.New_Lemmings_game.Abilities.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import Proyecto.games.New_Lemmings_game.utils.LemmingSkin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lemming_Entity {
    private int id;
    private int x;
    private int y;
    private int speed;
    private int currentTileX;
    private int currentTileY;
    private int fallingStartTileY = -1; // -1 = no está cayendo
    private int currentFrameIndex = 0;
    private long lastFrameChangeTime = 0;
    private boolean isWalkingToRight = true;
    private boolean saved = false;
    private boolean isOnExit = false;
    private boolean isActive;
    private final Map<LemmingAnimationState, BufferedImage[]> animations = new HashMap<>();
    private final Map<LemmingAnimationState, Integer> frameLengths = new HashMap<>();
    private Level level;
    private LemmingSkin skinType; 

    private LemmingState currentState; // Estado del lemming actual
    private Proyecto.games.New_Lemmings_game.utils.LemmingState currentStateLemming;
    private AbilityClass currentAbility; // hablidad del lemming actual
    private LemmingAnimationState lastState; // Ultimo estado de animación
    private LemmingAnimationState currentStateAnimation; // Estado actual de animación

    // Constructor
    public Lemming_Entity(int id, int x, int y, int speed, Level level, LemmingSkin skinType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.level = level;
        this.currentState = new FallingState();  // Estado inicial
        this.currentState.onEnter(this);
        this.currentStateAnimation = LemmingAnimationState.FALLING;
        this.skinType = skinType; 

        if (skinType == LemmingSkin.SPRITE) {
            try {
                loadAnimations();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

    }

    // Lógica principal (llamada cada frame)
    public void update(double delta) {

        //Mantemos actualizados los tiles actuales
        currentTileX = x / LemmingConstants.TILE_WIDTH;
        currentTileY = y / LemmingConstants.TILE_HEIGHT;

        // Chequeamos si tiene habildad y si es que puede hacer uso de ella.
        if (hasAbility() && canUseAbility()) {
            applyAbility(delta);
        }
        else {
            currentState.update(this, delta);
        }



    }

    public boolean canUseAbility(){
        if(currentAbility == null) return false;

        return currentAbility.canUseAbility(this);
    }

    public boolean isClicked(double clickX, double clickY, int camX){
        double margenExtra = 10; // pixeles de margen que se suman a los bordes

        double minClickableX = this.x - margenExtra;
        double maxClickableX = this.x + LemmingConstants.LEMMING_WIDTH + margenExtra;

        double minClickableY = this.y - LemmingConstants.LEMMING_HEIGHT - 20;
        double maxClickableY = this.y + LemmingConstants.LEMMING_HEIGHT + margenExtra;

        double clickXCam = clickX + camX;

        boolean clickedX = clickXCam >= minClickableX && clickXCam <= maxClickableX;
        boolean clickedY = clickY >= minClickableY  && clickY <= maxClickableY ;

        return clickedX && clickedY;
    }

    public void draw(Graphics g, int camX) {
        if (getState() instanceof SavedState) {
            return;
        }

        int drawX = getX() - camX;
        int drawY = getY();

        
        if (skinType == LemmingSkin.CUADRADO) {
            // Dibujar un cuadrado rojo fijo
            g.setColor(Color.RED);
            g.fillRect(drawX, drawY, 20, 30);
            return;
        }
        
        /*Si es sprite hace esto */
        updateAnimation();

        LemmingAnimationState state = getCurrentStateAnimation();
        BufferedImage[] frames = animations.get(state);

        if (frames == null) return;

        if (lastState != state) {
            lastState = state;
            currentFrameIndex = 0;
        }

        BufferedImage currentFrame = frames[currentFrameIndex];
        g.drawImage(currentFrame, drawX, drawY, 20, 30, null);
        //System.out.println("entre aca");
    }


    private void updateAnimation() {
        long now = System.currentTimeMillis();
        long frameDuration = 100;

        if (now - lastFrameChangeTime > frameDuration) {
            LemmingAnimationState state = getCurrentStateAnimation();
            int length = frameLengths.getOrDefault(state, 1);
            currentFrameIndex = (currentFrameIndex + 1) % length;
            lastFrameChangeTime = now;
        }
    }
    
    private void loadAnimations() throws IOException {
        BufferedImage walkSprites = load("/lemmingsAnimation/lemming_walk_v2.png");
        BufferedImage fallSprites = load("/lemmingsAnimation/lemming_fall_v2.png");
        BufferedImage digSprites = load("/lemmingsAnimation/lemming_dig.png");
        BufferedImage stopSprites = load("/lemmingsAnimation/lemming_stop_v2.png");
        BufferedImage climbSprites = load("/lemmingsAnimation/lemming_climb.png");
        BufferedImage explFallSprites = load("/lemmingsAnimation/lemming_explanting_fall.png");
        BufferedImage umbrellaSprites = load("/lemmingsAnimation/lemming_umbrella.png");
        BufferedImage nukeSprites = load("/lemmingsAnimation/lemming_nuke.png");


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

    // Setter para el nuevo estado
    public void setState(LemmingState newState) {
        if (currentState != null) currentState.onExit(this);
        currentState = newState;
        if (newState != null) newState.onEnter(this);
    }

    // Habilidad aplicada (aún no delegada)
    public boolean hasAbility() {
        return currentAbility != null;
    }

    public void assignAbility(AbilityClass ability) {
        this.currentAbility = ability;
    }

    public void clearAbility() {
        this.currentAbility = null;
    }

    public void applyAbility(double delta) {
        currentAbility.apply(this, delta);
    }

    public AbilityClass getAbilityClass(){
        return currentAbility; 
    }

    public void startFalling() {
        fallingStartTileY = getTileY();
    }

    public void stopFalling() {
        fallingStartTileY = -1;
    }

    public boolean isFalling() {
        return fallingStartTileY != -1;
    }

    public int getTilesFallen() {
        if (!isFalling()) return 0;
        return getTileY() - fallingStartTileY;
    }

    public boolean isGoingToDieFromFall() {
        return getTilesFallen() > 25 && !hasUmbrella();
    }

    // Getters & setters

    public LemmingState getState(){ return currentState;}
    public int getId() { return id; }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean hasUmbrella(){
        return currentState instanceof UmbrellaAbility;
    }

    public boolean isWalkingToRight() { return isWalkingToRight; }
    public void setWalkingToRight(boolean b) { this.isWalkingToRight = b; }

    public Level getLevel() { return level; }

    public LemmingAnimationState getCurrentStateAnimation() {
        return currentStateAnimation;
    }

    public void setCurrentStateAnimation(LemmingAnimationState anim) {
        this.currentStateAnimation = anim;
    }

    public void setAnimationState(LemmingAnimationState lemmingAnimationState){ this.currentStateAnimation = lemmingAnimationState;}

    public boolean isSaved() { return saved; }
    public void setSaved(boolean s) { this.saved = s; }

    public boolean isOnExit() { return isOnExit; }
    public void setOnExit(boolean o) { this.isOnExit = o; }
    public void setAbility(AbilityClass abilityClass){ this.currentAbility = abilityClass;  }

    public void setActivite(boolean isActive){this.isActive = isActive; }
    public int getSpeed() { return speed; }
    public void setSpeed(int s) { this.speed = s; }
    public void setFallingStartTileY(int fallingStartTileY){ this.fallingStartTileY = fallingStartTileY; }

    public int getTileX() { return currentTileX; }
    public int getTileY() { return currentTileY; }
}
