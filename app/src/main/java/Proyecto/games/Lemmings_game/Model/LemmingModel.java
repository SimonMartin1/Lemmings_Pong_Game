package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

import Proyecto.games.Lemmings_game.Constants.LemmingConstants;
import Proyecto.games.Lemmings_game.View.FirstLevelMapView;

public class LemmingModel {
    int id;
    int x;
    int y;
    int vx;
    int speed;
    int currentTileX;
    int currentTileY;
    FirstLevelMapView firstLevelMapView;
    FirstLevelMapModel firstLevelMapModel;
    private AbilityModel currentAbility; // Nueva línea
    boolean isWalkingToRight = true;


    public LemmingModel(int id, int x, int y, int vx, int speed, FirstLevelMapView firstLevelMapView, FirstLevelMapModel firstLevelMapModel) {
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

    public void clearAbility() {
        this.currentAbility = null;
    }

    public FirstLevelMapModel getMap() {
        return firstLevelMapModel;
    }

    public FirstLevelMapView getView() {
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

    public void setCurrentAbility(AbilityModel currentAbility){ this.currentAbility = currentAbility; }

    public boolean hasAbility(){ return currentAbility != null;}

    public void update(double delta) {
       currentTileY = (y)/LemmingConstants.TILE_HEIGHT;
       currentTileX = (x + firstLevelMapView.getCamX())/LemmingConstants.TILE_WIDTH;

        if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY+1, currentTileX))){
            y += speed;
        }
        else{
            applyHability(delta);
        }

    }

    public void applyHability(double delta){

        if(currentAbility != null){
            currentAbility.apply(this, delta);
        }
        else{
            walk();
        }
    }

    public void walk(){

        if(isWalkingToRight){
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

            //subida
            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-1, currentTileX))){
                y -= speed;
            }

            if(Color.BLACK.equals(firstLevelMapModel.getTileColor(currentTileY-2, currentTileX-1))){
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

        System.out.println("M_X: "+ clickedX + " - [" + minClickableX + ", " +maxClickableX + "]");
        System.out.println("M_X: "+ clickedY + " - [" + minClickableY + ", " +maxClickableY + "]");

        return clickedX && clickedY;
    }

    public void assignAbility(AbilityModel ability){
        this.currentAbility = ability;
    }

}
