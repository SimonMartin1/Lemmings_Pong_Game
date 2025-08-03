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
    private int numLevel;
    private double spawnTimer = 0;
    private final double spawnInterval = 2;
    private int spawnedLemmings = 0;
    private int camX = 600;

    private boolean nukeConfirmed = false;
    private long nukeStartTime = -1;
    private long cleanDeaths = -1;

    private boolean isNukeTime = false;
    private Exit exit;

    private Buttons buttonDig;
    private Buttons buttonBuild;
    private Buttons buttonStop;
    private Buttons buttonFly;
    private Buttons buttonAcelerate; 
    private Buttons buttonSlow;
    private Buttons buttonNuke;

    //private List<LemmingModel> lemmings = new ArrayList<>();
    private List<Lemming_Entity> lemmingEntities = new ArrayList<>();
    private int lemmingSpawnX;
    private int lemmingSpawnY;
    private int LevelScore;
    private Minimap minimap; 
    private int savedLemmings;

    public Level(Game_Map map, Stock stock, int lemmingsToGenerate, double percentajeToWin, int level, String lvlName, Exit exit, int lemmingSpawnX, int lemmingSpawnY) {
        this.map = map;
        this.stock = stock;
        this.levelName = lvlName;
        this.numLevel = level;
        this.exit = exit;
        this.lemmingsToGenerate = lemmingsToGenerate;
        this.percentajeToWin = percentajeToWin;
        this.lemmingSpawnX = lemmingSpawnX;
        this.lemmingSpawnY = lemmingSpawnY;
        this.minimap = new Minimap(map, this, null);
        // Relativo a pantalla: x = porcentaje del ancho, y = porcentaje del alto
        // ancho = 100px de 768px ≈ 0.13 — alto = 150px de 600px ≈ 0.25
        float buttonWidth = 0.13f;
        float buttonHeight = 0.25f;
        float startY = 0.75f; // 450/600
        buttonAcelerate = new Buttons(":D", 0.01f, startY, 0.1f, 0.1f);
        buttonSlow = new Buttons("+", 0.01f, 0.82f, 0.1f, 0.1f);
        buttonNuke = new Buttons("-", 0.01f, 0.89f, 0.1f, 0.1f);
        buttonDig = new Buttons("Cavar | " + stock.getQuantityAbility(Ability.DIGGER), 0.01f, startY, buttonWidth, buttonHeight);
        buttonBuild = new Buttons("Parar | " + stock.getQuantityAbility(Ability.STOP), 0.16f, startY, buttonWidth, buttonHeight);
        buttonStop = new Buttons("Umbrella | " + stock.getQuantityAbility(Ability.UMBRELLA), 0.31f, startY, buttonWidth, buttonHeight);
        buttonFly = new Buttons("Escalar | " + stock.getQuantityAbility(Ability.CLIMB), 0.46f, startY, buttonWidth, buttonHeight);

    }

    public Exit getExitModel(){
        return exit;
    }

    public void update(double delta) {

        updateLemmingSpawn(delta, lemmingSpawnX, lemmingSpawnY);

        // Contar 3s luego de que isNukeTime es true
        confirmNuke();
        handleNukeConfirmed();

        lemmingEntities.removeIf(l -> l.getState() instanceof DeadState);
        lemmingEntities.removeIf(l -> l.getState() instanceof SavedState);

        handleNukeTime();

        //minimap.handleClick();

        for (Lemming_Entity l : lemmingEntities) {
            l.update(delta);
        }

        map.setCamX(camX);

    }

    /*
    public void setCamX(int camX){
        //this.camX= camX; 
        for (Lemming l : lemmings) {
            l.setCamX(camX);
        }
    }*/

    public boolean isLevelWon(){
        return savedLemmings * 10 >= percentajeToWin;
    }

    public boolean isLevelFinished() {
        boolean result = false;

        if (spawnedLemmings < lemmingsToGenerate) {
            result = false;
        } else {
            if (lemmingEntities.isEmpty()) {
                if (cleanDeaths == -1) {
                    cleanDeaths = System.currentTimeMillis();
                }

                long elapsed = System.currentTimeMillis() - cleanDeaths;

                if (elapsed >= 3000) {
                    LevelScore = getSavedLemmings() * 10;
                    result = true;
                }
            }
        }

        return result;
    }
    
    public int getLevelScore(){
        return LevelScore;
    }
    // Logic

    private void updateLemmingSpawn(double delta, int lemmingSpawnX, int lemmingSpawnY) {
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
                if(!LemmingState.DEAD.equals(l.getState())){
                    l.setState(new ExplodingState());
                }
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
        g.drawString("Nivel: " + getLevelName(), 100, 100);
        g.drawString("Salvá al menos: " + getLemmingsToGenerate() + "% de lemmings", 100, 140);
        g.fillRoundRect(325, 310, 180, 40, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Play Level", 350, 340);

        // Podés agregar botón o esperar input para comenzar
    }

    public void drawLevel(Graphics2D g, int panelWidth, int panelHeight) {

        map.draw(g);
        buttonAcelerate.drawExtraButton(g, "NASHE", panelWidth, panelHeight);
        buttonSlow.drawExtraButton(g, "+", panelWidth, panelHeight);
        buttonNuke.drawExtraButton(g, "-", panelWidth, panelHeight);
        buttonDig.draw(g,"Cavar | " + stock.getQuantityAbility(Ability.DIGGER), panelWidth, panelHeight);
        buttonStop.draw(g,"Umbrella | " + stock.getQuantityAbility(Ability.UMBRELLA), panelWidth, panelHeight);
        buttonBuild.draw(g, "parar | " + stock.getQuantityAbility(Ability.STOP) , panelWidth,panelHeight);
        buttonFly.draw(g, "Escalar | " + stock.getQuantityAbility(Ability.CLIMB), panelWidth, panelHeight);

        minimap.drawMinimap(g);

        for (Lemming_Entity view : lemmingEntities) {
            this.camX = getCamX();
            //System.out.println("camX en controller: " + camX );

            view.draw(g, camX);
            //System.out.println("Dibuje el lemming");
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Lemmings salvados: " + getSavedLemmings(), 80, 80);
    }
    
    public void drawEndScreen(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (isLevelWon()) {
            g.drawString("Nivel completado!", 200, 200);
            g.drawString("Enter para avanzar al siguiente nivel", 200, 300);
        } else {
            g.drawString("Perdiste", 200, 200);
            g.drawString("Enter para repetir el nivel", 200, 300);
            g.drawString("Escape para volver al menu", 200, 400);
        }
    }



    // Getters básicos
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

    public boolean getNukeConfirmed(){ return nukeConfirmed; }


    public void reset(){
        this.spawnedLemmings = 0;
        this.camX = 0;

        this.nukeConfirmed = false;
        this.nukeStartTime = -1;
        this.cleanDeaths = -1;

        stock.reset();

        try{
            map.reset();
        }catch (IOException e){
            e.printStackTrace();
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
