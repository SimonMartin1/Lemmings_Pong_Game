package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.Ability;
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

    public LevelView(LevelModel model, MapView mapView, Stock stock){
        this.mapView  = mapView;
        this.model = model;
        this.stock = stock;

        minimapView = new MinimapView(480, 480, 250, 100, model.getNumLevel());

        buttonDig =  new Buttons("Cavar | " + stock.getQuantityAbility(Ability.DIGGER), 10, 450, 100, 150);
        buttonBuild = new Buttons("Parar | " + stock.getQuantityAbility(Ability.STOP),110,450,100,150);
        buttonStop = new Buttons("Construir | " + stock.getQuantityAbility(Ability.DIGGER),210,450,100,150);
        buttonFly =  new Buttons("Volar | " + stock.getQuantityAbility(Ability.CLIMB),310,450,100,150);

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

        buttonDig.draw(g, "Cavar | " + stock.getQuantityAbility(Ability.DIGGER));
        buttonStop.draw(g, "xd | " + stock.getQuantityAbility(Ability.DIGGER));
        buttonBuild.draw(g, "parar | " + stock.getQuantityAbility(Ability.STOP));
        buttonFly.draw(g, "Volar | " + stock.getQuantityAbility(Ability.CLIMB));
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
