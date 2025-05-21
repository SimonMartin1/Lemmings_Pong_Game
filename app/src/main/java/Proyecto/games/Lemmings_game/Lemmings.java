package Proyecto.games.Lemmings_game;
import java.awt.Graphics2D;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Lemmings_game.controller.LemmingsController;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;


//Explanation of the logic:
// 1. The Lemmings class extends JGame, which is a custom game loop class.
// 2. The constructor initializes the game with a title and dimensions in JGame.
// 3. The main method creates an instance of Lemmings and starts the game loop at 60 FPS and calling the own function that each game overrides.



public class Lemmings extends JGame {
private LemmingsView view;
    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 1200, 800);
        game.run(1.0 / 60.0); // 60 FPS
    }

    @Override
    public void gameStartup() {
        // start the game, view receives the instance of the game
    }

    @Override
    public void gameUpdate(double delta) {
        // Actualizar la lógica del juego
    }
    @Override
    public void gameDraw(Graphics2D g) {
        //Dibujar elementos del juego
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    @Override
    public void ReadPropertiesFile() {
        // Leer propiedades del juego desde un archivo
        // Implementar la lógica para leer el archivo de propiedades
    }

    public LemmingsView getLemmingsView(){
        return view;
    }
}