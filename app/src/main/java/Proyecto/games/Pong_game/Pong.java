package Proyecto.games.Pong_game;
import java.awt.Graphics2D;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Pong_game.controller.PongController;
import Proyecto.games.Pong_game.model.PongModel;
import Proyecto.games.Pong_game.view.PongView;

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
        // Inicializar pelotas, paletas, etc.
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
        g.fillRect(0, 0, width, height);
        //Dibujar elementos del juego
    }
        
    

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    @Override protected void ReadPropertiesFile(){
        
    }
}
