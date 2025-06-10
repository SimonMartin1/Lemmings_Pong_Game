package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;

public class LemmingModel {
    private int id;
    private int x;
    private int y;
    private int vx;
    private int speed;
    private int currentTileX;
    private int currentTileY;
    MapModel firstLevelMapModel;
    private AbilityModel currentAbility; // Nueva línea
    boolean isWalkingToRight = true;
    boolean isStartingToWalk = false;
    LemmingAnimationState currentStateAnimation = LemmingAnimationState.WALKING_RIGHT;
    boolean isOnExit = false;
    boolean saved = false;
    LemmingState state = LemmingState.ALIVE;
    int camX = 0;
    //ventana
    int offsetY = 0;
    int offsetY2 = 0; 
    //pantalla completa
    //int offsetY = 0;
    //int offsetY2 = 0;

    int lastTileBeforeFalling;
    int quantityTilesFalling = 0;
    boolean isDead = false;
    int currentFrame = 0;
    int ticksPerFrame = 6;  // Ajustá esto para la velocidad
    int tickCounter = 0;
    boolean isFinishedDeathAnimation = false;

    public int getCamX(){ return this.camX; }

    public void setCamX(int camX){
        this.camX = camX;
    }

    public LemmingModel(int id, int x, int y, int vx, int speed, MapModel firstLevelMapModel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.speed = speed;
        this.currentTileX = x/LemmingConstants.TILE_WIDTH;
        this.currentTileY = y/LemmingConstants.TILE_HEIGHT;
        this.firstLevelMapModel = firstLevelMapModel;
    }

    public void setAbility(AbilityModel ability) {
        this.currentAbility = ability;
    }

    public AbilityModel getCurrentAbility(){
        return currentAbility;
    }

    public void clearAbility() {
        this.currentAbility = null;
    }

    public MapModel getMap() {
        return firstLevelMapModel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y){
        this.y = y; 
    }

    public void setX(int x){
        this.x = x;
    }

    public void setCurrentLeemingState(LemmingAnimationState currentState){
        this.currentStateAnimation = currentState;
    }

    public boolean hasAbility(){ return currentAbility != null;}

    public void update(double delta) {
        //le actualizamos el camX probar
        camX = firstLevelMapModel.getCameraX();

        currentTileY = (y)/LemmingConstants.TILE_HEIGHT;

        currentTileX = (x) /LemmingConstants.TILE_WIDTH;

        if(hasAbility()){
            applyHability(delta);
        }
        else{
            if(shouldItFall()){

                if(quantityTilesFalling - lastTileBeforeFalling > 20 && isStartingToWalk) isDead = true;


                fall();
            }
            else{
                isStartingToWalk = true;
                walk();
            }
        }


        checkExit();

    }


    public void applyHability(double delta){

        switch (currentAbility.getName()){
            case Ability.DIGGER -> currentStateAnimation = LemmingAnimationState.DIGGING;
        }

        currentAbility.apply(this, delta);
    }


    public void checkExit(){
        if(firstLevelMapModel.getExit().checkLemming(this)){
            firstLevelMapModel.getExit().sumLemming(this);
            isOnExit = true;
        }
    }

    public boolean getOnExit(){
        return isOnExit;
    }

    public boolean isSaved() {
        return saved;
    }
    
    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    public void walk(){
        lastTileBeforeFalling = currentTileY;

        if(isDead) {
            currentStateAnimation = LemmingAnimationState.EXPLANTING_FALL;

            tickCounter++;

            if (tickCounter >= ticksPerFrame) {
                tickCounter = 0;
                currentFrame++;

                if (currentFrame >= 8) {  // Suponiendo que la animación de morir tiene 8 cuadros
                    isFinishedDeathAnimation = true;
                    state = LemmingState.DEAD;
                }
            }

        }
        else {
            if (isWalkingToRight) {
                currentStateAnimation = LemmingAnimationState.WALKING_RIGHT;

                //subida
                if (Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY - 1, currentTileX))) {
                    y -= speed;
                }

                if (Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY - 2, currentTileX + 1))) {
                    x += speed;
                } else {
                    isWalkingToRight = false;
                }
            } else {
                currentStateAnimation = LemmingAnimationState.WALKING_LEFT;

                //subida
                if (Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY - 1, currentTileX))) {
                    y -= speed;
                }

                if (Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY - 2, currentTileX - 1))) {
                    x -= speed;
                } else {
                    isWalkingToRight = true;
                }
            }
        }

    }

    public boolean shouldItFall(){

        return Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 2, currentTileX)) && isWalkingToRight ||
                Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 2, currentTileX + 1)) && !isWalkingToRight;

    }


    public void fall(){

        if(Color.BLACK.equals(firstLevelMapModel.getMapTiles()[currentTileY + 4][currentTileX].getColor())){
            currentStateAnimation = LemmingAnimationState.FALLING;
        }

        quantityTilesFalling = currentTileY;
        y += speed;
    }

    public boolean isClicked(double clickX, double clickY, int camX){
        double minClickableX = (this.x);

        double maxClickableX = minClickableX + LemmingConstants.LEMMING_WIDTH;
    
        double minClickableY = this.y - LemmingConstants.LEMMING_HEIGHT;
        double maxClickableY = this.y + LemmingConstants.LEMMING_HEIGHT;
    
        double clickXCam = clickX + camX;
    
        System.out.println("---- Click Detection ----");
        System.out.println("Click en pantalla: (" + clickX + ", " + clickY + ")");
        System.out.println("Click ajustado con camX: " + clickXCam);
        System.out.println("Lemming X range: [" + minClickableX + " , " + maxClickableX + "]");
        System.out.println("Lemming Y range: [" + (minClickableY - 20) + " , " + (maxClickableY - 30) + "]");
        System.out.println("camX: " + camX);
        System.out.println("Lemming actual en: (" + this.x + ", " + this.y + ")");
    
        boolean clickedX = clickXCam >= minClickableX && clickXCam <= maxClickableX;
        boolean clickedY = clickY >= minClickableY - offsetY && clickY <= maxClickableY - offsetY2;
    
        System.out.println("¿Está dentro del rango X?: " + clickedX);
        System.out.println("¿Está dentro del rango Y?: " + clickedY);
        System.out.println("¿CLICK VALIDO?: " + (clickedX && clickedY));
        System.out.println("-------------------------");
        return clickedX && clickedY;
    }
    

    public void assignAbility(AbilityModel ability){

        if(!currentStateAnimation.equals(LemmingAnimationState.STOPING)){
            this.currentAbility = ability;
        }
    }

    public boolean isWalkingToRight(){
        return isWalkingToRight;
    }


    public LemmingAnimationState getCurrentStateAnimation(){
        return currentStateAnimation;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public boolean isActive() {
        return state == LemmingState.ALIVE;
    }

    public void setStateLemming(LemmingState state){
        this.state = state;
    }
}
