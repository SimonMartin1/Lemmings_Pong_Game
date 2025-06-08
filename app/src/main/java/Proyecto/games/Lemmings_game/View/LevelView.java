package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.LevelModel;

import java.awt.*;

public class LevelView {
    private LevelModel model;
    private MapView mapView;

    private MinimapView minimapView;

    private Buttons buttonDig;
    private Buttons buttonBuild;
    private Buttons buttonStop;
    private Buttons buttonFly;

    private int camX;
    private int camY;

    public LevelView(LevelModel model, MapView mapView){
        this.mapView  = mapView;
        this.model = model;

        buttonDig =  new Buttons("Cavar", 10, 450, 100, 150);
        minimapView = new MinimapView(480, 480, 250, 100, model.getNumLevel());
        buttonBuild = new Buttons("Parar",110,450,100,150);
        buttonStop = new Buttons("Construir",210,450,100,150);
        buttonFly =  new Buttons("Volar",310,450,100,150);

    }


    public void drawPreLevelScreen(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Nivel: " + model.getLevelName(), 100, 100);
        g.drawString("Salvá al menos: " + model.getLemmingsToGenerate() + "% de lemmings", 100, 140);

        // Podés agregar botón o esperar input para comenzar
    }

    public void drawLevel(Graphics2D g) {

        mapView.draw(g);

        buttonDig.draw(g);
        buttonStop.draw(g);
        buttonBuild.draw(g);
        buttonFly.draw(g);
        minimapView.drawMinimap(g);
    }

    public void drawEndScreen(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (model.isLevelWon()) {
            g.drawString("¡Nivel completado!", 200, 200);
        } else {
            g.drawString("Perdiste 😢", 200, 200);
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
