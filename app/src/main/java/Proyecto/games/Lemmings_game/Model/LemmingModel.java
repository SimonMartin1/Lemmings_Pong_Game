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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(double delta) {
       currentTileY = (int)((y)/8);
       currentTileX = (int)((x + firstLevelMapView.getCamX())/8);

       if(firstLevelMapModel.getMapTiles()[currentTileY+1][currentTileX].getColor().equals(Color.BLACK)){
            y += speed;
       }else{
            if(firstLevelMapModel.getMapTiles()[currentTileY-1][currentTileX+1].getColor().equals(Color.BLACK) &&
               firstLevelMapModel.getMapTiles()[currentTileY-2][currentTileX+1].getColor().equals(Color.BLACK)){
                x += speed; 
            }
       }

    }

}
