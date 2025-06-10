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

    private int camX;
    private int camY;

    //int screenWidth = 1366;
    //int screenHeight = 768;
    private int screenWidth;
    private int screenHeight;
    
    // Estos son los valores fijos que usabas antes
    int baseX = 850;
    int baseY = 600;
    int baseWidth = 250;
    int baseHeight = 100;

    public LevelView(LevelModel model, MapView mapView, Stock stock, int screenWidth, int screenHeight) {
        this.mapView = mapView;
        this.model = model;
        this.stock = stock;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Relativo a pantalla: x = porcentaje del ancho, y = porcentaje del alto
        // ancho = 100px de 768px ≈ 0.13 — alto = 150px de 600px ≈ 0.25
        float buttonWidth = 0.13f;
        float buttonHeight = 0.25f;
        float startY = 0.75f; // 450/600

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

        // Podés agregar botón o esperar input para comenzar
    }

    public void drawLevel(Graphics2D g, int panelWidth, int panelHeight) {
        mapView.draw(g);
    
        buttonDig.draw(g,"Cavar | " + stock.getQuantityAbility(Ability.DIGGER), panelWidth, panelHeight);
        buttonStop.draw(g,"Umbrella | " + stock.getQuantityAbility(Ability.UMBRELLA), panelWidth, panelHeight);
        buttonBuild.draw(g, "parar | " + stock.getQuantityAbility(Ability.STOP) , panelWidth,panelHeight);
        buttonFly.draw(g, "Escalar | " + stock.getQuantityAbility(Ability.CLIMB), panelWidth, panelHeight);

        minimapView.drawMinimap(g);
    }
    
    public void drawEndScreen(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (model.isLevelWon()) {
            g.drawString("¡Nivel completado!", 200, 200);
        } else {
            g.drawString("Perdiste", 200, 200);
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

}
