package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import Proyecto.games.New_Lemmings_game.utils.LemmingState;
import Proyecto.games.New_Lemmings_game.View.Buttons;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {

    private final Game_Map map;
    private final Stock stock;

    private final String levelName;

    private final int lemmingsToGenerate;
    private final double percentajeToWin;
    private double levelTime;
    private final int numLevel;
    private double spawnTimer = 0;
    private int spawnedLemmings = 0;
    private int camX = 300;


    private long nukeStartTime = -1;
    private long cleanDeaths = -1;
    private boolean timeOver=false,isNukeTime = false,nukeConfirmed = false,levelOutcomeEvaluated = false;;
    private final Exit exit;

    private final Buttons buttonDig;
    private final Buttons buttonBuild;
    private final Buttons buttonStop;
    private final Buttons buttonFly;
    private final Buttons buttonAcelerate;
    private final Buttons buttonSlow;
    private final Buttons buttonNuke;

    private final List<Lemming_Entity> lemmingEntities = new ArrayList<>();
    private final int lemmingSpawnX;
    private final int lemmingSpawnY;
    private int LevelScore;
    private final Minimap minimap;
    private int savedLemmings;

    public Level(Game_Map map, Stock stock, int lemmingsToGenerate, double percentajeToWin, int level, String lvlName, Exit exit, int lemmingSpawnX, int lemmingSpawnY) {
        this.map = map;
        this.stock = stock;
        this.levelName = lvlName;
        this.numLevel = level;
        this.exit = exit;
        this.lemmingsToGenerate = lemmingsToGenerate;
        this.percentajeToWin = percentajeToWin;
        levelTime = calcLevelTime();
        this.lemmingSpawnX = lemmingSpawnX;
        this.lemmingSpawnY = lemmingSpawnY;
        this.minimap = new Minimap(map, this, null);
        // Relativo a pantalla: x = porcentaje del ancho, y = porcentaje del alto
        // ancho = 100px de 768px ≈ 0.13 — alto = 150px de 600px ≈ 0.25
        float buttonWidth = 0.13f;
        float buttonHeight = 0.25f;
        float startY = 0.75f; // 450/600
        buttonAcelerate = new Buttons("NASHE", 0,0.01f, startY, 0.1f, 0.1f);
        buttonSlow = new Buttons("+", 0,0.01f, 0.82f, 0.1f, 0.1f);
        buttonNuke = new Buttons("-", 0,0.01f, 0.89f, 0.1f, 0.1f);
        buttonDig = new Buttons("Cavar" ,stock.getQuantityAbility(Ability.DIGGER), 0.01f, startY, buttonWidth, buttonHeight);
        buttonBuild = new Buttons("Parar" , stock.getQuantityAbility(Ability.STOP), 0.16f, startY, buttonWidth, buttonHeight);
        buttonStop = new Buttons("Umbrella" , stock.getQuantityAbility(Ability.UMBRELLA), 0.31f, startY, buttonWidth, buttonHeight);
        buttonFly = new Buttons("Escalar" , stock.getQuantityAbility(Ability.CLIMB), 0.46f, startY, buttonWidth, buttonHeight);
    }


    public void update(double delta) {

        updateLemmingSpawn(delta, lemmingSpawnX, lemmingSpawnY);

        // Contar 3s luego de que isNukeTime es true
        confirmNuke();
        handleNukeConfirmed();
        if(spawnedLemmings >= lemmingsToGenerate && !isLevelFinished()) {
            decreaseTime();
        }
        lemmingEntities.removeIf(l -> l.getState() instanceof DeadState);
        lemmingEntities.removeIf(l -> l.getState() instanceof SavedState);

        handleNukeTime();

        for (Lemming_Entity l : lemmingEntities) {
            l.update(delta);
        }

        map.setCamX(camX);

    }


    public boolean isLevelWon() {
        double savedPercentage = (savedLemmings * 100.0) / lemmingsToGenerate;
        return savedPercentage >= percentajeToWin && levelTime > 0;
    }


    public boolean isLevelFinished() {
        boolean result = false;

        if (!(spawnedLemmings < lemmingsToGenerate)) {
            if (lemmingEntities.isEmpty()) {
                if (cleanDeaths == -1) {
                    cleanDeaths = System.currentTimeMillis();
                }

                long elapsed = System.currentTimeMillis() - cleanDeaths;

                if (elapsed >= 300) {
                    LevelScore = (getSavedLemmings() * 10) + (int)(levelTime * 2);
                    result = true;
                }
            }
        }
        if (levelTime <= 0) {
            result = true;
            timeOver=true;
        }

        return result;
    }
    
    public int getLevelScore(){
        return LevelScore;
    }


    private void updateLemmingSpawn(double delta, int lemmingSpawnX, int lemmingSpawnY) {
        double spawnInterval = 2;
        if (spawnedLemmings < lemmingsToGenerate) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval) {
                spawnTimer = 0;
                spawnedLemmings++;
                Lemming_Entity nuevo = new Lemming_Entity(spawnedLemmings, lemmingSpawnX, lemmingSpawnY, 1, this);
                lemmingEntities.add(nuevo);
            }
        }
    }

    public void decreaseTime(){
        if(levelTime!=0){
            levelTime-=0.01;
        }
    }

    public int calcLevelTime() {
        int lemmingsToSave = (int) Math.ceil(lemmingsToGenerate * (percentajeToWin / 100.0));
        int spawnTime = lemmingsToGenerate * 2 / 60;
        int extraTime = lemmingsToSave * 7; // por ejemplo 5 segundos por lemming
        return spawnTime + extraTime;
    }
    public String getFailCondition(){
        String res;
        if(timeOver){
            res="Time's Up";
        }else{
            res="You didn't save enough lemmings";
        }
        return res;
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
            if(lemmingEntities.isEmpty()) {
                isNukeTime = false;

            }else{
                for (Lemming_Entity l : lemmingEntities) {
                    if(!l.getState().equals(LemmingState.EXPLOTING)) {
                        l.setCurrentStateAnimation(LemmingAnimationState.NUKE);
                    }
                }

                isNukeTime = true;
            }

        }
    }

    private void handleNukeConfirmed(){
        if (nukeConfirmed) {
            for (Lemming_Entity l : lemmingEntities){
                //arreglar
                l.setState(new ExplodingState());
            }
        }
    }
    public boolean noMoreActiveLemmings() {
        boolean response = true;

        for (Lemming_Entity l : lemmingEntities){
            if (!l.getState().equals(new WaitingState())) {
                response = false;
                break;
            }
        }

        return response;
    }

    public void drawPreLevelScreen(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Level: " + getLevelName(), 100, 100);
        g.drawString(String.format("Save at Least: %.0f", percentajeToWin)+" % of the Lemmings", 100, 140);
        g.drawString(String.format("Time: %.0f", levelTime)+ " S", 100, 180);
        g.fillRoundRect(325, 310, 180, 40, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Play Level", 350, 340);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Click to Start", 350, 400);

        // Podés agregar botón o esperar input para comenzar
    }

    public void drawLevel(Graphics2D g, int panelWidth, int panelHeight) {

        map.draw(g);
        buttonAcelerate.drawExtraButton(g, panelWidth, panelHeight);
        buttonSlow.drawExtraButton(g, panelWidth, panelHeight);
        buttonNuke.drawExtraButton(g, panelWidth, panelHeight);
        buttonDig.draw(g,panelWidth, panelHeight);
        buttonStop.draw(g, panelWidth, panelHeight);
        buttonBuild.draw(g,panelWidth,panelHeight);
        buttonFly.draw(g,panelWidth, panelHeight);
        minimap.drawMinimap(g);

        for (Lemming_Entity view : lemmingEntities) {
            this.camX = getCamX();
            //System.out.println("camX en controller: " + camX );

            view.draw(g, camX);
            //System.out.println("Dibuje el lemming");
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString("Saved Lemmings: " + getSavedLemmings(), panelWidth-minimap.getWidth()+50, panelHeight-2.5f);
        g.drawString( String.format("Remaining Time: %.1f", levelTime)+ " S", panelWidth-minimap.getWidth()+50, panelHeight-minimap.getHeight()-30);
    }
    
    public void drawWonScreen(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (isLevelWon()) {
            g.drawString("Level Completed!", 200, 200);
            g.drawString("Level Score: " + LevelScore + "Points" , 200, 250);
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.drawString("Enter to go next level", 200, 300);
        } else {
            g.drawString("You Lose", 200, 200);
            g.drawString(getFailCondition(), 200, 270);
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.drawString("Enter to repeat level", 200, 340);
            g.drawString("Escape to go back menu", 200, 420);
        }
    }


    // Getters básicos

    public Minimap getMinimap(){
        return minimap;
    }
    public Game_Map getMap(){ return map;}

    public Stock getStock(){ return stock; }

    public int getNumLevel(){
        return numLevel;
    }

    public String getLevelName(){
        return levelName;
    }

    public void sumSavedLemmings(){
        this.savedLemmings++;
    }

    public int getSavedLemmings(){
        return savedLemmings; 
    }

    public Exit getExit(){
        return exit; 
    }
    public List<Lemming_Entity> getLemmings() {
        return lemmingEntities;
    }

    public Game_Map getMapModel(){
        return map;
    }

    public int getCamX(){
        return camX; 
    }

    public int getLemmingsToGenerate() {
        return lemmingsToGenerate;
    }

    public double getPercentajeToWin() {
        return percentajeToWin;
    }

    public boolean getlevelOutcomed(){ return levelOutcomeEvaluated; }

    public void setLevelOutcomed(boolean option){ this.levelOutcomeEvaluated = option; }


    public void reset(){
        this.spawnedLemmings = 0;
        this.camX = 0;

        this.nukeConfirmed = false;
        this.nukeStartTime = -1;
        this.cleanDeaths = -1;

        stock.reset();
        levelTime=calcLevelTime();

        try{
            map.reset();
        }catch (IOException e){
            e.printStackTrace(System.err);
        }

    }

    public void setCamX(int camX) {
        this.camX = camX; 
    }
    /*
    public Minimap getMinimap() {
        return minimap;
    }*/
}
