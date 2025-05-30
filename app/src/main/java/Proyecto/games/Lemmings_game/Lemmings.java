package Proyecto.games.Lemmings_game;

import java.awt.*;


import Proyecto.games.Lemmings_game.Model.*;
import Proyecto.games.Lemmings_game.View.*;
import com.entropyinteractive.*;


public class Lemmings extends JGame {

    private FirstLevelMapModel firstLevelMapModel;
    private FirstLevelMapView firstLevelMapView;

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }


    @Override
    public void gameStartup() {
<<<<<<< HEAD
        //modelos
        try {
            firstLevelMapModel = new FirstLevelMapModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //vistas
        firstLevelMapView = new FirstLevelMapView(firstLevelMapModel);


=======
        // start the game, view receives the instance of the game
>>>>>>> 6d48f51cc15b8e6e322edcd0af117a9379d9737e
    }

    @Override
    public void gameUpdate(double delta) {

    }

    @Override
    public void gameDraw(Graphics2D g) {
        // Limpiar pantalla
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar elementos
        firstLevelMapView.draw(g);
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
}