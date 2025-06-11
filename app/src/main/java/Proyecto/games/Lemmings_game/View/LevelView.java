package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.Model.Stock;

import java.awt.*;

public class LevelView {
    private LevelModel model;
    private MapView mapView;

    private Stock stock;
    private MinimapView minimapView;

    private Buttons buttonDig;
    private Buttons buttonBuild;
    private Buttons buttonStop;
    private Buttons buttonFly;
    private Buttons buttonAcelerate; 
    private Buttons buttonSlow;
    private Buttons buttonNuke;
    private int camX;
    private int camY;

    //int screenWidth = 1366;
    //int screenHeight = 768;
    private int screenWidth;
    private int screenHeight;
    
    int baseX = 850;
    int baseY = 600;
    int baseWidth = 250;
    int baseHeight = 100;

    public LevelView(LevelModel model, MapView mapView, int screenWidth, int screenHeight) {
        this.mapView = mapView;
        this.model = model;
        this.stock = model.getStock();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

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

        // El minimapa queda igual si sigue con valores absolutos (o lo podés escalar también)
        minimapView = new MinimapView(baseX, baseY, baseWidth, baseHeight, model.getNumLevel(), screenWidth, screenHeight);
    }


    public void drawPreLevelScreen(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Nivel: " + model.getLevelName(), 100, 100);
        g.drawString("Salvá al menos: " + model.getLemmingsToGenerate() + "% de lemmings", 100, 140);
        g.fillRoundRect(325, 310, 180, 40, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("Play Level", 350, 340);

        // Podés agregar botón o esperar input para comenzar
    }

    public void drawLevel(Graphics2D g, int panelWidth, int panelHeight) {

        mapView.draw(g);
        buttonAcelerate.drawExtraButton(g, "NASHE", panelWidth, panelHeight);
        buttonSlow.drawExtraButton(g, "+", panelWidth, panelHeight);
        buttonNuke.drawExtraButton(g, "-", panelWidth, panelHeight);
        buttonDig.draw(g,"Cavar | " + stock.getQuantityAbility(Ability.DIGGER), panelWidth, panelHeight);
        buttonStop.draw(g,"Umbrella | " + stock.getQuantityAbility(Ability.UMBRELLA), panelWidth, panelHeight);
        buttonBuild.draw(g, "parar | " + stock.getQuantityAbility(Ability.STOP) , panelWidth,panelHeight);
        buttonFly.draw(g, "Escalar | " + stock.getQuantityAbility(Ability.CLIMB), panelWidth, panelHeight);

        minimapView.drawMinimap(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Lemmings salvados: " + model.getExitModel().getSavedLemmings(), 80, 80);
    }
    
    public void drawEndScreen(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (model.isLevelWon()) {
            g.drawString("Nivel completado!", 200, 200);
            g.drawString("Enter para avanzar al siguiente nivel", 200, 300);
        } else {
            g.drawString("Perdiste", 200, 200);
            g.drawString("Enter para repetir el nivel", 200, 300);
            g.drawString("Escape para volver al menu", 200, 400);
        }
    }

    public void setCamX(int camX){
        this.camX = camX;
    }

    public void setCamY(int camY){
        this.camY = camY;
    }

    public int getCamX(){
        return camX;
    }
    public int getCamY(){
        return camY;
    }   


    public void reset(){
        stock = model.getStock();
    }
}
