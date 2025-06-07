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


    public LemmingModel(int id, int x, int y, int vx, int speed, MapView firstLevelMapView, MapModel firstLevelMapModel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.speed = speed;
        this.currentTileX = x/LemmingConstants.TILE_WIDTH;
        this.currentTileY = y/LemmingConstants.TILE_HEIGHT;
        this.firstLevelMapView  = firstLevelMapView;    
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

    public void setCurrentAbility(AbilityModel currentAbility){ this.currentAbility = currentAbility; }

    public boolean hasAbility(){ return currentAbility != null;}

    public void update(double delta) {
       currentTileY = (y)/LemmingConstants.TILE_HEIGHT;
       currentTileX = (x + firstLevelMapView.getCamX())/LemmingConstants.TILE_WIDTH;

        //System.out.println("mi speed es: " + speed);

        if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 1, currentTileX)) && isWalkingToRight ||
           Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY + 1, currentTileX + 1)) && !isWalkingToRight
        ){
            currentState = LemmingAnimationState.FALLING; //hallar el problema de que no cae
            //if(!isStartingToWalk)
            y += speed;
            return;
        }
        else{
            //applyHability(delta);
        }
        
        // si no está cayendo, sigue con la habilidad o caminando
        applyHability(delta);
        

        //System.out.println(currentState);

    }

    public void applyHability(double delta){

        if(currentAbility != null){

            /*
            switch (currentAbility.getName()){
                case Ability.DIGGER -> currentState = LemmingAnimationState.DIGGING;
            }
             */

            currentAbility.apply(this, delta);
        }
        else{
            
            walk();
        }
    }

    public void walk(){
        isStartingToWalk = true;

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

    public boolean isClicked(double clickX, double clickY){

        double minClickableX = this.x - LemmingConstants.LEMMING_WIDTH;
        double maxClickableX = this.x + LemmingConstants.LEMMING_WIDTH;
        double minClickableY = this.y - LemmingConstants.LEMMING_HEIGHT;
        double maxClickableY = this.y + LemmingConstants.LEMMING_HEIGHT;

        boolean clickedX = clickX >= minClickableX && clickX <= maxClickableX;
        boolean clickedY = clickY >= minClickableY - 20 && clickY <= maxClickableY - 30;

        //System.out.println("M_X: "+ clickedX + " - [" + minClickableX + ", " +maxClickableX + "]");
        //System.out.println("M_X: "+ clickedY + " - [" + minClickableY + ", " +maxClickableY + "]");

        return clickedX && clickedY;
    }

    public void assignAbility(AbilityModel ability){
        this.currentAbility = ability;
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
}
