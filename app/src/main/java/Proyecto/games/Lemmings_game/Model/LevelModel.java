package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;
import Proyecto.games.Lemmings_game.Utils.LemmingState;

import java.util.ArrayList;
import java.util.List;

public class LevelModel {

    private final MapModel mapModel;
    private final Stock stock;

    private final String levelName;

    private final int lemmingsToGenerate;
    private final double percentajeToWin;
    private int numLevel;
    private double spawnTimer = 0;
    private final double spawnInterval = 2;
    private int spawnedLemmings = 0;
    private int camX = 0;

    private boolean nukeConfirmed = false;
    private long nukeStartTime = -1;
    private long cleanDeaths = -1;

    private boolean isNukeTime = false;
    private ExitModel exit;

    private List<LemmingModel> lemmings = new ArrayList<>();
    private int lemmingSpawnX;
    private int lemmingSpawnY;


    public LevelModel(MapModel map, Stock stock, int lemmingsToGenerate, double percentajeToWin, int level, String lvlName, ExitModel exit, int lemmingSpawnX, int lemmingSpawnY) {
        this.mapModel = map;
        this.stock = stock;
        this.levelName = lvlName;
        this.numLevel = level;
        this.exit = exit;
        this.lemmingsToGenerate = lemmingsToGenerate;
        this.percentajeToWin = percentajeToWin;
        this.lemmingSpawnX = lemmingSpawnX;
        this.lemmingSpawnY = lemmingSpawnY;

    }

    public ExitModel getExitModel(){
        return exit;
    }

    public void update(double delta) {

        updateLemmingSpawn(delta, lemmingSpawnX, lemmingSpawnY);

        // Contar 3s luego de que isNukeTime es true
        confirmNuke();
        handleNukeConfirmed();

        lemmings.removeIf(l -> l.state == LemmingState.DEAD);
        lemmings.removeIf(l -> l.state == LemmingState.EXITED);

        handleNukeTime();

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

    public boolean isLevelFinished() {
        boolean result = false;

        if (spawnedLemmings < lemmingsToGenerate) {
            result = false;
        } else {
            if (lemmings.isEmpty()) {
                if (cleanDeaths == -1) {
                    cleanDeaths = System.currentTimeMillis();
                }

                long elapsed = System.currentTimeMillis() - cleanDeaths;

                if (elapsed >= 3000) {
                    result = true;
                }
            }
        }

        return result;
    }

    // Logic

    private void updateLemmingSpawn(double delta, int lemmingSpawnX, int lemmingSpawnY) {
        if (spawnedLemmings < lemmingsToGenerate) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval) {
                spawnTimer = 0;
                spawnedLemmings++;
                LemmingModel nuevo = new LemmingModel(spawnedLemmings, lemmingSpawnX, lemmingSpawnY, 1, 1, mapModel);
                lemmings.add(nuevo);
            }
        }
    }

    private void confirmNuke(){
        if(isNukeTime){
            if (nukeStartTime == -1) {
                nukeStartTime = System.currentTimeMillis();
            }

            long elapsed = System.currentTimeMillis() - nukeStartTime;

            if (elapsed >= 3000) {
                nukeConfirmed = true;
            }
        }
    }

    private void handleNukeTime(){
        if(noMoreActiveLemmings()){
            if(lemmings.isEmpty()) {
                isNukeTime = false;

            }else{
                for (LemmingModel l : lemmings) {
                    if(!l.getState().equals(LemmingState.EXPLOTING)) {
                        l.setCurrentLeemingState(LemmingAnimationState.NUKE);
                    }
                }

                isNukeTime = true;
            }

        }
    }

    private void handleNukeConfirmed(){
        if (nukeConfirmed) {
            for (LemmingModel l :lemmings){
                if(!LemmingState.DEAD.equals(l.getState())){
                    l.setStateLemming(LemmingState.EXPLOTING);
                }
            }
        }
    }
    public boolean noMoreActiveLemmings() {
        boolean response = true;

        for (LemmingModel l : lemmings){
            if (!l.getState().equals(LemmingState.WAITING)) {
                response = false;
                break;
            }
        }

        return response;
    }

    // Getters básicos

    public Stock getStock(){ return  stock; }

    public int getNumLevel(){
        return numLevel;
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

    public boolean getNukeConfirmed(){ return nukeConfirmed; }
}
