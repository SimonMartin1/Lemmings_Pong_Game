package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.Lemmings_game.View.MapView;

import javax.imageio.ImageIO;

public class LemmingModel {
    int id;
    int x;
    int y;
    int vx;
    int speed;
    int currentTileX;
    int currentTileY;
    MapView firstLevelMapView;
    MapModel firstLevelMapModel;
    private AbilityModel currentAbility; // Nueva línea
    boolean isWalkingToRight = true;
    boolean isStartingToWalk = false;
    LemmingAnimationState currentState = LemmingAnimationState.WALKING_RIGHT;
    boolean isOnExit = false;
    boolean saved = false;
    LemmingState state = LemmingState.ALIVE;

    int camX = 0;

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

    public MapView getView() {
        return firstLevelMapView;
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

    public void setCurrentLeemingState(LemmingAnimationState currentState){
        this.currentState = currentState;
    }

    public boolean hasAbility(){ return currentAbility != null;}

    public void update(double delta) {

       currentTileY = (y)/LemmingConstants.TILE_HEIGHT;
       currentTileX = (x + 430) /LemmingConstants.TILE_WIDTH;

        if(hasAbility()){
            applyHability(delta);
        }
        else{
            if(shouldItFall()){
                fall();
            }
            else{
                //System.out.println("CAMINO");
                walk();
            }
        }


        checkExit();

    }


    public void applyHability(double delta){

        switch (currentAbility.getName()){
            case Ability.DIGGER -> currentState = LemmingAnimationState.DIGGING;
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

        if(isWalkingToRight){
            currentState = LemmingAnimationState.WALKING_RIGHT;

            //subida
            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-1, currentTileX)) ){
                y -= speed;
            }

            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-2, currentTileX+1))){
                x += speed;
            }
            else{
                isWalkingToRight = false;
            }
        }
        else{
            currentState = LemmingAnimationState.WALKING_LEFT;

            //subida
            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-1, currentTileX))){
                y -= speed;
            }

            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-2, currentTileX - 1))){
                x -= speed;
            }
            else{
                isWalkingToRight = true;
            }
        }

    }

    public boolean shouldItFall(){

        return Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 2, currentTileX)) && isWalkingToRight ||
                Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 2, currentTileX + 1)) && !isWalkingToRight;

    }


    public void fall(){

        if(Color.BLACK.equals(firstLevelMapModel.getMapTiles()[currentTileY + 4][currentTileX].getColor())){
            currentState = LemmingAnimationState.FALLING;
        }

        y += speed;
    }

    public boolean isClicked(double clickX, double clickY){

        double minClickableX = this.x - LemmingConstants.LEMMING_WIDTH;
        double maxClickableX = this.x + LemmingConstants.LEMMING_WIDTH;
        double minClickableY = this.y - LemmingConstants.LEMMING_HEIGHT;
        double maxClickableY = this.y + LemmingConstants.LEMMING_HEIGHT;

        boolean clickedX = clickX >= minClickableX && clickX <= maxClickableX;
        boolean clickedY = clickY >= minClickableY - 20 && clickY <= maxClickableY - 30;

        return clickedX && clickedY;
    }

    public void assignAbility(AbilityModel ability){

        if(!currentState.equals(LemmingAnimationState.STOPING)){
            this.currentAbility = ability;
        }
    }

    public boolean isWalkingToRight(){
        return isWalkingToRight;
    }


    public LemmingAnimationState getCurrentState(){
        return currentState;
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
