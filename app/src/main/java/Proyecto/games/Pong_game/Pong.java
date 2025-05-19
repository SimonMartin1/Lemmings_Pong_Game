package Proyecto.games.Pong_game;
import java.awt.Graphics2D;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Pong_game.controller.PongController;
import Proyecto.games.Pong_game.model.PongModel;
import Proyecto.games.Pong_game.View.PongView;


//Explanation of the logic:
// 1. The Pong class extends JGame, which is a custom game loop class.
// 2. The constructor initializes the game with a title and dimensions in JGame.
// 3. The main method creates an instance of Pong and starts the game loop at 60 FPS and calling the own function that each game overrides.

public class Pong extends JGame {
    public Pong(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Pong game = new Pong("Mi Pong", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }
    @Override
    public void gameStartup() {
        PongModel model = new PongModel();
        PongView view = new PongView();
        PongController controller = new PongController(model, view);
    }

    @Override
    public void gameUpdate(double delta) {
    }
    @Override
    public void gameDraw(Graphics2D g) {
        // Dibujar elementos del juego
        //g.fillRect(0, 0, width, height);
        //Dibujar elementos del juego
    }
        
    

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    @Override protected void ReadPropertiesFile(){
        
    }
}
