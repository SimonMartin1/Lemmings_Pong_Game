package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.LemmingState;

import java.util.ArrayList;
import java.util.List;

public class LevelModel {

    private MapModel mapModel;
    private Stock stock;

    private String levelName;

    private int lemmingsToGenerate;
    private double percentajeToWin;
    private int numlevel;
    private double spawnTimer = 0;
    private final double spawnInterval = 2;
    private int spawnedLemmings = 0;
    private int camX = 0;
    private ExitModel exit;
    private List<LemmingModel> lemmings = new ArrayList<>();


    public LevelModel(MapModel map, Stock stock, int lemmingsToGenerate, double percentajeToWin, int level, String lvlName, ExitModel exit) {
        this.mapModel = map;
        this.stock = stock;
        this.levelName = lvlName;
        this.numlevel = level;
        this.exit = exit;
        this.lemmingsToGenerate = lemmingsToGenerate;
        this.percentajeToWin = percentajeToWin;

    }

    public ExitModel getExitModel(){
        return exit;
    }

    public void update(double delta) {

        if (spawnedLemmings < lemmingsToGenerate) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval) {
                spawnTimer = 0;
                spawnedLemmings++;
                LemmingModel nuevo = new LemmingModel(spawnedLemmings, 500, 205, 1, 1, mapModel);
                //LemmingModel nuevo = new LemmingModel(1, 720, 120, 1, 1, mapModel);
                lemmings.add(nuevo);
            }
        }


        lemmings.removeIf(l -> l.state == LemmingState.DEAD);

        lemmings.removeIf(l -> l.state == LemmingState.EXITED);

        for (LemmingModel l : lemmings) {
            l.update(delta);
        }

        mapModel.setCamX(camX);

    }


    public void setCamX(int camX){
        //this.camX= camX; 
        for (LemmingModel l : lemmings) {
            l.setCamX(camX);
        }
    }

    public boolean isLevelWon(){
        return mapModel.getLemmingsSaved() >= lemmingsToGenerate * percentajeToWin;
    }

    public boolean isLevelFinished(){

        if(lemmings.size() < lemmingsToGenerate) return false;

        for (LemmingModel l : lemmings) {
            if (l.isActive()) {
                return false;
            }
        }

        return true;
    }




    // Getters básicos

    public Stock getStock(){ return  stock; }

    public int getNumLevel(){
        return numlevel;
    }

    public String getLevelName(){
        return levelName;
    }

    public List<LemmingModel> getLemmings() {
        return lemmings;
    }

    public MapModel getMapModel(){
        return mapModel;
    }

    public int getLemmingsToGenerate() {
        return lemmingsToGenerate;
    }

    public double getPercentajeToWin() {
        return percentajeToWin;
    }

}
