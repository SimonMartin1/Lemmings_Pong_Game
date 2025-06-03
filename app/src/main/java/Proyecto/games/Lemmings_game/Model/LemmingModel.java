package Proyecto.games.Lemmings_game.Model;

import java.awt.Color;

import Proyecto.games.Lemmings_game.View.FirstLevelMapView;

public class LemmingModel {
    int x;
    int y;
    int vx;
    int speed;
    int currentTileX;
    int currentTileY;
    FirstLevelMapView firstLevelMapView;
    FirstLevelMapModel firstLevelMapModel;
    private AbilityModel currentAbility; // Nueva línea


    public LemmingModel(int x, int y, int vx, int speed, FirstLevelMapView firstLevelMapView, FirstLevelMapModel firstLevelMapModel) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.speed = speed;
        this.currentTileX = (int)(x/8);
        this.currentTileY = (int)(y/8);
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

    
    public void update(double delta) {
       currentTileY = (int)((y)/8);
       currentTileX = (int)((x + firstLevelMapView.getCamX())/8);

        if(firstLevelMapModel.getMapTiles()[currentTileY+1][currentTileX].getColor().equals(Color.BLACK)){
            y += speed;
        }
        else{
            if(firstLevelMapModel.getMapTiles()[currentTileY-1][currentTileX+1].getColor().equals(Color.BLACK) &&
               firstLevelMapModel.getMapTiles()[currentTileY-2][currentTileX+1].getColor().equals(Color.BLACK)){
                x += speed; 
            }else{
                //x -= speed;
            }
        }

    }

/*
    public void update(double delta) {
        currentTileY = (y) / 8;
        currentTileX = (x + firstLevelMapView.getCamX()) / 8;

        if (currentAbility != null) {
            currentAbility.apply(this, delta);
            return;
        }

        // Comportamiento por defecto (caminar/caer)
        if (firstLevelMapModel.getMapTiles()[currentTileY + 1][currentTileX].getColor().equals(Color.BLACK)) {
            y += speed;
        } else {
            if (firstLevelMapModel.getMapTiles()[currentTileY - 1][currentTileX + 1].getColor().equals(Color.BLACK) &&
                firstLevelMapModel.getMapTiles()[currentTileY - 2][currentTileX + 1].getColor().equals(Color.BLACK)) {
                x += speed; 
            } else {
                //x -= speed;
            }
        }
    }
    */

    

}
